package com.chenchen.factory.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.chenchen.factory.R;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MapUtils {
    private static MapUtils instance;
    private AMap aMap;
    private Context context;
    private MapUtils(AMap aMap,Context context) {
        this.context = context;
        this.aMap = aMap;
    }

    public static MapUtils getInstance(AMap aMap, Context context) {
        if (instance == null) {
            instance = new MapUtils(aMap,context);
        }
        return instance;
    }

    public void addMaker(){
        //绘制marker
        Marker marker = aMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.986919,116.353369))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(context.getResources(), R.drawable.poi_marker_pressed)))
                .draggable(true));

        // 绘制曲线
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(43.828, 87.621), new LatLng(45.808, 126.55));
        polylineOptions.geodesic(true).color(Color.RED);
        aMap.addPolyline(polylineOptions);
    }
}
