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

    def get_fw_third_library(self, base_dir, lib_name):
        if lib_name.lower() == "openssl":
            return fw_openssl_version.do(base_dir)


class AndroidService:
    def get_apk_info(self, file_name, path):
        return run_jadx.get_apk_info(file_name, path)


class SocketServer(socketserver.BaseRequestHandler):
    def handle(self):
        r_data = self.request.recv(2048)
        data = json.loads(r_data.decode('utf8'))
        print("Receive data: "+str(data))
        classname, method, params = self.resolve_data(data)
        ret = self.do_action(classname, method, params)
        print("Send data: "+str(ret))
        self.request.sendall(json.dumps(ret).encode('utf8'))

    def resolve_data(self, data):
        cmd = data['cmd']
        params = data['params']
        if cmd == "exit":
            exit(int(params['code']))
        classname = cmd.split('.')[0]
        method = cmd.split('.')[1]
        print('classname: '+classname)
        print('method: '+method)
        print('params: '+str(params))
        return classname, method, params

    def do_action(self, classname, method, params):
        result = {}
        if classname == 'FwService':
            fwservice = FwService()
            if method == 'get_fw_info':
                result = fwservice.get_fw_info(params['file_name'], params['path'])
            elif method == 'get_fw_root_directory':
                result = fwservice.get_fw_root_directory(params['fw_info'])
            elif method == 'get_fw_third_library':
                result = fwservice.get_fw_third_library(params['base_dir'], params['lib_name'])
        if len(result) != 0:
            ret = {
                'status': 0,
                'reason': 'OK',
                'data': result
            }
        else:
            ret = {
                'status': 1002,
                'reason': 'No such python method or python error'
            }
        return ret


if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('localhost', 8888), SocketServer)
    server.serve_forever()
