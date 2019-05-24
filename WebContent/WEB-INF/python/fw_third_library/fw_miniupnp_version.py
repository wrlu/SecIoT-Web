import os
import re
import platform


libminiupnp_so_names = [

]
libminiupnp_version_search_regex = b''


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    libminiupnp_so_path = ''
    for name in libminiupnp_so_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            libminiupnp_so_path = name

    if libminiupnp_so_path == '':
        return {
            'lib_avaliable': False
        }
    libminiupnp_so = open(base_dir + libminiupnp_so_path.replace('/', path_fix), 'rb')
    libminiupnp_so_binary = libminiupnp_so.read()
    libminiupnp_so.close()
    regex = re.compile(libminiupnp_version_search_regex)
    miniupnp_version = regex.findall(libminiupnp_so_binary)
    if len(miniupnp_version) != 0:
        result = {
            'lib_name': 'MiniUPnP',
            'lib_avaliable': True,
            'lib_path': libminiupnp_so_path,
            'lib_version': miniupnp_version[0].decode('utf8').replace(' Copyright', '')
        }
    else:
        result = {
            'lib_name': 'MiniUPnP',
            'lib_avaliable': True,
            'lib_path': libminiupnp_so_path,
            'lib_version': 'Unknown'
        }
    return result


if __name__ == '__main__':
    d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

