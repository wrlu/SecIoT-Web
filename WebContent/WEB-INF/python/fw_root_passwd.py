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
    can_login_users = []
    for line in passwd_file_content_lines:
        for shell in can_login_shell:
            if shell in line:
                can_login_users.append(line[:line.find(':')])
    result = {
        'can_login_user_num': len(can_login_users),
        'can_login_users': can_login_users
    }
    return result


if __name__ == '__main__':
    base_dir = 'D:\\Analysis\\_mico_all_f86a5_1.44.4.bin.extracted\\squashfs-root'
    result = fw_root_passwd(base_dir)
    print(result)
