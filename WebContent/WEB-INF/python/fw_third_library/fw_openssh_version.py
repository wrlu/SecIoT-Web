import os
import re
import platform


openssh_names = [
    '/sbin/ssh',
    '/usr/sbin/ssh',
    '/bin/ssh',
    '/usr/bin/ssh',
    '/usr/local/bin/ssh'
]
openssh_version_search_regex = b'OpenSSH_[0-9].[0-9].?[0-9]*p?[0-9]?'
dropbear_version_search_regex = b'Dropbear SSH'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    openssh_path = ''
    for name in openssh_names:
        if os.path.exists(base_dir + name.replace('/', path_fix)):
            openssh_path = name

    if openssh_path == '':
        return {
            'lib_avaliable': False
        }
    openssh_exec = open(base_dir + openssh_path.replace('/', path_fix), 'rb')
    openssh_exec_binary = openssh_exec.read()
    openssh_exec.close()
    regex = re.compile(openssh_version_search_regex)
    openssh_version = regex.findall(openssh_exec_binary)
    if len(openssh_version) != 0:
        result = {
            'lib_name': 'Dropbear',
            'lib_avaliable': True,
            'lib_path': openssh_path,
            'lib_version': openssh_version[0].decode('utf8').replace('OpenSSH_', '')
        }
    else:
        d_regex = re.compile(dropbear_version_search_regex)
        dropbear_version = d_regex.findall(openssh_exec_binary)
        if len(dropbear_version) != 0:
            return {
                'lib_avaliable': False
            }
        else:
            result = {
                'lib_name': 'Dropbear',
                'lib_avaliable': True,
                'lib_path': openssh_path,
                'lib_version': 'Unknown'
            }
    return result


if __name__ == '__main__':
    d = '/'
    # d = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    r = do(d)
    print(r)

