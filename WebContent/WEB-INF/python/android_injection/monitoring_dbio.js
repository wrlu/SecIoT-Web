setImmediate(function() {
    Java.perform(function () {
        var SQLiteOpenHelper = Java.use('android.database.sqlite.SQLiteOpenHelper');
        SQLiteOpenHelper.getReadableDatabase.overload().implementation = function () {
            var sqlite_database = this.getReadableDatabase();
            var path = sqlite_database.getPath();
            var version = sqlite_database.getVersion();
            send('[Host'+localhost+' - 数据库I/O检测] 类型: 数据库只读打开(ReadableDatabase), 数据库文件路径: '+path+', 数据库版本: '+version);
            return sqlite_database;
        }
        SQLiteOpenHelper.getWritableDatabase.overload().implementation = function () {
            var sqlite_database = this.getWritableDatabase();
            var path = sqlite_database.getPath();
            var version = sqlite_database.getVersion();
            send('[Host'+localhost+' - 数据库I/O检测] 类型: 数据库读写打开(WritableDatabase), 数据库文件路径: '+path+', 数据库版本: '+version);
            return sqlite_database;
        }
        var SQLiteDatabase = Java.use('android.database.sqlite.SQLiteDatabase');
        SQLiteDatabase.execSQL.overload('java.lang.String').implementation = function (sql) {
            var path = this.getPath();
            var version = this.getVersion();
            send('[Host'+localhost+' - 数据库I/O检测] 类型: 数据库SQL语句执行(execSQL), SQL语句: '+sql+', 数据库文件路径: '+path+', 数据库版本: '+version);
            this.execSQL(sql);
        }
        SQLiteDatabase.execSQL.overload('java.lang.String', '[Ljava.lang.Object;').implementation = function (sql, o) {
            var path = this.getPath();
            var version = this.getVersion();
            send('[Host'+localhost+' - 数据库I/O检测] 类型: 数据库SQL语句执行(execSQL), SQL语句: '+sql+', 数据库文件路径: '+path+', 数据库版本: '+version);
            this.execSQL(sql, o);
        }
    });
});