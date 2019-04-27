import sys
import frida
import _frida


js_file_name = '.js'
process_name = 'com.huawei.ipc'


# 自定义回调函数
def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


def get_js_code():
    js_file = open(js_file_name)
    return js_file.read()


if __name__ == '__main__':
    # 附加到进程并得到进程对象
    device = frida.get_usb_device()
    try:
        device.get_process(process_name)
        process = device.attach(process_name)
        # 指定JavaScript脚本
        script = process.create_script(get_js_code())
        # 加载JavaScript脚本
        script.on('message', on_message)
        script.load()
        # 读取返回输入
        sys.stdin.read()
    except _frida.ProcessNotFoundError as error:
        print('[!] ProcessNotFoundError: unable to find process with name '+process_name)


