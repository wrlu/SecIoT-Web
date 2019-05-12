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
    remote_processes = remote_device.enumerate_processes()
    return remote_processes


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
    processes = get_process_list('127.0.0.1:9000')
    for process in processes:
        print(process)


