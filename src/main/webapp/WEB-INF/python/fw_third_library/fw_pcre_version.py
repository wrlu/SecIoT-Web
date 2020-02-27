import os
import re
import platform


pcre_so_names = [
    '/lib/libpcre.so',
    '/usr/lib/libpcre.so',
    '/usr/local/lib/libpcre.so',
]
pcre_version_search_regex = b'[1,7-9]+.[0-9]+ 20[0-9][0-9]-[0-1][0-9]-[0-3][0-9]'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    pcre_so_path = ''
    for name in pcre_so_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            pcre_so_path = name

    if pcre_so_path == '':
        return {
            'lib_avaliable': False
        }
    pcre_so = open(base_dir + pcre_so_path.replace('/', path_fix), 'rb')
    pcre_so_binary = pcre_so.read()
    pcre_so.close()
    regex = re.compile(pcre_version_search_regex)
    zlib_version = regex.findall(pcre_so_binary)
    if len(zlib_version) != 0:
        result = {
            'lib_name': 'Pcre',
            'lib_avaliable': True,
            'lib_path': pcre_so_path,
            'lib_version': zlib_version[0].decode('utf8')[:-11]
        }
    else:
        result = {
            'lib_name': 'Pcre',
            'lib_avaliable': True,
            'lib_path': pcre_so_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

