package com.bw.shen.detailpagemvp.view;

import com.bw.shen.bean.DetailBean;
import com.bw.shen.bean.SynchronousBean;

/**
 * Created by lenovo on 2019-1-4.
 */

public interface DetailView {
void onSuccessDetail(DetailBean detailBean);

//添加购物车
    void synchronous(SynchronousBean bean);
void onFailed(Exception e);

}
