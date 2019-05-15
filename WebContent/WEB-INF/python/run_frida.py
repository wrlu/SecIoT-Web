import frida
import sys


def hook(host, process_name, js_file_name):
    manager = frida.get_device_manager()
    remote_device = manager.add_remote_device(host)
    remote_device.get_process(process_name)
    remote_process = remote_device.attach(process_name)
    js_file = open(js_file_name, 'r')
    script = remote_process.create_script(js_file.read())
    script.on('message', on_message)
    script.load()
    sys.stdin.read()


def get_process_list(host):
    manager = frida.get_device_manager()
    remote_device = manager.add_remote_device(host)

    remote_processes_obj = remote_device.enumerate_processes()
    remote_processes = []
    for process_obj in remote_processes_obj:
        remote_processes.append(process_obj.name)
    manager.remove_remote_device(host)
    return {
        'processes': remote_processes
    }


def get_frida_version():
    return {
        'version': frida.__version__
    }


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


if __name__ == '__main__':
    print(get_frida_version())
    result = get_process_list('127.0.0.1:9001')
    for process in result['processes']:
        print(process)


