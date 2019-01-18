package com.bw.shen.homepagemvp.model;

import com.bw.shen.RetrofitApi;
import com.bw.shen.bean.Shops;
import com.bw.shen.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019-1-1.
 */

public class HomepageModel {

private RetrofitApi mRetrofitApi= RetrofitManager.getinstance().create(RetrofitApi.class);

public Observable<Shops> get(){
    Observable<Shops> ShopRx=mRetrofitApi.ShopRx();
     return  ShopRx;
}
}
