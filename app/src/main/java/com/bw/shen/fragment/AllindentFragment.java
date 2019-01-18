package com.bw.shen.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bw.shen.R;
import com.bw.shen.adapter.AllIndentAdapter;
import com.bw.shen.Presenter.AllIndentPresenter;
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

public class AllindentFragment extends WDFragment{

    @BindView(R.id.allorderfragemnt_item_RecyclerView)
    RecyclerView mrecyView;
    Unbinder unbinder;
private AllIndentAdapter adapter;
private AllIndentPresenter mAllIndentPresenter;
    @Nullable
    @Override
    public String getPageName() {
        return "全部订单";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.allindent_fragment;
    }

    @Override
    protected void initView() {
        adapter=new AllIndentAdapter(getActivity());

        //布局管理器
        mrecyView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mrecyView.addItemDecoration(new SpacingItemDecoration(10));
        mrecyView.setAdapter(adapter);
     //   mrecyView.setLoadingListener(this);
       //请求数据
        mAllIndentPresenter=new AllIndentPresenter(new AllIndentCall());
        mAllIndentPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
    }



    class AllIndentCall implements DataCall <Result<List<IndentAll>>>{

        @Override
        public void success(Result<List<IndentAll>> data) {


            Log.e("aa",data.getMessage()+"");
           // mrecyView.refreshComplete();
            //mrecyView.loadMoreComplete();
            if (data.getStatus().equals("0000")){
                adapter.addAll(data.getOrderList());
                adapter.notifyDataSetChanged();
             //   Toast.makeText(getActivity(),"来了",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
          //  mrecyView.refreshComplete();
           // mrecyView.loadMoreComplete();
        }
    }
}
