import frida
import sys


def hook(host, process_name, js_files):
    manager = frida.get_device_manager()
    try:
        remote_device = manager.add_remote_device(host)
    except Exception as e:
        manager.remove_remote_device(host)
        remote_device = manager.add_remote_device(host)
        print(e)
    try:
        remote_device.get_process(process_name)
        session = remote_device.attach(process_name)
        for js_file_name in js_files:
            js_file = open(js_file_name, 'r')
            script = session.create_script(js_file.read())
            js_file.close()
            script.on('message', on_message)
            script.load()
        return {
            'hook_status': 'success'
        }
    except frida.ServerNotRunningError as e:
        print(e)
        return {
            'hook_status': 'unable to connect to remote frida-server'
        }
    except frida.ProcessNotFoundError as e:
        print(e)
        return {
            'hook_status': 'unable to find process with name ' + process_name
        }


def stop_hook(host):
    manager = frida.get_device_manager()
    try:
        manager.remove_remote_device(host)
        return {
            'hook_status': 'stop'
        }
    except Exception as e:
        print(e)
        return {
            'hook_status': 'device not found'
        }


def get_process_list(host):
    manager = frida.get_device_manager()
    try:
        remote_device = manager.add_remote_device(host)
    except Exception as e:
        manager.remove_remote_device(host)
        remote_device = manager.add_remote_device(host)
        print(e)
    try:
        remote_processes_obj = remote_device.enumerate_processes()
        remote_processes = []
        for process_obj in remote_processes_obj:
            remote_processes.append(process_obj.name)
        manager.remove_remote_device(host)
        return {
            'hook_status': 'success',
            'processes': remote_processes
        }
    except frida.ServerNotRunningError as e:
        print(e)
        return {
            'hook_status': 'unable to connect to remote frida-server',
            'processes': []
        }


def get_frida_version():
    return {
        'version': frida.__version__
    }


def on_message(message, data):
    if message['type'] == 'send':
        print(message['payload'])
    elif message['type'] == 'error':
        print(message['description'])
    else:
        print(message)


if __name__ == '__main__':
    print(get_frida_version())
    result = get_process_list('127.0.0.1:27042')
    for process in result['processes']:
        print(process)
    hook_status = hook('127.0.0.1:27042', 'com.huawei.ipc_honor',
                       ['android_injection/monitoring_api.js',
                        'android_injection/monitoring_ip.js',
                        'android_injection/monitoring_traffic.js',
                        'android_injection/monitoring_fileio.js',
                        'android_injection/monitoring_dbio.js'])
    print(hook_status)
    while True:
        cmd_in = sys.stdin.readline().strip('\n')
        if cmd_in == 'exit':
            break
        if cmd_in == 'stop':
            hook_status = stop_hook('127.0.0.1:27042')
            print(hook_status)
