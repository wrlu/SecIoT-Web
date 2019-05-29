setImmediate(function() {
    Java.perform(function () {
        var ActivityCompat = Java.use('android.support.v4.app.ActivityCompat');
        ActivityCompat.checkSelfPermission.overload('android.content.Context', 'java.lang.String').implementation = function(context, p) {
            var res = ActivityCompat.checkSelfPermission(context, p);
            var res_str = '拒绝';
            if (res == 0) {
                res_str = 'PERMISSION_GRANTED (权限授予)'
            } else if (res == -1) {
                res_str = 'PERMISSION_DENIED (权限拒绝)'
            }
            send('[Host'+localhost+' - API调用检测] 操作: 动态权限检查(API >= 23), 权限名称: '+p+', 检查对象: '+context+', 检查结果: '+res_str);
            return res;
        }
        ActivityCompat.requestPermissions.overload('android.app.Activity', '[Ljava.lang.String;', 'int').implementation = function(activity, ps, i) {
            send('[Host'+localhost+' - API调用检测] 操作: 动态权限申请(API >= 23), 权限名称: '+ps+', 申请对象: '+activity);
            ActivityCompat.requestPermissions(activity, ps, i)
        }
    });
});