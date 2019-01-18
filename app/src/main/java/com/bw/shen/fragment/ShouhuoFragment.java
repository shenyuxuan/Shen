package com.bw.shen.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.shen.Presenter.PayPresenter;
import com.bw.shen.Presenter.WaitsIndentPresenter;
import com.bw.shen.R;
import com.bw.shen.adapter.PayAdapter;
import com.bw.shen.adapter.WaitIndentAdapter;
import com.bw.shen.adapter.WaitsIndentAdapter;
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

public class ShouhuoFragment extends WDFragment{

    @BindView(R.id.recyview)
    RecyclerView mrecyView;
    Unbinder unbinder;
    private WaitIndentAdapter adapter;
    private WaitsIndentPresenter mWaitsIndentPresenter;
    @Nullable
    @Override
    public String getPageName() {
        return "待收货订单";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shouhuo_fragment;
    }

    @Override
    protected void initView() {
        adapter=new WaitIndentAdapter(getActivity());

        //布局管理器
        mrecyView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mrecyView.addItemDecoration(new SpacingItemDecoration(10));
        mrecyView.setAdapter(adapter);
        //  mrecyView.setLoadingListener(this);
        //请求数据
        mWaitsIndentPresenter=new WaitsIndentPresenter(new WaitsIndentCall());
        mWaitsIndentPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());

    }



    class WaitsIndentCall implements DataCall<Result<List<IndentAll>>> {

        @Override
        public void success(Result<List<IndentAll>> data) {


            Log.e("aa",data.getMessage()+"");
            if (data.getStatus().equals("0000")){
                adapter.addAll(data.getOrderList());
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
