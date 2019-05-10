import frida
import sys


def hook(process_name, js_file_name):
    device = frida.get_remote_device()
    device.get_process(process_name)
    process = device.attach(process_name)
    js_file = open(js_file_name, 'r')
    script = process.create_script(js_file.read())
    script.on('message', on_message)
    script.load()
    sys.stdin.read()


def get_process_list():
    device = frida.get_remote_device()
    processes = device.enumerate_processes()
    return processes


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
    processes = get_process_list()
    for process in processes:
        print(process)


