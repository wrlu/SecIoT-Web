from xml.dom import minidom


risk_name = 'iOS应用传输安全风险'
risk_description = 'iOS App Transport Security要求iOS应用必须使用安全的HTTPS协议，如果配置禁用则允许明文传输，可能导致信息泄露。'
risk_level = 'Medium'
risk_platform = 'iOS'


def do(info_plist_file):
    info_plist_content = minidom.parse(info_plist_file)
    root = info_plist_content.documentElement
    dict_elements = root.getElementsByTagName('dict')[0]
    all_elements = dict_elements.getElementsByTagName('*')
    index = 0
    is_allows_arbitrary_loads = False
    has_exception_domains = False
    exception_domains = []
    for element in all_elements:
        if element.nodeName == 'key':
            key_value = element.childNodes[0].nodeValue
            if key_value == 'NSAppTransportSecurity':
                if index + 1 < len(all_elements):
                    next_element = all_elements[index + 1]
                    if next_element.nodeName == 'dict':
                        elements_in_dict = next_element.getElementsByTagName('*')
                        index2 = 0
                        for element_in_dict in elements_in_dict:
                            if element_in_dict.nodeName == 'key':
                                key_value_in_dict = element_in_dict.childNodes[0].nodeValue
                                if key_value_in_dict == 'NSAllowsArbitraryLoads':
                                    if index2 + 1 < len(elements_in_dict):
                                        next_element_in_dict = elements_in_dict[index2 + 1]
                                        if next_element_in_dict.nodeName == 'true':
                                            is_allows_arbitrary_loads = True
                                elif key_value_in_dict == 'NSExceptionDomains':
                                    if index2 + 1 < len(elements_in_dict):
                                        next_element_in_dict = elements_in_dict[index2 + 1]
                                        if next_element_in_dict.nodeName == 'dict':
                                            index3 = 0
                                            elements_in_exception_dict = next_element_in_dict.getElementsByTagName('*')
                                            for element_in_exception_dict in elements_in_exception_dict:
                                                if element_in_exception_dict.nodeName == 'key':
                                                    key_value_in_exception_dict = element_in_exception_dict.childNodes[0].nodeValue
                                                    if key_value_in_exception_dict != 'NSIncludesSubdomains':
                                                        exception_domain = key_value_in_exception_dict
                                                    else:
                                                        index3 = index3 + 1
                                                        continue
                                                    if index3 + 1 < len(elements_in_exception_dict):
                                                        next_element_in_exception_dict = elements_in_exception_dict[index3 + 1]
                                                        if next_element_in_exception_dict.nodeName == 'dict':
                                                            index4 = 0
                                                            elements_in_domain_dict = next_element_in_exception_dict.getElementsByTagName('*')
                                                            for element_in_domain_dict in elements_in_domain_dict:
                                                                if element_in_domain_dict.nodeName == 'key':
                                                                    key_value_in_domain_dict = element_in_domain_dict.childNodes[0].nodeValue
                                                                    if key_value_in_domain_dict == 'NSIncludesSubdomains':
                                                                        if index4 + 1 < len(elements_in_domain_dict):
                                                                            next_element_in_domain_dict = elements_in_domain_dict[index4 + 1]
                                                                            if next_element_in_domain_dict.nodeName == 'true':
                                                                                exception_domain = exception_domain + ' ( IncludesSubdomains ) '
                                                                index4 = index4 + 1
                                                    exception_domains.append(exception_domain)
                                                index3 = index3 + 1
                            index2 = index2 + 1
        index = index + 1
    if len(exception_domains) != 0:
        has_exception_domains = True
    risk_exists = is_allows_arbitrary_loads or has_exception_domains
    risk_details = {
        'NSAllowsArbitraryLoads': ['true'],
        'NSExceptionDomains': exception_domains
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
