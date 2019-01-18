package com.bw.shen.homepagemvp.view;

import com.bw.shen.bean.Shops;

import java.util.List;

/**
 * Created by lenovo on 2019-1-1.
 */

public interface HomepageView {
    void onSuccessRx(Shops shops);
    void onSuccessMl(Shops shops);
    void onSuccessPz(Shops shops);
    void onFailed(Exception e);
}
