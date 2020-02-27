import platform


risk_name = 'Linux用户帐户风险'
risk_description = '固件中存在可登录的Linux用户，可能导致攻击者从本地接口或远程方式取得系统权限。'
risk_level = 'Low'
risk_platform = 'Linux'
can_login_shell = [
    '/bin/bash', '/bin/sh', '/bin/ash'
]
passwd_file_name = '/etc/passwd'
shadow_file_name = '/etc/shadow'


def do(base_dir):
    globals()
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    try:
        passwd_file = open(base_dir + passwd_file_name.replace('/', path_fix), 'r')
        passwd_file_content = passwd_file.read()
        passwd_file_content_lines = passwd_file_content.split('\n')
        passwd_file.close()
        user_can_login = []
        for line in passwd_file_content_lines:
            for shell in can_login_shell:
                if shell in line:
                    username = line[:line.find(':')]
                    user_can_login.append(username)

        shadow_file = open(base_dir + shadow_file_name.replace('/', path_fix), 'r')
        shadow_file_content = shadow_file.read()
        shadow_file_content_lines = shadow_file_content.split('\n')
        shadow_file.close()
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
        risk_details = {
            'user_avaliable': user_can_login,
            'user_has_no_passwd': user_has_no_passwd,
            'user_has_passwd': user_has_passwd,
        }
        if len(user_can_login) != 0:
            risk_exists = True
        else:
            risk_exists = False
        risk_result = {
            'risk_exists': risk_exists,
            'risk_name': risk_name,
            'risk_description': risk_description,
            'risk_level': risk_level,
            'risk_platform': risk_platform,
            'risk_details': risk_details
        }
        return risk_result
    except Exception as e:
        print(e)
        risk_result = {
            'risk_exists': False,
            'risk_name': risk_name,
            'risk_description': risk_description,
            'risk_level': risk_level,
            'risk_platform': risk_platform,
            'risk_details': {}
        }
        return risk_result


if __name__ == '__main__':
    base_dir = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    result = do(base_dir)
    print(result)
