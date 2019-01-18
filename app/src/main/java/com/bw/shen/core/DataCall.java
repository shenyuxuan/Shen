package com.bw.shen.core;

import com.bw.shen.core.exception.ApiException;

/**
 * Created by lenovo on 2019-1-7.
 */

public interface DataCall<T> {

    void success(T data);
    void fail(ApiException e);

}
