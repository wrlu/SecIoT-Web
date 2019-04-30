from xml.dom import minidom


risk_name = 'Android组件暴露风险'
risk_description = 'App在AndroidManifest.xml中没有正确设置四大组件的权限，暴露不必要的组件可能导致隐私信息泄漏给第三方App。'
risk_level = 'Medium'
risk_platform = 'Android'


def do(manifest_file):
    manifest_file_content = minidom.parse(manifest_file)
    root = manifest_file_content.documentElement
    application_element = root.getElementsByTagName('application')[0]
    allow_backup = application_element.getAttribute('android:allowBackup')
    print(allow_backup)
    activity_elements = application_element.getElementsByTagName('activity')
    service_elements = application_element.getElementsByTagName('service')
    broadcast_receiver_elements = application_element.getElementsByTagName('receiver')
    content_provider_elements = application_element.getElementsByTagName('provider')
    exported_activity = []
    exported_service = []
    exported_broadcast_receiver = []
    exported_content_provider = []
    for activity_element in activity_elements:
        if activity_element.getAttribute('android:exported') == 'true':
            exported_activity.append(activity_element.getAttribute('android:name'))
        else:
            if activity_element.getElementsByTagName('intent-filter'):
                exported_activity.append(activity_element.getAttribute('android:name'))
    for service_element in service_elements:
        if service_element.getAttribute('android:exported') == 'true':
            exported_service.append(service_element.getAttribute('android:name'))
        else:
            if service_element.getElementsByTagName('intent-filter'):
                exported_service.append(service_element.getAttribute('android:name'))
    for broadcast_receiver_element in broadcast_receiver_elements:
        if broadcast_receiver_element.getAttribute('android:exported') == 'true':
            exported_broadcast_receiver.append(broadcast_receiver_element.getAttribute('android:name'))
        else:
            if broadcast_receiver_element.getElementsByTagName('intent-filter'):
                exported_broadcast_receiver.append(broadcast_receiver_element.getAttribute('android:name'))
    for content_provider_element in content_provider_elements:
        if content_provider_element.getAttribute('android:exported') == 'true':
            exported_content_provider.append(content_provider_element.getAttribute('android:name'))
        else:
            if content_provider_element.getElementsByTagName('intent-filter'):
                exported_content_provider.append(content_provider_element.getAttribute('android:name'))
    if len(exported_activity) != 0 or len(exported_service) != 0 or len(exported_broadcast_receiver) != 0 or len(exported_content_provider) != 0:
        risk_exists = True
    else:
        risk_exists = False
    risk_details = {
        'activity': exported_activity,
        'service': exported_service,
        'broadcast_receiver': exported_broadcast_receiver,
        'content_provider': exported_content_provider
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
    manifest_file = '/mnt/data/Analysis/com.huawei.ipc.apk.jadx.out/resources/AndroidManifest.xml'
    exported_contexts = do(manifest_file)
    print(exported_contexts)
