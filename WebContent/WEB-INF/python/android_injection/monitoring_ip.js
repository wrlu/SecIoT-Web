setImmediate(function() {
    Java.perform(function () {
        var Socket = Java.use('java.net.Socket');
        Socket.$init.overload('java.net.InetAddress', 'int').implementation = function (ip, port) {
            var target_ip = ip.getHostAddress();
            send('网络连接目标: '+target_ip+', 端口:'+port);
            return this.$init(ip, port);
        }
        Socket.$init.overload('java.lang.String', 'int').implementation = function (ip, port) {
            send('网络连接目标: '+ip+', 端口:'+port);
            return this.$init(ip, port);
        }
        var OkHttpRequest = Java.use('okhttp3.Request');
        OkHttpRequest.url.overload('java.lang.String').implementation = function (u) {
            send('网络连接目标: '+u);
            return this.url(u);
        }
//        OkHttpRequest.url.overload('java.lang.String').implementation = function (u) {
//            send('网络连接目标: '+u);
//            return this.url(u);
//        }
//        OkHttpRequest.url.overload('okhttp3.HttpUrl').implementation = function (u) {
//            send('网络连接目标: '+u);
//            return this.url(u);
//        }
    });
});