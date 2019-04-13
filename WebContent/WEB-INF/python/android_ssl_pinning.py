import os
import re
import platform


risk_name = 'Android SSL弱校验风险'
risk_description = ''
risk_level = 'High'
risk_platform = 'Android'
search_regex_strs = {
    'checkServerTrusted': 'public\\s+void\\s+checkServerTrusted\\s*\\('
                          '\\s*X509Certificate\\s*\\[\\]\\s*[a-z,A-Z,_][a-z,A-Z,0-9,_]*\\s*,'
                          '\\s*String\\s+[a-z,A-Z,_][a-z,A-Z,0-9,_]*\\s*\\)',
    'checkClientTrusted': 'public\\s+void\\s+checkClientTrusted\\s*\\('
                          '\\s*X509Certificate\\s*\\[\\]\\s*[a-z,A-Z,_][a-z,A-Z,0-9,_]*\\s*,'
                          '\\s*String\\s+[a-z,A-Z,_][a-z,A-Z,0-9,_]*\\s*\\)'
}


def list_all_classes(root_dir, dirname, suffix):
    suffix_len = len(suffix)
    file_list = os.listdir(dirname)
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    classes = []
    for filename in file_list:
        if os.path.isdir(dirname + path_fix + filename):
            if filename.startswith('android'):
                continue
            classes_in_dir = list_all_classes(root_dir, dirname + path_fix + filename, suffix)
            for clazz in classes_in_dir:
                classes.append(clazz)
        else:
            if filename.endswith(suffix):
                writeable_packagename = dirname.replace(root_dir + path_fix, '').replace(path_fix, '.')
                writeable_filename = filename[:-suffix_len]
                classes.append(writeable_packagename + '.' + writeable_filename)
    return classes


def check_ssl_pinning():
    pass


def do(sources_dir):
    classes_list = list_all_classes(sources_dir, sources_dir, '.java')
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    risk_details = {
        'checkServerTrusted': [],
        'checkClientTrusted': []
    }
    for clazz in classes_list:
        class_file = open(sources_dir + path_fix + clazz.replace('.', path_fix) + '.java', 'rb')
        class_file_data = class_file.read()
        class_file_content = class_file_data.decode('utf8')
        for key in search_regex_strs:
            regex = re.compile(search_regex_strs[key])
            found = regex.findall(class_file_content)
            if len(found) != 0:
                risk_details[key].append(clazz)
    risk_exists = False
    for key in risk_details:
        if len(risk_details[key]) != 0:
            risk_exists = True
            break
    risk_result = {
        'risk_exists': risk_exists,
        'risk_name': risk_name,
        'risk_description': risk_description,
        'risk_level': risk_level,
        'risk_platform': risk_platform,
        'risk_detail_keys': ['checkServerTrusted', 'checkClientTrusted'],
        'risk_details': risk_details
    }
    return risk_result

#   @JsonProperty("risk_exists")
#   private boolean exists;
#   @JsonProperty("risk_name")
#   private String name;
#   @JsonProperty("risk_description")
#   private String description;
#   @JsonProperty("risk_level")
#   private String level;
#   @JsonProperty("risk_platform")
#   private String platform;
#   @JsonProperty("risk_detail_keys")
#   private String[] detailKeys;
#   @JsonProperty("risk_details")


if __name__ == '__main__':
    sources_folder = 'D:\\Analysis\\com.huawei.ipc.apk.jadx.out\\sources'
    result = do(sources_folder)
    print(result)
