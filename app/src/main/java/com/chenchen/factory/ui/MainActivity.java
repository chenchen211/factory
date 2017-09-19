package com.chenchen.factory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chenchen.factory.R;
import com.chenchen.factory.alipay.PayActivity;
import com.chenchen.factory.map.navi.CalculateRouteActivity;
import com.chenchen.factory.map.navi.NaviActivity;
import com.chenchen.factory.map.search.SearchActivity;

import net.sourceforge.simcpux.wxapi.WXEntryActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final int SELECT_IMGS = 200;
    private ArrayList<String> imgs=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        button.setText(HelloJni.getPackageName());
    }
    public void alipay(View v){
        changeActivity(PayActivity.class,false);
    }
    public void wechat(View view){
        changeActivity(WXEntryActivity.class,false);
    }
    public void selectImgs(View view){
        MultiImageSelector.create()
                .showCamera(true) // show camera or not. true by default
                .count(6) // max select image size, 9 by default. used width #.multi()
                .multi() // single mode
                .start(this,SELECT_IMGS);
    }

    public void startMap(View view){
        changeActivity(MapActivity.class,false);
    }

    public void showSearch(View view){
        changeActivity(SearchActivity.class,false);
    }
    public void showRoute(View view){
        changeActivity(CalculateRouteActivity.class,false);
    }
    public void showNavi(View view){
        changeActivity(NaviActivity.class,false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_IMGS){
            if(resultCode == RESULT_OK){
                // Get the result list of select image paths
                imgs = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Log.i("images", "onActivityResult: "+ Arrays.toString(imgs.toArray()));
            }
        }
    }
}
