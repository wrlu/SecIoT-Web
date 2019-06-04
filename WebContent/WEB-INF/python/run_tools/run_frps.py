import subprocess


def get_frps_version(path):
    version = call_frps_version(path)
    return {
        'version': version
    }


def call_frps_version(path):
    cmd = 'chmod +x ' + path + 'frps; ' + path + 'frps -v'
    print(cmd)
    process = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    result = process.stdout.readline().decode('utf8').replace('\n', '')
    return result


if __name__ == '__main__':
    print(get_frps_version('/mnt/data/WebServer/apache-tomcat-9.0.19/wtpwebapps/SecIoT/WEB-INF/frps-amd64/'))
