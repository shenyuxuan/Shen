package com.bw.shen.detailpagemvp.presenter;

import com.bw.shen.bean.DetailBean;
import com.bw.shen.bean.SynchronousBean;
import com.bw.shen.detailpagemvp.model.DetailModel;
import com.bw.shen.detailpagemvp.view.DetailView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2019-1-4.
 */

public class DetailPresenter {
    private DetailView dv;
    private DetailModel mDetailModel;

    public void attach(DetailView dv) {
        this.dv = dv;
        mDetailModel = new DetailModel();
    }

    public void get(int userId, String sessionId, int commodityId) {
        mDetailModel.get(userId, sessionId, commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailBean>() {
                    @Override
                    public void accept(DetailBean detailBean) throws Exception {
                        if (detailBean != null) {
                            dv.onSuccessDetail(detailBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dv.onFailed(new Exception("网络异常"));
                    }
                });
    }

    //添加购物车
    public void synchronous(int userId, String sessionId, String data) {
        mDetailModel.synchronous(userId, sessionId, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SynchronousBean>() {
                    @Override
                    public void accept(SynchronousBean bean) throws Exception {
                        dv.synchronous(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dv.onFailed(new Exception("网络异常"));
                    }
                });
    }
}
