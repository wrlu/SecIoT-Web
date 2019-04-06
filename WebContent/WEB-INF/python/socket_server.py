import socketserver
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
        data = self.request.recv(2048)


if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('localhost', 8888), SocketServer)
    server.serve_forever()
