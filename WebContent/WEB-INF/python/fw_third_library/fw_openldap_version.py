import os
import re
import platform


libldap_so_names = [
    '/lib/libldap_r-2.4.so.2',
    '/usr/lib/libldap_r-2.4.so.2',
    '/usr/local/lib/libldap_r-2.4.so.2',
]
libldap_version_search_regex = b'libldap_r-2.4.so.[0-2].[0-9]+.[0-9]+-[0-2].[0-9].[0-9]+'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    libldap_so_path = ''
    for name in libldap_so_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            libldap_so_path = name

    if libldap_so_path == '':
        return {
            'lib_avaliable': False
        }
    libldap_so = open(base_dir + libldap_so_path.replace('/', path_fix), 'rb')
    libldap_so_binary = libldap_so.read()
    libldap_so.close()
    regex = re.compile(libldap_version_search_regex)
    libldap_version = regex.findall(libldap_so_binary)
    libldap_version_readable = libldap_version[0].decode('utf8').replace('libldap_r-2.4.so.2', '')
    libldap_version_readable = libldap_version_readable[libldap_version_readable.find('-') + 1:]
    if len(libldap_version) != 0:
        result = {
            'lib_name': 'OpenLDAP',
            'lib_avaliable': True,
            'lib_path': libldap_so_path,
            'lib_version': libldap_version_readable
        }
    else:
        result = {
            'lib_name': 'OpenLDAP',
            'lib_avaliable': True,
            'lib_path': libldap_so_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/'
    # d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

