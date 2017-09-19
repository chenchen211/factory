# factory
集成了百度云推送、支付宝支付、微信支付、retrofit2.0网络框架、xutils3、greendao、MPAndroidChart图表等常用框架，可下载修改直接使用，减少集成时间。
本例还封装了常用的工具类、常用加密方法等。
--------
# 1、MPAndroidChart图表
https://github.com/PhilJay/MPAndroidChart

# 2、GreenDao数据库
https://github.com/greenrobot/greenDAO

提供一行代码升级数据库，如下：
```
MigrationHelper.getInstance().migrate(db,SearchDao.class);
```
第二个参数是可变数组，只需要将修改后的类的Dao设置即可

# 3、retrofit网络框架
https://github.com/square/retrofit

结合MVP模式和AndroidStudio的MVPHealper插件，很好用的框架。

封装了两个基本方法doGET、doPOST，提供基本的get、post请求，其他根据需要自己修改HttpService接口。

```
    HttpService service = HttpHelper.getInstance().getService();
    service.doGET(url).enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    });
```

HttpHelper类已经实现本地化Cookie、网络日志、GsonConverter，提供Base64ConverterFactory,可以将Base64加密后的json数据解析为JavaBean。
# 4、xutils3
https://github.com/wyouflf/xUtils3

# 5、Picasso图片加载框架
https://github.com/square/picasso

# 6、MultiImageSelector图片选择器
https://github.com/lovetuzitong/MultiImageSelector

# 7、支付宝支付
按照官方demo集成的，参考官方文档即可。
https://docs.open.alipay.com/204/105296/
# 8、微信支付
微信支付坑比较多，而且微信的文档真是垃圾，本来很简单的玩意，折腾了一天。
集成步骤见https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5

注：
1、net.sourceforge.simcpux.wxapi包路径中实现WXPayEntryActivity类,包名或类名一定要一致。

2、`builde.gradle`构建时因为.9图片不合格一致报错，懒的去修复.9图了，所有加上了
```
android{
    aaptOptions.cruncherEnabled = false
    aaptOptions.useNewCruncher = false
}
```
如果自己修改过.9图片的话可以将这两行去掉。

3、`builde.gradle`中添加依赖
```
compile 'com.tencent.mm.opensdk:wechat-sdk-android-with-mta:+'
```
# 9、passcodeview密码输入视图
https://github.com/hanks-zyh/PasscodeView

# 10、百度云推送
http://push.baidu.com/doc/android/api

在ui.receiver包下的PushReceiver中处理推送消息即可。
