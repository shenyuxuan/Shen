package com.bw.shen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bw.shen.Presenter.FootPresenter;
import com.bw.shen.adapter.FootAdapter;
import com.bw.shen.bean.Foot;
import com.bw.shen.bean.Result;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDActivity;
import com.bw.shen.core.exception.ApiException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFootPrintActivity extends WDActivity {

    @BindView(R.id.foot_xrv_list)
    RecyclerView footXrvList;
    private FootAdapter footAdapter;
    private FootPresenter footPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_foot_print;
    }

    @Override
    protected void initView() {
        footAdapter = new FootAdapter(this);

        footXrvList.setLayoutManager(new GridLayoutManager(this,2));

        footXrvList.setAdapter(footAdapter);

        footPresenter = new FootPresenter(new FootCall());

        footPresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());

    }

    @Override
    protected void destoryData() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    class FootCall implements DataCall<Result<List<Foot>>> {

        @Override
        public void success(Result<List<Foot>> data) {
            if (data.getStatus().equals("0000")) {
                footAdapter.addAll(data.getResult());
                footAdapter.notifyDataSetChanged();

            }
        }

        @Override
        public void fail(ApiException e) {
        }
    }

}
