import socketserver
import json
import run_binwalk
import fw_linux_shadow
import fw_openssl_version
import run_jadx


class FwService:
    def get_fw_info(self, file_name, path):
        return run_binwalk.get_fw_info(file_name, path)

    def get_fw_root_directory(self, fw_info):
        return run_binwalk.get_fw_root_directory(fw_info)

    def linux_shadow(self, base_dir):
        return fw_linux_shadow.do(base_dir)

    def openssl_version(self, base_dir):
        return fw_openssl_version.do(base_dir)


class AndroidService:
    def get_apk_info(self, file_name, path):
        return run_jadx.get_apk_info(file_name, path)


class SocketServer(socketserver.BaseRequestHandler):
    def handle(self):
        r_data = self.request.recv(2048)
        data = json.load(r_data.encode('utf8'))
        classname, method, params = self.resolve_data(data)
        result = {}
        if classname == 'FwService':
            fwservice = FwService()
            if method == 'get_fw_info':
                result = fwservice.get_fw_info(params['file_name'], params['path'])
            elif method == 'get_fw_root_directory':
                result = fwservice.get_fw_root_directory(params['fw_info'])
        if len(result) != 0:
            ret = {
                'status': 0,
                'reason': 'OK',
                'data': result
            }
        else:
            ret = {
                'status': -1,
                'reason': 'Python Error'
            }
        self.request.sendall(json.dump(ret))

    def resolve_data(self, data):
        cmd = data['cmd']
        params = data['params']
        if cmd == "exit":
            exit(int(params['code']))
        classname = cmd.split('.')
        method = cmd.split('.')
        return classname, method, params


if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('localhost', 8888), SocketServer)
    server.serve_forever()
