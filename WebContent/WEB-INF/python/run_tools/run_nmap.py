import platform
import subprocess


def port_scan(target):
    result = call_nmap(target, '-v')
    if result == '':
        raise OSError('nmap runs failed.')
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    return result


def all_scan(target):
    result = call_nmap(target, '-A')
    if result == '':
        raise OSError('nmap runs failed.')
    if platform.system() == 'Windows':
        path_fix = '\\'
    else:
        path_fix = '/'
    return result


def call_nmap(target, params):
    cmd = 'nmap ' + params + ' ' + target
    print(cmd)
    process = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    result = ''
    for line in process.stdout.readlines():
        result += line.decode('utf8')
        print(line.decode('utf8').replace('\n', ''))
    return result


if __name__ == '__main__':
    target = '127.0.0.1'
    r1 = port_scan(target)
    r2 = all_scan(target)
