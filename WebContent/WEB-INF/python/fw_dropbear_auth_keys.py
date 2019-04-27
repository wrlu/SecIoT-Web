import platform


risk_name = 'Dropbear配置公钥风险'
risk_description = ''
risk_level = 'Medium'
risk_platform = 'Linux'
dropbear_auth_keys_file_name = '/etc/dropbear/authorized_keys'


def do(base_dir):
    globals()
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    dropbear_auth_keys_file_name.replace('/', path_fix)
    try:
        dropbear_auth_keys_file = open(base_dir + dropbear_auth_keys_file_name, 'r')
        dropbear_auth_keys_file_content = dropbear_auth_keys_file.read()
        dropbear_auth_keys_file_content_lines = dropbear_auth_keys_file_content.split('\n')
        risk_result = {
            'risk_exists': True,
            'risk_name': risk_name,
            'risk_description': risk_description,
            'risk_level': risk_level,
            'risk_platform': risk_platform,
            'risk_details': {
                'auth_keys': dropbear_auth_keys_file_content_lines
            }
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
    base_dir = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root'
    result = do(base_dir)
    print(result)
