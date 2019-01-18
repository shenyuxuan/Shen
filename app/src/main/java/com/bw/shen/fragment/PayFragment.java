package com.bw.shen.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bw.shen.Presenter.PayPresenter;
import com.bw.shen.R;
import com.bw.shen.adapter.PayAdapter;
import com.bw.shen.bean.IndentAll;
import com.bw.shen.bean.Result;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDFragment;
import com.bw.shen.core.exception.ApiException;
import com.bw.shen.utils.recyclerview.SpacingItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019-1-4.
 */

public class PayFragment extends WDFragment{
    @BindView(R.id.payorderfragemnt_item_RecyclerView)
     RecyclerView mrecyView;
    Unbinder unbinder;
    private PayAdapter adapter;
    private PayPresenter  mPayPresenter;
    @Nullable
    @Override
    public String getPageName() {
        return "待支付订单";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pay_fragment;
    }

    @Override
    protected void initView() {
        adapter=new PayAdapter(getActivity());

        //布局管理器
        mrecyView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mrecyView.addItemDecoration(new SpacingItemDecoration(10));
        mrecyView.setAdapter(adapter);
      //  mrecyView.setLoadingListener(this);
        //请求数据
        mPayPresenter=new PayPresenter(new PayIndentCall());
        mPayPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());

    }

//    @Override
//    public void onRefresh() {
//        if (mPayPresenter.isRunning()){
//            mrecyView.refreshComplete();
//            return;
//        }
//
//    }

//    @Override
//    public void onLoadMore() {
//        if (mPayPresenter.isRunning()) {
//            mrecyView.loadMoreComplete();
//            return;
//        }
//        mPayPresenter.reqeust(false,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
//    }

    class PayIndentCall implements DataCall<Result<List<IndentAll>>> {

        @Override
        public void success(Result<List<IndentAll>> data) {


            Log.e("aa",data.getMessage()+"");
//            mrecyView.refreshComplete();
//            mrecyView.loadMoreComplete();
            if (data.getStatus().equals("0000")){
                adapter.addAll(data.getOrderList());
                adapter.notifyDataSetChanged();
                //   Toast.makeText(getActivity(),"来了",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
//            mrecyView.refreshComplete();
//            mrecyView.loadMoreComplete();
        }
    }
}
