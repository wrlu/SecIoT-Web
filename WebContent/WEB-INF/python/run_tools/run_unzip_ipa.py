import zipfile
import platform
import os


def get_ipa_info(file_name, path):
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    ipa_out_directory = path + '.out' + path_fix
    call_unzip(path, ipa_out_directory)
    ipa_payload_path = ipa_out_directory + 'Payload' + path_fix
    ipa_content_path = os.listdir(ipa_payload_path)[0]
    ipa_source_file = ipa_payload_path + ipa_content_path + path_fix + ipa_content_path[:-4]
    ipa_info_plist_file = ipa_payload_path + ipa_content_path + path_fix + 'Info.plist'
    ipa_info = {
        'ipa_name': file_name,
        'ipa_path': path,
        'ipa_source_file': ipa_source_file,
        'ipa_info_plist_file': ipa_info_plist_file
    }
    return ipa_info


def call_unzip(path, target):
    ipa_file = zipfile.ZipFile(path, 'r')
    for content in ipa_file.filelist:
        ipa_file.extract(content, target)


if __name__ == '__main__':
    p = '/mnt/data/Analysis/com.huawei.ipchuawei.ipa'
    n = 'com.huawei.ipchuawei.ipa'
    info = get_ipa_info(n, p)
    print(info)
