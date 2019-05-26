from xml.dom import minidom


risk_name = 'iOS后台使用'
risk_description = 'iOS允许应用程序在后台使用GPS定位，持续跟踪位置并使应用即使在锁屏后依旧可以在后台运行。'
risk_level = 'Low'
risk_platform = 'iOS'


def do(info_plist_file):
    info_plist_content = minidom.parse(info_plist_file)
    root = info_plist_content.documentElement
    dict_elements = root.getElementsByTagName('dict')[0]
    all_elements = dict_elements.getElementsByTagName('*')
    index = 0
    risk_exists = False
    background_modes = []
    for element in all_elements:
        if element.nodeName == 'key':
            key_value = element.childNodes[0].nodeValue
            if key_value == 'UIBackgroundModes':
                if index + 1 < len(all_elements):
                    next_element = all_elements[index + 1]
                    if next_element.nodeName == 'array':
                        string_elements = next_element.getElementsByTagName('string')
                        for string_element in string_elements:
                            if len(string_element.childNodes) != 0:
                                risk_exists = True
                                background_modes.append(string_element.childNodes[0].nodeValue)
        index = index + 1
    risk_details = {
        'background_modes': background_modes
    }
    risk_result = {
        'risk_exists': risk_exists,
        'risk_name': risk_name,
        'risk_description': risk_description,
        'risk_level': risk_level,
        'risk_platform': risk_platform,
        'risk_details': risk_details
    }
    return risk_result


if __name__ == '__main__':
    f = '/mnt/data/Analysis/com.huawei.ipchuawei.ipa.out/Payload/HuaweiVideo.app/Info.plist'
    p = do(f)
    print(p)
