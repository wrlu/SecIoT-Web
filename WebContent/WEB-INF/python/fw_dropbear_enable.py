import platform
import re


risk_name = 'Dropbear开启风险'
risk_description = 'Dropbear是一款实现SSH远程登录的实用工具，开启Dropbear将允许攻击者有机会从远程登录设备。'
risk_level = 'High'
risk_platform = 'Linux'
dropbear_config_file_name = '/etc/config/dropbear'
pwd_auth_search_regex_str = "option\\s+PasswordAuth\\s+'[Oo][Nn]'"
root_pwd_auth_search_regex_str = "option\\s+RootPasswordAuth\\s+'[Oo][Nn]'"


def do(base_dir):
    globals()
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    try:
        dropbear_config_file = open(base_dir + dropbear_config_file_name.replace('/', path_fix), 'r')
        dropbear_config_file_content = dropbear_config_file.read()
        dropbear_config_file_content_lines = dropbear_config_file_content.split('\n')
        pwd_auth_status = False
        root_pwd_auth_status = False
        for line in dropbear_config_file_content_lines:
            pwd_auth_search_regex = re.compile(pwd_auth_search_regex_str)
            pwd_auth_search_found = pwd_auth_search_regex.findall(line)
            if len(pwd_auth_search_found) != 0:
                pwd_auth_status = True
            root_pwd_auth_search_regex = re.compile(root_pwd_auth_search_regex_str)
            root_pwd_auth_search_found = root_pwd_auth_search_regex.findall(line)
            if len(root_pwd_auth_search_found) != 0:
                root_pwd_auth_status = True

        risk_result = {
            'risk_exists': pwd_auth_status or root_pwd_auth_status,
            'risk_name': risk_name,
            'risk_description': risk_description,
            'risk_level': risk_level,
            'risk_platform': risk_platform,
            'risk_details': {
                'pwd_auth': [pwd_auth_status],
                'root_pwd_auth': [root_pwd_auth_status]
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
    base_dir = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root/'
    result = do(base_dir)
    print(result)
