import os
import re
import platform


libcrypto_so_names = [
    '/lib/libcrypto.so.1.0.0',
    '/usr/lib/libcrypto.so.1.0.0',
    '/usr/lib/ssl/libcrypto.so.1.0.0',
    '/usr/local/lib/libcrypto.so.1.0.0',
    '/usr/local/ssl/lib/libcrypto.so.1.0.0',
]
openssl_version_search_regex = b'OpenSSL [0-1].[0-9]*.[0-9]*.[a-z]?'


def do(base_dir):
    global libcrypto_so_names
    global openssl_version_search_regex
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    libcrypto_so_path = ''
    for name in libcrypto_so_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            libcrypto_so_path = name
    result = {}
    if libcrypto_so_path == '':
        result['lib_avaliable'] = False
        return result
    libcrypto_so = open(base_dir + libcrypto_so_path.replace('/', path_fix), 'rb')
    libcrypto_so_binary = libcrypto_so.read()
    regex = re.compile(openssl_version_search_regex)
    openssl_version = regex.findall(libcrypto_so_binary)
    result['lib_name'] = 'OpenSSL'
    result['lib_avaliable'] = True
    result['lib_path'] = libcrypto_so_path
    result['lib_version'] = openssl_version[0].decode('utf8').replace('OpenSSL ', '')
    return result


if __name__ == '__main__':
    base_dir = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    result = do(base_dir)
    print(result)

