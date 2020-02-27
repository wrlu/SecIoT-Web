import os
import re
import platform


busybox_names = [
    '/bin/busybox',
    '/usr/bin/busybox',
    '/usr/local/bin/busybox',
    '/sbin/busybox',
    '/usr/sbin/busybox'
]
busybox_version_search_regex = b'BusyBox v[0-1].[0-9]*.[0-9]*'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    busybox_path = ''
    for name in busybox_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            busybox_path = name

    if busybox_path == '':
        return {
            'lib_avaliable': False
        }
    busybox_exec = open(base_dir + busybox_path.replace('/', path_fix), 'rb')
    busybox_exec_binary = busybox_exec.read()
    busybox_exec.close()
    regex = re.compile(busybox_version_search_regex)
    dropbear_version = regex.findall(busybox_exec_binary)
    if len(dropbear_version) != 0:
        result = {
            'lib_name': 'Busybox',
            'lib_avaliable': True,
            'lib_path': busybox_path,
            'lib_version': dropbear_version[0].decode('utf8').replace('BusyBox v', '')
        }
    else:
        result = {
            'lib_name': 'Dropbear',
            'lib_avaliable': True,
            'lib_path': busybox_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

