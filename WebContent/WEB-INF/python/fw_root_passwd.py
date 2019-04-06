import platform

can_login_shell = [
    '/bin/bash', '/bin/sh', '/bin/ash'
]


def fw_root_passwd(base_dir):
    global can_login_shell
    passwd_file_name = '/etc/passwd'
    shadow_file_name = '/etc/shadow'
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    passwd_file_name.replace('/', path_fix)
    shadow_file_name.replace('/', path_fix)
    passwd_file = open(base_dir + passwd_file_name, 'r')
    passwd_file_content = passwd_file.read()
    passwd_file_content_lines = passwd_file_content.split('\n')
    user_can_login = []
    for line in passwd_file_content_lines:
        for shell in can_login_shell:
            if shell in line:
                username = line[:line.find(':')]
                user_can_login.append(username)

    shadow_file = open(base_dir + shadow_file_name, 'r')
    shadow_file_content = shadow_file.read()
    shadow_file_content_lines = shadow_file_content.split('\n')
    user_has_no_passwd = []
    user_has_passwd = []
    for line in shadow_file_content_lines:
        username = line[:line.find(':')]
        if username == '':
            continue
        if username not in user_can_login:
            continue
        passwd_hash_len = line.find(':', line.find(':') + 1) - line.find(':') - 1
        if passwd_hash_len > 2:
            user_has_passwd.append(username)
        elif passwd_hash_len == 0:
            user_has_no_passwd.append(username)
    result = {
        'user_avaliable': user_can_login,
        'user_has_no_passwd': user_has_no_passwd,
        'user_has_passwd': user_has_passwd,
    }
    return result


if __name__ == '__main__':
    base_dir = 'D:\\Analysis\\_mico_all_f86a5_1.44.4.bin.extracted\\squashfs-root'
    result = fw_root_passwd(base_dir)
    print(result)
