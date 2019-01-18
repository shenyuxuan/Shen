package com.bw.shen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.shen.MyCircleActivity;
import com.bw.shen.MyFootPrintActivity;
import com.bw.shen.MyShippingAddressActivity;
import com.bw.shen.MyWalletActivity;
import com.bw.shen.MyzlActivity;
import com.bw.shen.R;
import com.bw.shen.SetActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018-12-29.
 */

public class MypageFragment extends Fragment {


    @BindView(R.id.personal_data)
    TextView mPersonalData;
    @BindView(R.id.my_circle)
    TextView mMyCircle;
    @BindView(R.id.my_footprint)
    TextView mMyFootprint;
    @BindView(R.id.my_wallet)
    TextView mMyWallet;
    @BindView(R.id.my_shipping_address)
    TextView mMyShippingAddress;
    @BindView(R.id.shezhi)
    TextView mShezhi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_fragment, null, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.personal_data, R.id.my_circle, R.id.my_footprint, R.id.my_wallet, R.id.my_shipping_address, R.id.shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_data:
                startActivity(new Intent(getContext(), MyzlActivity.class));
                break;
            case R.id.my_circle:
                startActivity(new Intent(getContext(), MyCircleActivity.class));
                break;
            case R.id.my_footprint:
                startActivity(new Intent(getContext(), MyFootPrintActivity.class));
                break;
            case R.id.my_wallet:
                startActivity(new Intent(getContext(), MyWalletActivity.class));
                break;
            case R.id.my_shipping_address:
                startActivity(new Intent(getContext(), MyShippingAddressActivity.class));
                break;
            case R.id.shezhi:
                startActivity(new Intent(getContext(), SetActivity.class));
                break;
        }
    }
}
