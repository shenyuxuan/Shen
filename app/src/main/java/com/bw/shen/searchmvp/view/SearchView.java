package com.bw.shen.searchmvp.view;

import com.bw.shen.bean.Result;
import com.bw.shen.bean.Search;
import com.bw.shen.bean.ShopCarBean;

import java.util.List;

/**
 * Created by lenovo on 2019-1-8.
 */

public interface SearchView {
    void onSuccess(Result<List<Search>> data);
    void onfailed(Exception e);
}
