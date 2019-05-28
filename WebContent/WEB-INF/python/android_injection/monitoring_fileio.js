setImmediate(function() {
    Java.perform(function () {
        var FileInputStream = Java.use('java.io.FileInputStream');
        FileInputStream.$init.overload('java.io.File').implementation = function (file) {
            var file_name = file.getName();
            var file_path = file.getAbsolutePath();
            send('[文件I/O检测] 类型: 文件读取(FileInputStream), 文件名: '+file_name+', 文件完整路径: '+file_path);
            return this.$init(file);
        }
        FileInputStream.$init.overload('java.lang.String').implementation = function (name) {
            send('[文件I/O检测] 类型: 文件读取(FileInputStream), 文件相对路径: '+name);
            return this.$init(name);
        }
        var FileOutputStream = Java.use('java.io.FileOutputStream');
        FileOutputStream.$init.overload('java.io.File').implementation = function (file) {
            var file_name = file.getName();
            var file_path = file.getAbsolutePath();
            send('[文件I/O检测] 类型: 文件写入(FileOutputStream), 文件名: '+file_name+', 文件完整路径: '+file_path);
            return this.$init(file);
        }
        FileOutputStream.$init.overload('java.lang.String').implementation = function (name) {
            send('[文件I/O检测] 类型: 文件写入(FileOutputStream), 文件相对路径: '+name);
            return this.$init(name);
        }
        FileOutputStream.$init.overload('java.io.File', 'boolean').implementation = function (file, append) {
            var file_name = file.getName();
            var file_path = file.getAbsolutePath();
            send('[文件I/O检测] 类型: 文件写入(FileOutputStream), 文件名: '+file_name+', 文件完整路径: '+file_path);
            return this.$init(file, append);
        }
        FileOutputStream.$init.overload('java.lang.String', 'boolean').implementation = function (name, append) {
            send('[文件I/O检测] 类型: 文件写入(FileOutputStream), 文件相对路径: '+name);
            return this.$init(name, append);
        }
        var FileReader = Java.use('java.io.FileReader');
        FileReader.$init.overload('java.io.File').implementation = function (file) {
            var file_name = file.getName();
            var file_path = file.getAbsolutePath();
            send('[文件I/O检测] 类型: 文件读取(FileReader), 文件名: '+file_name+', 文件完整路径: '+file_path);
            return this.$init(file);
        }
        FileReader.$init.overload('java.lang.String').implementation = function (name) {
            send('[文件I/O检测] 类型: 文件读取(FileReader), 文件相对路径: '+name);
            return this.$init(name);
        }
        var FileWriter = Java.use('java.io.FileWriter');
        FileWriter.$init.overload('java.io.File').implementation = function (file) {
            var file_name = file.getName();
            var file_path = file.getAbsolutePath();
            send('[文件I/O检测] 类型: 文件写入(FileWriter), 文件名: '+file_name+', 文件完整路径: '+file_path);
            return this.$init(file);
        }
        FileWriter.$init.overload('java.lang.String').implementation = function (name) {
            send('[文件I/O检测] 类型: 文件写入(FileWriter), 文件相对路径: '+name);
            return this.$init(name);
        }
        FileWriter.$init.overload('java.io.File', 'boolean').implementation = function (file, append) {
            var file_name = file.getName();
            var file_path = file.getAbsolutePath();
            send('[文件I/O检测] 类型: 文件写入(FileWriter), 文件名: '+file_name+', 文件完整路径: '+file_path);
            return this.$init(file, append);
        }
        FileWriter.$init.overload('java.lang.String', 'boolean').implementation = function (name, append) {
            send('[文件I/O检测] 类型: 文件写入(FileWriter), 文件相对路径: '+name);
            return this.$init(name, append);
        }
    });
});