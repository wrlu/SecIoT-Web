import frida
import sys
import re


log_base_dir = 'hook_log/'
log_regex = re.compile('Host127\\.0\\.0\\.1:9[0-9][0-9][0-9]')


def hook(host, process_name, js_files, log_dir):
    global log_base_dir
    log_base_dir = log_dir
    if len(js_files) == 0:
        return {
            'hook_status': 'stop'
        }
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
        host_log = open(log_base_dir + host + '.log', 'w')
        host_log.write(' ')
        host_log.close()
        for js_file_name in js_files:
            js_file = open(js_file_name, 'r')
            script = session.create_script('var localhost = "' + host + '";' + js_file.read())
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
        host_log = open(log_base_dir + host + '.log', 'w')
        host_log.write(' ')
        host_log.close()
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
    global log_base_dir
    if message['type'] == 'send':
        payload = message['payload']
        print(payload)
        host = log_regex.findall(payload)[0]
        host_log = open(log_base_dir + host + '.log', 'a+')
        host_log.write(payload + '\n')
        host_log.close()
    elif message['type'] == 'error':
        print(message['description'])
        error_log = open(log_base_dir + 'error.log', 'a+')
        error_log.write(message['description'] + '\n')
        error_log.close()
    else:
        print(message)


if __name__ == '__main__':
    print(get_frida_version())
    result = get_process_list('127.0.0.1:9000')
    for process in result['processes']:
        print(process)
    hook_status = hook('127.0.0.1:9000', 'com.huawei.smartspeaker',
                       ['android_injection/monitoring_api.js',
                        'android_injection/monitoring_ip.js',
                        'android_injection/monitoring_traffic.js',
                        'android_injection/monitoring_fileio.js',
                        'android_injection/monitoring_dbio.js'], log_base_dir)
    print(hook_status)
    while True:
        cmd_in = sys.stdin.readline().strip('\n')
        if cmd_in == 'exit':
            break
        if cmd_in == 'stop':
            hook_status = stop_hook('127.0.0.1:9000')
            print(hook_status)
