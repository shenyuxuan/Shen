package com.bw.shen.searchmvp.model;

import com.bw.shen.RetrofitApi;
import com.bw.shen.bean.Result;
import com.bw.shen.bean.Search;
import com.bw.shen.bean.ShopCarBean;
import com.bw.shen.utils.RetrofitManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019-1-8.
 */

public class SearchModel {
    private RetrofitApi mRetrofitApi= RetrofitManager.getinstance().create(RetrofitApi.class);

    public Observable<Result<List<Search>>> search(String keyword,int page,int count){
        Observable<Result<List<Search>>> getsearch=mRetrofitApi.Search(keyword, page, count);
        return getsearch;
    }
}
