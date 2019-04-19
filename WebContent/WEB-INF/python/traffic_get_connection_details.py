from scapy.all import *
from scapy.layers.inet import *


def do(pcap_file_name, ip_addr):
    pkgs = rdpcap(pcap_file_name)
    pair_hosts = []
    pair_connection_protocol = []
    for pkg in pkgs:
        pair_host, pair_protocol = get_connection_details(pkg, ip_addr)
        if pair_host != '' and pair_host not in pair_hosts:
            pair_hosts.append(pair_host)
            pair_connection_protocol.append(pair_protocol)
    result = {
        'pair_hosts': pair_hosts,
        'pair_connection_protocol': pair_connection_protocol
    }
    return result


def get_connection_details(pkg, ip_addr):
    # 使用此方法只能判断使用标准熟知端口的数据包
    proto_names = ['http', 'https', 'ftp', 'dns', 'TCP', 'UDP', 'ICMP', 'IPv6', 'IP', 'ARP', 'Ether']
    proto = ''
    for pn in proto_names:
        if pn in pkg.summary():
            proto = pn
            break
    host = ''
    if 'IPv6' in pkg.summary():
        src = pkg[IPv6].src
        dst = pkg[IPv6].dst
    elif 'IP' in pkg.summary():
        src = pkg[IP].src
        dst = pkg[IP].dst
    else:
        return '', ''
    if 'TCP' in pkg.summary() or 'UDP' in pkg.summary():
        if src == ip_addr:
            host = dst + ':' + str(pkg.dport)
        elif dst == ip_addr:
            host = src + ':' + str(pkg.sport)
    else:
        if src == ip_addr:
            host = dst
        elif dst == ip_addr:
            host = src
    return host, proto


if __name__ == '__main__':
    pcap_file_name = '/mnt/data/Analysis/HUAWEI-Q2-9.0.2.9-9.0.2.9patch01-Update.cap'
    ip_addr = '192.168.8.112'
    result = do(pcap_file_name, ip_addr)
    print(result)
