package com.bw.shen.Presenter;

import com.bw.shen.core.DataCall;
import com.bw.shen.core.http.IRequest;
import com.bw.shen.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019-1-11.
 */

public class OverPresenter extends BasePresenter{
    int page=1;
    public OverPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest= NetworkManager.instance().create(IRequest.class);
        boolean refresh= (boolean) args[0];
        if (refresh){
            page=1;
        }else{
            page++;
        }

        return iRequest.IndentPay((long) args[1],(String)args[2],9,1,5);
    }
}
