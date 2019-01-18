package com.bw.shen.homepagemvp.presenter;

import com.bw.shen.bean.Shops;
import com.bw.shen.homepagemvp.model.HomepageModel;
import com.bw.shen.homepagemvp.view.HomepageView;

import java.lang.reflect.Modifier;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2019-1-1.
 */

public class HomepagePresenter {
    //找方法
    private HomepageView sv;
    private HomepageModel mHomepageModel;

    public void attach(HomepageView sv) {
        this.sv = sv;
        mHomepageModel = new HomepageModel();
    }


    //加载数据
    public  void  get(){
        mHomepageModel.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Shops>() {
                    @Override
                    public void accept(Shops shops) throws Exception {
                        if (shops != null) {
                          sv.onSuccessRx(shops);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sv.onFailed(new Exception("网络异常"));
                    }
                });
    }
public  void  getml(){
        mHomepageModel.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Shops>() {
                    @Override
                    public void accept(Shops shops) throws Exception {
                        if (shops!=null){
                            sv.onSuccessMl(shops);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sv.onFailed(new Exception("网络异常"));
                    }
                });
}

public  void getpz(){
    mHomepageModel.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Shops>() {
                @Override
                public void accept(Shops shops) throws Exception {
                    if (shops != null) {
                        sv.onSuccessPz(shops);
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    sv.onFailed(new Exception("网络异常"));
                }
            });
}
}
