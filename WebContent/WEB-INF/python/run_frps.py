import subprocess


def get_frps_version(path):
    version = call_frps_version(path)
    return {
        'version': version
    }


def start_frps_service(path, ini_path):
    pass


def stop_frps_service():
    call_kill_frps()
    return {}


def call_frps_version(path):
    cmd = 'chmod +x ' + path + 'frps; ' + path + 'frps -v'
    print(cmd)
    process = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    result = process.stdout.readline().decode('utf8').replace('\n', '')
    return result


def call_frps(path, ini_path):
    cmd = 'chmod +x ' + path + 'frps; ' + path + 'frps -c ' + ini_path
    print(cmd)
    subprocess.Popen(cmd, shell=True, stdout=open(path + 'access.log', 'rw'), stderr=open(path + 'error.log', 'rw'))


def call_kill_frps():
    cmd = 'ps -aux | grep frps'
    print(cmd)
    subprocess.Popen(cmd, shell=True, stdout=None, stderr=None)


if __name__ == '__main__':
    print(get_frps_version('/mnt/data/WebServer/apache-tomcat-9.0.19/wtpwebapps/SecIoT/WEB-INF/frp/'))
