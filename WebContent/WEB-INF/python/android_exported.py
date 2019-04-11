from xml.dom import minidom


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
    exported_contexts = {
        'exported_activity': exported_activity,
        'exported_service': exported_service,
        'exported_broadcast_receiver': exported_broadcast_receiver,
        'exported_content_provider': exported_content_provider
    }
    return exported_contexts


if __name__ == '__main__':
    manifest_file = 'D:\\Analysis\\com.huawei.ipc.apk.jadx.out\\resources\\AndroidManifest.xml'
    exported_contexts = do(manifest_file)
    print(exported_contexts)
