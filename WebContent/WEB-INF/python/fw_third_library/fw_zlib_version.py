import os
import re
import platform


libz_so_names = [
    '/lib/libz.so',
    '/usr/lib/libz.so',
    '/usr/local/lib/libz.so',
]
zlib_version_search_regex = b'1.[0-9]*.[0-9]*[0-9]? Copyright'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    libz_so_path = ''
    for name in libz_so_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            libz_so_path = name

    if libz_so_path == '':
        return {
            'lib_avaliable': False
        }
    libz_so = open(base_dir + libz_so_path.replace('/', path_fix), 'rb')
    libz_so_binary = libz_so.read()
    libz_so.close()
    regex = re.compile(zlib_version_search_regex)
    zlib_version = regex.findall(libz_so_binary)
    if len(zlib_version) != 0:
        result = {
            'lib_name': 'Zlib',
            'lib_avaliable': True,
            'lib_path': libz_so_path,
            'lib_version': zlib_version[0].decode('utf8').replace(' Copyright', '')
        }
    else:
        result = {
            'lib_name': 'Zlib',
            'lib_avaliable': True,
            'lib_path': libz_so_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

