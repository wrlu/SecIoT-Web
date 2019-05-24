import os
import re
import platform


libc_so_names = [
    '/lib/libc.so.6',
    '/usr/lib/libc.so.6'
]
libc_version_search_regex = b'version [0-9].[0-9]+'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    libc_so_path = ''
    for name in libc_so_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            libc_so_path = name

    if libc_so_path == '':
        return {
            'lib_avaliable': False
        }
    libc_so = open(base_dir + libc_so_path.replace('/', path_fix), 'rb')
    libc_so_binary = libc_so.read()
    libc_so.close()
    regex = re.compile(libc_version_search_regex)
    libc_version = regex.findall(libc_so_binary)
    if len(libc_version) != 0:
        result = {
            'lib_name': 'Uclibc',
            'lib_avaliable': True,
            'lib_path': libc_so_path,
            'lib_version': libc_version[0].decode('utf8').replace('version ', '')
        }
    else:
        result = {
            'lib_name': 'Uclibc',
            'lib_avaliable': True,
            'lib_path': libc_so_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

