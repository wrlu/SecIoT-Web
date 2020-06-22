import socketserver
import json
import platform
from run_tools import run_binwalk
from fw_third_library import fw_openssl_version
from fw_third_library import fw_zlib_version
from fw_third_library import fw_dropbear_version
from fw_third_library import fw_busybox_version
from fw_third_library import fw_openssh_version
from fw_third_library import fw_iproute2_version
from fw_third_library import fw_pcre_version
from fw_third_library import fw_miniupnp_version
from fw_third_library import fw_uclibc_version
from fw_third_library import fw_openldap_version
from fw_platform import fw_dropbear_enable
from fw_platform import fw_linux_shadow
from fw_platform import fw_dropbear_auth_keys
from fw_platform import fw_crontab


class FwService:
    @staticmethod
    def get_fw_info(file_name, file_path):
        return run_binwalk.get_fw_info(file_name, file_path)

    @staticmethod
    def get_fw_root_directory(fw_info):
        return run_binwalk.get_fw_root_directory(fw_info)

    @staticmethod
    def linux_shadow(base_dir):
        return fw_linux_shadow.do(base_dir)

    @staticmethod
    def get_fw_third_library(base_dir, lib_name):
        if lib_name.lower() == "openssl":
            return fw_openssl_version.do(base_dir)
        elif lib_name.lower() == "dropbear":
            return fw_dropbear_version.do(base_dir)
        elif lib_name.lower() == "openssh":
            return fw_openssh_version.do(base_dir)
        elif lib_name.lower() == "zlib":
            return fw_zlib_version.do(base_dir)
        elif lib_name.lower() == "iproute2":
            return fw_iproute2_version.do(base_dir)
        elif lib_name.lower() == "miniupnp":
            return fw_miniupnp_version.do(base_dir)
        elif lib_name.lower() == "pcre":
            return fw_pcre_version.do(base_dir)
        elif lib_name.lower() == "uclibc":
            return fw_uclibc_version.do(base_dir)
        elif lib_name.lower() == "busybox":
            return fw_busybox_version.do(base_dir)
        elif lib_name.lower() == "openldap":
            return fw_openldap_version.do(base_dir)

    @staticmethod
    def dropbear_enable(base_dir):
        return fw_dropbear_enable.do(base_dir)

    @staticmethod
    def dropbear_auth_keys(base_dir):
        return fw_dropbear_auth_keys.do(base_dir)

    @staticmethod
    def crontab(base_dir):
        return fw_crontab.do(base_dir)

class PySocketServerHandler(socketserver.BaseRequestHandler):
    def handle(self):
        r_data = self.request.recv(2048)
        data = json.loads(r_data.decode('utf8'))
        print("Receive data: "+str(data))
        classname, method, params = self.resolve_data(data)
        ret = self.do_action(classname, method, params)
        print("Send data: "+str(ret))
        self.request.sendall(json.dumps(ret).encode('utf8'))

    @staticmethod
    def resolve_data(data):
        cmd = data['cmd']
        params = data['params']
        classname = cmd.split('.')[0]
        method = cmd.split('.')[1]
        print('classname: '+classname)
        print('method: '+method)
        print('params: '+str(params))
        return classname, method, params

    @staticmethod
    def do_action(classname, method, params):
        result = {}
        try:
            if classname == 'FwService':
                if method == 'get_fw_info':
                    result = FwService.get_fw_info(params['file_name'], params['file_path'])
                elif method == 'get_fw_root_directory':
                    result = FwService.get_fw_root_directory(params['fw_info'])
                elif method == 'get_fw_third_library':
                    result = FwService.get_fw_third_library(params['fw_info']['fw_root_directory'], params['lib_name'])
                elif method == 'linux_shadow':
                    result = FwService.linux_shadow(params['fw_info']['fw_root_directory'])
                elif method == 'dropbear_enable':
                    result = FwService.dropbear_enable(params['fw_info']['fw_root_directory'])
                elif method == 'dropbear_auth_keys':
                    result = FwService.dropbear_auth_keys(params['fw_info']['fw_root_directory'])
                elif method == 'crontab':
                    result = FwService.crontab(params['fw_info']['fw_root_directory'])

            if len(result) != 0:
                ret = {
                    'status': 0,
                    'reason': 'OK',
                    'data': result
                }
            else:
                ret = {
                    'status': -1,
                    'reason': 'No such python method or python error'
                }
        except Exception as e:
            print(e)
            ret = {
                'status': -1,
                'reason': 'Python error'
            }
        return ret


if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('localhost', 8081), PySocketServerHandler)
    server.serve_forever()
