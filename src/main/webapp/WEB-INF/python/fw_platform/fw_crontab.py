import platform
import os

risk_name = 'Crontab计划任务'
risk_description = 'Crontab是Linux中的计划任务程序，在Crontab中设置的不恰当的项目可能意味着系统存在后门。'
risk_level = 'Low'
risk_platform = 'Linux'
crontab_folder_name = '/etc/crontabs/'


def do(base_dir):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    try:
        crontab_file_list = os.listdir(base_dir + crontab_folder_name.replace('/', path_fix))
        risk_details = {}
        risk_exists = False
        for crontab_file_name in crontab_file_list:
            crontab_file = open(base_dir + crontab_folder_name.replace('/', path_fix) + crontab_file_name, 'r')
            crontab_file_content = crontab_file.read()
            if crontab_file_content == '':
                continue
            crontab_file_content_lines = crontab_file_content.split('\n')
            risk_details[crontab_file_name] = []
            for line in crontab_file_content_lines:
                risk_details[crontab_file_name].append(line)
            crontab_file.close()
        if risk_details != {}:
            risk_exists = True
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
