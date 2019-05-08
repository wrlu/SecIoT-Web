import frida


js_file_name = '.js'
process_name = 'com.huawei.ipc'


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


def get_js_code():
    js_file = open(js_file_name)
    return js_file.read()


if __name__ == '__main__':
    device = frida.get_remote_device()
    processes = device.enumerate_processes()
    for process in processes:
        print(process)

    # process = device.attach(process_name)
    # script = process.create_script(get_js_code())
    # script.on('message', on_message)
    # script.load()
    # sys.stdin.read()


