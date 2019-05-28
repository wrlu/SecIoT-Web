import socketserver
import json
from run_tools import run_frps
from run_tools import run_jadx
from run_tools import run_frida
from run_tools import run_binwalk
from run_tools import run_unzip_ipa
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
from android_platform import android_permission
from android_platform import android_ssl_pinning
from android_platform import android_exported
from ios_platform import ios_ats_policy
from ios_platform import ios_background_usage
from ios_platform import ios_permission


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

    @staticmethod
    def get_process_list(port):
        return run_frida.get_process_list("127.0.0.1:" + str(port))


class FrpsService:
    @staticmethod
    def get_frps_version(path):
        return run_frps.get_frps_version(path)


class AppleiOSService:
    @staticmethod
    def get_ipa_info(file_name, file_path):
        return run_unzip_ipa.get_ipa_info(file_name, file_path)

    @staticmethod
    def ats_policy(info_plist_file):
        return ios_ats_policy.do(info_plist_file)

    @staticmethod
    def background(info_plist_file):
        return ios_background_usage.do(info_plist_file)

    @staticmethod
    def permission(info_plist_file):
        return ios_permission.do(info_plist_file)


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
                elif method == 'get_process_list':
                    result = FridaService.get_process_list(params['port'])

            elif classname == 'FrpsService':
                if method == 'get_frps_version':
                    result = FrpsService.get_frps_version(params['frps_path'])

            elif classname == 'AppleiOSService':
                if method == 'get_ipa_info':
                    result = AppleiOSService.get_ipa_info(params['file_name'], params['file_path'])
                elif method == 'permission':
                    result = AppleiOSService.permission(params['ipa_info']['ipa_info_plist_file'])
                elif method == 'background':
                    result = AppleiOSService.background(params['ipa_info']['ipa_info_plist_file'])
                elif method == 'ats_policy':
                    result = AppleiOSService.ats_policy(params['ipa_info']['ipa_info_plist_file'])

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
