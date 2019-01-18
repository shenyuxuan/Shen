package com.bw.shen.Presenter;

import com.bw.shen.core.DataCall;
import com.bw.shen.core.http.IRequest;
import com.bw.shen.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019-1-15.
 */

public class AddAddressPresenter extends BasePresenter {
    public AddAddressPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = NetworkManager.instance().create(IRequest.class);
        return apiService.getaddress((String) args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5]);

    }
}
