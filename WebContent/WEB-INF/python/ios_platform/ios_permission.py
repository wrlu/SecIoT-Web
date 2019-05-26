from xml.dom import minidom


def do(info_plist_file):
    info_plist_content = minidom.parse(info_plist_file)
    root = info_plist_content.documentElement
    dict_elements = root.getElementsByTagName('dict')[0]
    all_elements = dict_elements.getElementsByTagName('*')
    permissions = []
    index = 0
    for element in all_elements:
        if element.nodeName == 'key':
            key_value = element.childNodes[0].nodeValue
            if key_value.endswith('UsageDescription'):
                permission = key_value.replace('UsageDescription', '')
                if index + 1 < len(all_elements):
                    next_element = all_elements[index + 1]
                    if next_element.nodeName == 'string':
                        string_value = next_element.childNodes[0].nodeValue
                        permission = permission + ' ( UsageDescription: '+string_value+' )'
                permissions.append(permission)
        index = index + 1

    result = {
        'permission': permissions
    }
    return result


if __name__ == '__main__':
    f = '/mnt/data/Analysis/com.huawei.ipchuawei.ipa.out/Payload/HuaweiVideo.app/Info.plist'
    p = do(f)
    print(p)
