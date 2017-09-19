package com.chenchen.factory.alipay;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.chenchen.factory.R;
import com.chenchen.factory.alipay.uitl.AuthResult;
import com.chenchen.factory.ui.BaseActivity;
import com.chenchen.factory.alipay.uitl.OrderInfoUtil2_0;
import com.chenchen.factory.alipay.uitl.PayResult;

import org.xutils.view.annotation.ContentView;

import java.util.Map;

@ContentView(R.layout.activity_pay)
public class PayActivity extends BaseActivity {

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016090900472985";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDBk9V9YsVj6Olg900snkMEsBbVIksm2dHKlpQ2R/wh39AJA4576KahJfMYOYQnOVP18r6cCi4slLHMPnrXiLbJpO/XnU/uGlqsSQn9M9vJ2hYfWPhbE5iGYIH2EeQ4nDRo/fq9qfcjKgsTPFA5KnkI5bWrDgcnYS5YbucAhQM3mCqtmoaQAOjoLgaez7LtkS8W7GXgMlLjItcp6qcwwy6GcSLb0jygWXacJ/SLkwq5jPnlPC4Y+yKkwHE8sO7I6LYef7BZp3I5mkMGizgPrfy72cJxb0RiDeiZ7oYRQkokdAlMXVMLEDbS99FBomQejOdHagAn1+Fuyz4/d5dDfiGHAgMBAAECggEAUBiIVIpC3HUp46pP729Ika7+s4+uAtRL3wRxLQiaBbYi0Sf8k76O+8zFCjf6O3SYDgMz5UK9GksoSjdsArSZ1VN+vftkcEPNUs4h6PfFMH8hejUJBj5UdP2HqLZ8dQQFF5BHaKTkElO6I2poU08Ep67MfYufYCbw0zyILona6+w9KghWRrJI/cLAkCOBZZFjFoyYDErnsfYdjH0w+yCQ7WIdfALwgowdIvtUX6tz6o1ndEWNysE3RYMKs4aPalaGEAX5vysGt8ETYoEZKT+8eizQonltY569Q16yEr9fiKHV0UaoNEoPvRjaY8zCbQ1i+9evtW3G1XRrdkejpgVpuQKBgQDk0yffkENaBy2uJ7M31msCviXVxePHEMB2mst65Rzgg6GFDZzayk7PdmNAiPEvEqp7ONJ1YHX712+JPVvW2bTqkN0+asGcQEvccodeabpf7V15s3npMdArDAVNcgxFC8lQHBp/80EIcXt7s7Bd9scm2E5cMJhOU5qZJOuj6JEojQKBgQDYkRItHIdmh4t/HbLuH4P388O3NOkp+yGvgT8f+2PhXo3YX6Ve6hP7skcG45pMznm5KTBelraesymme+EgRXiJn+Rq/jZphcsZk01seWSg0dWJgGos1bRygpxtaM9qx9yOUV1ZGjk8fjLExvI4XLK1sEBDw+pVTliZJTRB/5P/YwKBgHJ0wDQOR/InNw0upHFgmgQ0Ml6cblOrKGnrAa7GHUyb6tXPuk6h9QvGrtyf4uvlzUp3YKX/iuRenVarT13HZbwSm+fVpGRQsOFilB2Aiw3if+gZAvjGxtZRW/GiA0siGx8r/4FSnovnn5l/hs9pWbIvbfqk//nlzaWelxNUiA8dAoGANXhUCYlSsPDkc0Us6XgkiDLKbtCEhRX5MjDyGf5iocFLulGbLf0TZACXYs8NSMtt6FaQF7qUakiIDw/k/KfKZAhzSCtt2c3r4GurIQ4z1zUXyPRIwrsc62zuHGFVXOLtxMG4D4jW+uWZuVX5dgsFILDcnR6hG+DVJTK3XVgx/UsCgYEAuYKpDSrBzAVSA48WPH2vwrQe+zNX0mT0mfeeKoXnf4BuWREYQN3NJuZZOeRhZXk2mZGu8tZIevC+4pXUFz7XZwhEuqI7rVxcX/IHUZYWT4m16lkx8sKae01SEPnid5wQFAyLaOd2lozg19r/xn21/KaHAOK8ydXJPDstZVePPTI=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PayActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
    }

    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务
     *
     * @param v
     */
    public void authV2(View v) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(PayActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(this, H5PayActivity.class);
        Bundle extras = new Bundle();
        /**
         * url 是要测试的网站，在 Demo App 中会使用 H5PayDemoActivity 内的 WebView 打开。
         *
         * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
         * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
         * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
         * 进行测试。
         *
         * H5PayDemoActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
         * 可以参考它实现自定义的 URL 拦截逻辑。
         */
        String url = "http://m.taobao.com";
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
