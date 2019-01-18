package com.bw.shen.Presenter;

import com.bw.shen.core.DataCall;
import com.bw.shen.core.http.IRequest;
import com.bw.shen.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019-1-11.
 */

public class FootPresenter extends BasePresenter {
    int page = 1;
    public FootPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = NetworkManager.instance().create(IRequest.class);
        boolean refresh = (boolean) args[0];
        if (refresh){
            page = 1;
        }else {
            page ++;
        }
        return apiService.getmyfoot((long)args[1],(String)args[2],page,50);
    }
}
