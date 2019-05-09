import frida
import subprocess


def get_frida_version():
    return {
        'version': frida.__version__
    }


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


def get_js_code(js_file_name):
    js_file = open(js_file_name)
    return js_file.read()


if __name__ == '__main__':
    print(get_frida_version())


