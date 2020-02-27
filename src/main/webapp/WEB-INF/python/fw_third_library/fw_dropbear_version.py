import os
import re
import platform


dropbear_names = [
    '/sbin/dropbear',
    '/usr/sbin/dropbear',
    '/bin/dropbear',
    '/usr/bin/dropbear',
    '/usr/local/bin/dropbear'
]
dropbear_version_search_regex = b'20[1-9][0-9].[0-9]+\x00Dropbear SSH'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    dropbear_path = ''
    for name in dropbear_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            dropbear_path = name

    if dropbear_path == '':
        return {
            'lib_avaliable': False
        }
    dropbear_exec = open(base_dir + dropbear_path.replace('/', path_fix), 'rb')
    dropbear_exec_binary = dropbear_exec.read()
    dropbear_exec.close()
    regex = re.compile(dropbear_version_search_regex)
    dropbear_version = regex.findall(dropbear_exec_binary)
    if len(dropbear_version) != 0:
        result = {
            'lib_name': 'Dropbear',
            'lib_avaliable': True,
            'lib_path': dropbear_path,
            'lib_version': dropbear_version[0].decode('utf8').replace('\x00Dropbear SSH', '')
        }
    else:
        result = {
            'lib_name': 'Dropbear',
            'lib_avaliable': True,
            'lib_path': dropbear_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

