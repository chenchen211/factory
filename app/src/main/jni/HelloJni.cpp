//
// Created by Administrator on 2017/9/8.
//

#include <com_chenchen_factory_ndk_HelloJni.h>

JNIEXPORT jstring JNICALL Java_com_chenchen_factory_ndk_HelloJni_getPackageName
        (JNIEnv *env, jclass){
    return env->NewStringUTF("FromC");
}