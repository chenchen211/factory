package com.chenchen.factory.ndk;

/**
 * Created by Administrator on 2017/9/8.
 */

public class HelloJni {
    static {
        System.loadLibrary("chenchen-factory");
    }

    public static native String getPackageName();
}
