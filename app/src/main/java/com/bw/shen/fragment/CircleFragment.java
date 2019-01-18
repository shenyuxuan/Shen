package com.bw.shen.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.bw.shen.Presenter.CirclePresenter;
import com.bw.shen.R;
import com.bw.shen.adapter.CircleAdpater;
import com.bw.shen.bean.Circle;
import com.bw.shen.bean.Result;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDFragment;
import com.bw.shen.core.exception.ApiException;
import com.bw.shen.utils.gridview.RecyclerGridView;
import com.bw.shen.utils.recyclerview.SpacingItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2019-1-7.
 * 圈子的显示页面
 */

public class CircleFragment extends WDFragment implements XRecyclerView.LoadingListener {

    public static boolean addCircle;
    @BindView(R.id.circle_list)
    XRecyclerView mCircleList;

    private CircleAdpater mCircleAdapter;

    CirclePresenter circlePresenter;

    @Override
    public String getPageName() {
        return "发布圈子Fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_circle;
    }

    @Override
    protected void initView() {

        mCircleAdapter = new CircleAdpater(getContext());

        mCircleList.setLayoutManager(new GridLayoutManager(getContext(),1));

        int space = getResources().getDimensionPixelSize(R.dimen.dp_20);

        mCircleList.addItemDecoration(new SpacingItemDecoration(space));

        mCircleList.setAdapter(mCircleAdapter);
        mCircleList.setLoadingListener(this);

        // 设置数据
        circlePresenter = new CirclePresenter(new CircleCall());

        mCircleList.refresh();//xreview的方法

    }

    @Override
    public void onRefresh() {
        if (circlePresenter.isRunning()){
            mCircleList.refreshComplete();
            return;
        }
        circlePresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (circlePresenter.isRunning()){
            mCircleList.loadMoreComplete();
            return;
        }
        circlePresenter.reqeust(false,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    class CircleCall implements DataCall<Result<List<Circle>>> {

        @Override
        public void success(Result<List<Circle>> data) {
            mCircleList.refreshComplete();
            mCircleList.loadMoreComplete();
            if (data.getStatus().equals("0000")){
                //添加列表并刷新
                Toast.makeText(getContext(), ""+data, Toast.LENGTH_SHORT).show();
                if (circlePresenter.getPage()==1){
                    mCircleAdapter.clear();
                }
                mCircleAdapter.addAll(data.getResult());
                mCircleAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            mCircleList.refreshComplete();
            mCircleList.loadMoreComplete();
        }
    }

}