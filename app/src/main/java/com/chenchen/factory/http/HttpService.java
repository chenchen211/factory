package com.chenchen.factory.http;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

import static android.R.attr.path;

/**
 * Created by Administrator on 2017/7/24.
 * Api接口
 */

public interface HttpService {

    String BASE_URL = "http://192.168.0.113:8888/api/";

    @GET()
    Call<ResponseBody> doGET(@Url String url);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> doPost(@Url String url,@FieldMap Map<String,String> map);

    /**
     * 登录
     * @return 登录结果
     */
    @FormUrlEncoded
    @POST("login/index")
    Call<ResponseBody> login(@FieldMap Map<String, String> map);

    @GET("product/index/page/{page}")
    Call<ResponseBody> getGoodsList(@Path("page") int page);

    /**
     * 上传图片
     * @param partList  MultipartBody.Part
     * @return 上传结果
     */
    @Multipart
    @POST("upload/index")
    Call<ResponseBody> upload(@Part List<MultipartBody.Part> partList);

}
