package com.chenchen.factory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chenchen.factory.MyApp;
import com.chenchen.utils.SPUtils;

import org.xutils.x;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.instances.getmRefWatcher().watch(this);
    }

    protected void changeActivity(Class<?> activity,boolean isFinish){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        if(isFinish){
            finish();
        }
    }
    protected void changeActivity(Intent intent,boolean isFinish){
        startActivity(intent);
        if(isFinish){
            finish();
        }
    }
    protected void saveToken(String token){
        MyApp.instances.setToken(token);
    }
    protected String getToken(){
        return MyApp.instances.getToken();
    }
    protected SPUtils getSP(){
        return MyApp.instances.getSP();
    }
}
