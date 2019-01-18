package com.bw.shen.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018-12-30.
 */

public class RetrofitManager {
    //http://172.17.8.100/small/commodity/v1/commodityList

    //网址
    public static final  String url="http://mobile.bwstudent.com/small/";
    private Retrofit retrofit;
    //单例模式
    public  static  final  class  SINGLE_INSTACE{
        public static final RetrofitManager _INSTANCE=new RetrofitManager();
    }
    //方法请求
    public static  RetrofitManager getinstance(){
        return  SINGLE_INSTACE._INSTANCE;
    }

    private RetrofitManager(){
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //设置读写超时
    private OkHttpClient  mOkHttpClient(){
        return  new OkHttpClient.Builder()
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(3000,TimeUnit.MILLISECONDS)
                .build();
    }

    public  Retrofit getRetrofit(){
        return  retrofit;
    }
    public <T> T create(Class<T> clazz){
        return  retrofit.create(clazz);
    }
}
