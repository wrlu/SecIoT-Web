from xml.dom import minidom


def do(manifest_file):
    manifest_content = minidom.parse(manifest_file)
    root = manifest_content.documentElement
    permission_elements = root.getElementsByTagName('uses-permission')
    permissions = []
    for element in permission_elements:
        permissions.append(element.getAttribute('android:name'))
    result = {
        'permission': permissions
    }
    return result


if __name__ == '__main__':
    f = '/mnt/data/Analysis/com.huawei.ipc.apk.jadx.out/resources/AndroidManifest.xml'
    p = do(f)
    print(p)
