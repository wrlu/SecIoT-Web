setImmediate(function() {
    Java.perform(function () {
        var Object = Java.use('java.lang.Object');
        Object.equals.implementation = function (o) {
            send('Hooking equals method');
            return this.equals(o);
        }
    });
});