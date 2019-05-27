setImmediate(function() {
    Java.perform(function () {
        var Socket = Java.use('java.net.Socket');
        Socket.$init.overload('java.net.InetAddress', 'int').implementation = function (ip, port) {
            var target_ip = ip.getHostAddress();
            send('[网络连接检测] 协议: TCP, 目标: '+target_ip+', 端口:'+port);
            return this.$init(ip, port);
        }
        Socket.$init.overload('java.lang.String', 'int').implementation = function (ip, port) {
            send('[网络连接检测] 协议: TCP, 目标: '+ip+', 端口: '+port);
            return this.$init(ip, port);
        }
        var DatagramPacket = Java.use('java.net.DatagramPacket');
        DatagramPacket.$init.overload('[B', 'int', 'java.net.InetAddress', 'int').implementation = function (buf, len, ip, port) {
            var target_ip = ip.getHostAddress();
            send('[网络连接检测] 协议: UDP, 目标: '+ip+', 端口: '+port);
            return this.$init(buf, len, ip, port);
        }
        DatagramPacket.$init.overload('[B', 'int', 'int', 'java.net.InetAddress', 'int').implementation = function (buf, len, offset, ip, port) {
            var target_ip = ip.getHostAddress();
            send('[网络连接检测] 协议: UDP, 目标: '+ip+', 端口: '+port);
            return this.$init(buf, len, offset, ip, port);
        }
        var OkHttpRequest = Java.use('okhttp3.Request$Builder');
        OkHttpRequest.url.overload('java.lang.String').implementation = function (u) {
            var url_array = u.split('?');
            var host = url_array[0];
            var param = url_array[1].replace(/&/g, ", ");
            send('[网络连接检测] 协议: HTTP/HTTPS, 目标: '+host+", 参数: "+param);
            return this.url(u);
        }
        OkHttpRequest.url.overload('okhttp3.HttpUrl').implementation = function (hu) {
            var u = hu.toString();
            var url_array = u.split('?');
            var host = url_array[0];
            var param = url_array[1].replace(/&/g, ", ");
            send('[网络连接检测] 协议: HTTP/HTTPS, 目标: '+host+", 参数: "+param);
            return this.url(hu);
        }
    });
});