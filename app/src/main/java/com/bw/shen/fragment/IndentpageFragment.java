package com.bw.shen.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.shen.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018-12-29.
 */

public class IndentpageFragment extends Fragment {
    @BindView(R.id.allindent)
    RadioButton mAllindent;
    @BindView(R.id.pay)
    RadioButton mPay;
    @BindView(R.id.shouhuo)
    RadioButton mShouhuo;
    @BindView(R.id.pingjia)
    RadioButton mPingjia;
    @BindView(R.id.over)
    RadioButton mOver;
    @BindView(R.id.fragment_order_fourrg)
    RadioGroup mFragmentOrderFourrg;

    @BindView(R.id.vp)
    ViewPager mVp;
    Unbinder unbinder;
List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.indentpage_fragment, null, false);
        unbinder = ButterKnife.bind(this, view);
        mFragments=new ArrayList<>();
        mFragments.add(new AllindentFragment());
        mFragments.add(new PayFragment());
        mFragments.add(new ShouhuoFragment());
        mFragments.add(new PingjiaFragment());
        mFragments.add(new OverFragment());
        mVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        return view;
    }


    @OnClick({R.id.allindent, R.id.pay, R.id.shouhuo, R.id.pingjia, R.id.over})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.allindent:
                mVp.setCurrentItem(0);

                break;
            case R.id.pay:
                mVp.setCurrentItem(1);
                break;
            case R.id.shouhuo:
                mVp.setCurrentItem(2);
                break;
            case R.id.pingjia:
                mVp.setCurrentItem(3);
                break;
            case R.id.over:
                mVp.setCurrentItem(4);
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
