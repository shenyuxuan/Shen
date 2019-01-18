package com.bw.shen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.shen.Presenter.AddAddressPresenter;
import com.bw.shen.Presenter.MyAddressPresenter;
import com.bw.shen.adapter.MyAddressAdaper;
import com.bw.shen.bean.MyAddress;
import com.bw.shen.bean.Result;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDActivity;
import com.bw.shen.core.exception.ApiException;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends WDActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_address);
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

    @BindView(R.id.address_add_name)
    EditText addressAddName;
    @BindView(R.id.address_add_phone)
    EditText addressAddPhone;
    @BindView(R.id.address_add_district)
    TextView addressAddDistrict;
    @BindView(R.id.mine_address_add_deposit)
    EditText mineAddressAddDeposit;
    @BindView(R.id.address_add_postcode)
    EditText addressAddPostcode;
    @BindView(R.id.address_add_save)
    Button addressAddSave;
    private CityPicker mBuild;
    AddAddressPresenter addAddressPresenter = new AddAddressPresenter(new AddressCall());
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {
        initSan();
    }

    private void initSan() {
        addressAddDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPiker();
                mBuild.show();

            }
        });
    }
    private void initPiker() {
        mBuild = new CityPicker.Builder(this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#FFC0CB")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();
        mBuild.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                //省份
                String province = strings[0];
                String city = strings[1];
                String district = strings[2];
                String code = strings[3];
                addressAddDistrict.setText(province + city + district);
                Log.e("aaaaaaaaaaaaaa", addressAddDistrict.getText().toString());
            }

            @Override
            public void onCancel() {

            }
        });
    }


    @Override
    protected void destoryData() {

    }

    @OnClick(R.id.address_add_save)
    public void onViewClicked() {
        String name = addressAddName.getText().toString();
        String phone = addressAddPhone.getText().toString();
        String districe = addressAddDistrict.getText().toString();
        String mineaddress = mineAddressAddDeposit.getText().toString();
        String postcode = addressAddPostcode.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(districe) || TextUtils.isEmpty(mineaddress) || TextUtils.isEmpty(postcode)){
            Toast.makeText(this, "所有输入框内容不能为空", Toast.LENGTH_SHORT).show();
        }else {
            addAddressPresenter.reqeust(LOGIN_USER.getUserId()+"",LOGIN_USER.getSessionId(),name,phone,districe+mineaddress,postcode);
        }
    }
    class AddressCall implements DataCall<Result>{

        @Override
        public void success(Result data) {

            if (data.getStatus().equals("0000")){
                Toast.makeText(AddAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddAddressActivity.this,MyShippingAddressActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(AddAddressActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
        }
    }
}
