import socketserver
import json
import run_binwalk
import run_jadx
import run_frida
import run_frps
import fw_linux_shadow
import fw_openssl_version
import fw_dropbear_enable
import fw_dropbear_auth_keys
import android_exported
import android_permission
import android_ssl_pinning


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

    @staticmethod
    def dropbear_enable(base_dir):
        return fw_dropbear_enable.do(base_dir)

    @staticmethod
    def dropbear_auth_keys(base_dir):
        return fw_dropbear_auth_keys.do(base_dir)


class AndroidService:
    @staticmethod
    def get_apk_info(file_name, file_path):
        return run_jadx.get_apk_info(file_name, file_path)

    @staticmethod
    def ssl_pinning(sources_dir):
        return android_ssl_pinning.do(sources_dir)

    @staticmethod
    def permission(manifest_file):
        return android_permission.do(manifest_file)

    @staticmethod
    def exported(manifest_file):
        return android_exported.do(manifest_file)


class FridaService:
    @staticmethod
    def get_frida_version():
        return run_frida.get_frida_version()


class FrpsService:
    @staticmethod
    def get_frps_version(path):
        return run_frps.get_frps_version(path)


class AppleiOSService:
    @staticmethod
    def get_ipa_info(file_name, file_path):
        pass

    @staticmethod
    def ats_policy(info_plist_file):
        pass

    @staticmethod
    def permission(info_plist_file):
        pass


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
        if cmd == "exit":
            exit(int(params['code']))
        classname = cmd.split('.')[0]
        method = cmd.split('.')[1]
        print('classname: '+classname)
        print('method: '+method)
        print('params: '+str(params))
        return classname, method, params

    @staticmethod
    def do_action(classname, method, params):
        result = {}
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

        elif classname == 'AndroidService':
            if method == 'get_apk_info':
                result = AndroidService.get_apk_info(params['file_name'], params['file_path'])
            elif method == 'permission':
                result = AndroidService.permission(params['apk_info']['apk_manifest_file'])
            elif method == 'exported':
                result = AndroidService.exported(params['apk_info']['apk_manifest_file'])
            elif method == 'ssl_pinning':
                result = AndroidService.ssl_pinning(params['apk_info']['apk_sources_path'])

        elif classname == 'FridaService':
            if method == 'get_frida_version':
                result = FridaService.get_frida_version()

        elif classname == 'FrpsService':
            if method == 'get_frps_version':
                result = FrpsService.get_frps_version(params['frps_path'])

        elif classname == 'AppleiOSService':
            pass

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
        return ret


if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('localhost', 8081), PySocketServerHandler)
    server.serve_forever()
