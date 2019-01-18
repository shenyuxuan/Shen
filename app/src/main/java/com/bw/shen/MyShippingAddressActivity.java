package com.bw.shen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bw.shen.Presenter.MyAddressPresenter;
import com.bw.shen.adapter.MyAddressAdaper;
import com.bw.shen.bean.MyAddress;
import com.bw.shen.bean.Result;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDActivity;
import com.bw.shen.core.exception.ApiException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyShippingAddressActivity extends WDActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_shipping_address);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.btn_wancheng)
    TextView btnWancheng;
    @BindView(R.id.actvity_address_xrecy)
    RecyclerView actvityAddressXrecy;
    @BindView(R.id.address_btn_address)
    Button addressBtnAddress;
    private MyAddressAdaper myAddressAdaper;
    private MyAddressPresenter myAddressPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_shipping_address;
    }

    @Override
    protected void initView() {
        myAddressAdaper = new MyAddressAdaper(this);
        actvityAddressXrecy.setLayoutManager(new GridLayoutManager(this, 1));
        actvityAddressXrecy.setAdapter(myAddressAdaper);
        myAddressPresenter = new MyAddressPresenter(new MyAddressCall());
        myAddressPresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
        myAddressAdaper.setOnItemClickListener(new MyAddressAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                myAddressAdaper.remove(position);
            }
        });
    }

    class MyAddressCall implements DataCall<Result<List<MyAddress>>> {

        @Override
        public void success(Result<List<MyAddress>> data) {
            if (data.getStatus().equals("0000")){
                myAddressAdaper.addAll(data.getResult());
                myAddressAdaper.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
        }
    }

    @Override
    protected void destoryData() {

    }

    @OnClick({R.id.address_btn_address,R.id.btn_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.address_btn_address:
                Intent intent = new Intent(this,AddAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wancheng:
                finish();
                break;
        }

    }

}
