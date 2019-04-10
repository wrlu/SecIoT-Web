from xml.dom import minidom


def exported(manifest_file):
    manifest_file_content = minidom.parse(manifest_file)
    root = manifest_file_content.documentElement
    application_element = root.getElementsByTagName('application')[0]
    permissions = []

    return permissions


if __name__ == '__main__':
    manifest_file = 'D:\\Analysis\\com.huawei.ipc.apk.jadx.out\\resources\\AndroidManifest.xml'
    exported_contexts = exported(manifest_file)
    print(exported_contexts)
