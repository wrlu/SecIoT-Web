import os
import re
import platform


libcrypto_so_names = [
    '/lib/libcrypto.so.1.0.0',
    '/usr/lib/libcrypto.so.1.0.0',
    '/usr/lib/ssl/libcrypto.so.1.0.0',
    '/usr/local/lib/libcrypto.so.1.0.0',
    '/usr/local/ssl/lib/libcrypto.so.1.0.0',
    '/lib/libssl.so.1.0.0',
    '/usr/lib/libssl.so.1.0.0',
    '/usr/lib/ssl/libssl.so.1.0.0',
    '/usr/local/lib/libssl.so.1.0.0',
    '/usr/local/ssl/lib/libssl.so.1.0.0'
]
openssl_version_search_regex = b'OpenSSL [0-1].[0-9]+.[0-9]+[a-z]?'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    libcrypto_so_path = ''
    for name in libcrypto_so_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            libcrypto_so_path = name

    if libcrypto_so_path == '':
        return {
            'lib_avaliable': False
        }
    libcrypto_so = open(base_dir + libcrypto_so_path.replace('/', path_fix), 'rb')
    libcrypto_so_binary = libcrypto_so.read()
    libcrypto_so.close()
    regex = re.compile(openssl_version_search_regex)
    openssl_version = regex.findall(libcrypto_so_binary)
    if len(openssl_version) != 0:
        result = {
            'lib_name': 'OpenSSL',
            'lib_avaliable': True,
            'lib_path': libcrypto_so_path,
            'lib_version': openssl_version[0].decode('utf8').replace('OpenSSL ', '')
        }
    else:
        result = {
            'lib_name': 'OpenSSL',
            'lib_avaliable': True,
            'lib_path': libcrypto_so_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

