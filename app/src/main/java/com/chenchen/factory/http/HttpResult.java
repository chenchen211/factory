package com.chenchen.factory.http;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/25.
 */

public abstract class HttpResult<T> implements Callback<T> {

    private static final String TAG = "HttpResult";
    private int count;

    public abstract void response(Call<T> call,T t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        count=0;
        Log.i(TAG, "onResponse: "+response.message());
        T t = response.body();
        if(null != t){
            response(call,t);
        }else{
            onFailure(call,new Exception("无法解析的响应数据"));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(count < 3){
            call.enqueue(this);
            count++;
        }
        Log.i(TAG, "onFailure: 重试次数="+count+";\n错误信息："+t.getMessage());
    }
}
