package com.bw.shen.detailpagemvp.model;

import com.bw.shen.RetrofitApi;
import com.bw.shen.bean.DetailBean;
import com.bw.shen.bean.SynchronousBean;
import com.bw.shen.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019-1-4.
 */

public class DetailModel {
    RetrofitApi mRetrofitApi= RetrofitManager.getinstance().create(RetrofitApi.class);
    public Observable<DetailBean> get(int userId,String sessionId,int commodityId){
        Observable<DetailBean> getdatail=mRetrofitApi.ShopDetail(userId, sessionId, commodityId);
        return getdatail;
    }
    public Observable<SynchronousBean> synchronous(int userId,String sessionId,String data){
        Observable<SynchronousBean> synchronous = mRetrofitApi.synchronous(userId, sessionId, data);
        return synchronous;
    }
}
