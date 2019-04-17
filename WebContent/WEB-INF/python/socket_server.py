import socketserver
import json
import run_binwalk
import fw_linux_shadow
import fw_openssl_version
import run_jadx
import android_exported
import android_permission
import android_ssl_pinning


class FwService:
    def get_fw_info(self, file_name, file_path):
        return run_binwalk.get_fw_info(file_name, file_path)

    def get_fw_root_directory(self, fw_info):
        return run_binwalk.get_fw_root_directory(fw_info)

    def linux_shadow(self, base_dir):
        return fw_linux_shadow.do(base_dir)

    def get_fw_third_library(self, base_dir, lib_name):
        if lib_name.lower() == "openssl":
            return fw_openssl_version.do(base_dir)


class AndroidService:
    def get_apk_info(self, file_name, file_path):
        return run_jadx.get_apk_info(file_name, file_path)

    def ssl_pinning(self, sources_dir):
        return android_ssl_pinning.do(sources_dir)

    def permission(self, manifest_file):
        return android_permission.do(manifest_file)

    def exported(self, manifest_file):
        return android_exported.do(manifest_file)


class AppleiOSService:
    def get_ipa_info(self, file_name, file_path):
        pass

    def ats_policy(self, info_plist_file):
        pass

    def permission(self, info_plist_file):
        pass


class TrafficService:
    def get_connection_pairs(self, file_name, file_path, ip):
        pass


class ScanService:
    def device_scan(self):
        pass

    def port_scan(self, ip):
        pass


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
                result = fwservice.get_fw_info(params['file_name'], params['file_path'])
            elif method == 'get_fw_root_directory':
                result = fwservice.get_fw_root_directory(params['fw_info'])
            elif method == 'get_fw_third_library':
                result = fwservice.get_fw_third_library(params['fw_info']['fw_root_directory'], params['lib_name'])
            elif method == 'linux_shadow':
                result = fwservice.linux_shadow(params['fw_info']['fw_root_directory'])

        elif classname == 'AndroidService':
            androidservice = AndroidService()
            if method == 'get_apk_info':
                result = androidservice.get_apk_info(params['file_name'], params['file_path'])
            elif method == 'permission':
                result = androidservice.permission(params['apk_info']['apk_manifest_file'])
            elif method == 'exported':
                result = androidservice.exported(params['apk_info']['apk_manifest_file'])
            elif method == 'ssl_pinning':
                result = androidservice.ssl_pinning(params['apk_info']['apk_sources_path'])

        elif classname == 'AppleiOSService':
            iosservice = AppleiOSService()
        elif classname == 'TrafficService':
            trafficservice = TrafficService()
        elif classname == 'ScanService':
            scanservice = ScanService()

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
