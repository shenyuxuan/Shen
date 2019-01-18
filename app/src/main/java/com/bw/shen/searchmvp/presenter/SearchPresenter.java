package com.bw.shen.searchmvp.presenter;

import com.bw.shen.bean.Result;
import com.bw.shen.bean.Search;
import com.bw.shen.bean.ShopCarBean;
import com.bw.shen.searchmvp.model.SearchModel;
import com.bw.shen.searchmvp.view.SearchView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2019-1-8.
 */

public class SearchPresenter {
    private SearchView cv;
    private SearchModel mSearchModel;

    public void attach(SearchView cv){
        this.cv=cv;
        mSearchModel=new SearchModel();
    }
    public  void get(String keyword,int page,int count){
        mSearchModel.search(keyword, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

               .subscribe(new Consumer<Result<List<Search>>>() {
                   @Override
                   public void accept(Result<List<Search>> listResult) throws Exception {
                       if (listResult != null) {
                           cv.onSuccess(listResult);
                       }
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                       cv.onfailed(new Exception("网络异常"));
                   }
               });
    }
}
