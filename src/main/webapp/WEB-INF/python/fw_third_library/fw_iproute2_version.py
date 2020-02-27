import os
import re
import platform


iproute2_names = [
    '/sbin/ip',
    '/usr/sbin/ip'
]
iproute2_version_search_regex = b'1[0-9][0-1][0-9][0-3][0-9]'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    iproute2_path = ''
    for name in iproute2_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            iproute2_path = name

    if iproute2_path == '':
        return {
            'lib_avaliable': False
        }
    iproute2_exec = open(base_dir + iproute2_path.replace('/', path_fix), 'rb')
    iproute2_exec_binary = iproute2_exec.read()
    iproute2_exec.close()
    regex = re.compile(iproute2_version_search_regex)
    iproute2_version = regex.findall(iproute2_exec_binary)
    if len(iproute2_version) != 0:
        result = {
            'lib_name': 'Iproute2',
            'lib_avaliable': True,
            'lib_path': iproute2_path,
            'lib_version': iproute2_version[0].decode('utf8')
        }
    else:
        result = {
            'lib_name': 'Iproute2',
            'lib_avaliable': True,
            'lib_path': iproute2_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/'
    # d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

