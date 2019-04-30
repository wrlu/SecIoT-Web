import platform


risk_name = 'crontab计划任务'
risk_description = ''
risk_level = 'Low'
risk_platform = 'Linux'
crontab_folder_name = '/etc/crontabs/'


def do(base_dir):
    globals()
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'


if __name__ == '__main__':
    base_dir = '/mnt/data/Analysis/_mico_all_f86a5_1.44.4.bin.extracted/squashfs-root'
    result = do(base_dir)
    print(result)
