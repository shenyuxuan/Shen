package com.bw.shen;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.shen.fragment.CircleFragment;
import com.bw.shen.fragment.HomepageFragment;
import com.bw.shen.fragment.IndentpageFragment;
import com.bw.shen.fragment.MypageFragment;
import com.bw.shen.fragment.ShoppingCarFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity {

    //    @BindView(R.id.homepage)
//    RadioButton mHomepage;
//    @BindView(R.id.findpage)
//    RadioButton mFindpage;
//    @BindView(R.id.shopcarage)
//    RadioButton mShopcarage;
//    @BindView(R.id.orderpage)
//    RadioButton mOrderpage;
//    @BindView(R.id.mepage)
//    RadioButton mMepage;
//    @BindView(R.id.bottom)
//    LinearLayout mBottom;
//    @BindView(R.id.vp)
    //   ViewPager mVp;
    List<Fragment> mFragments;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.bottom)
    RelativeLayout mBottom;
    @BindView(R.id.homeapge)
    TextView mHomeapge;
    @BindView(R.id.findpage)
    TextView mFindpage;
    @BindView(R.id.shopcarage)
    TextView mShopcarage;
    @BindView(R.id.orderpage)
    TextView mOrderpage;
    @BindView(R.id.mepage)
    TextView mMepage;
//    @BindView(R.id.homeapge)
//    RadioButton mHomeapge;
//    @BindView(R.id.findpage)
//    RadioButton mFindpage;
//    @BindView(R.id.shopcarage)
//    RadioButton mShopcarage;
//    @BindView(R.id.orderpage)
//    RadioButton mOrderpage;
//    @BindView(R.id.mepage)
//    RadioButton mMepage;
//    @BindView(R.id.bottom)
//    RadioGroup mBottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);

//        mFragments = new ArrayList<>();
//        mFragments.add(new HomepageFragment());
//        mFragments.add(new CircleFragment());
//        mFragments.add(new ShoppingCarFragment());
//        mFragments.add(new IndentpageFragment());
//        mFragments.add(new MypageFragment());
//
//        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return mFragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return mFragments.size();
//            }
//        });
//    }
//
//
//    @OnClick({R.id.homeapge, R.id.findpage, R.id.shopcarage, R.id.orderpage, R.id.mepage})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.homeapge:
//                mVp.setCurrentItem(0);
//                ChangeBackGround(0);
//                break;
//            case R.id.findpage:
//                mVp.setCurrentItem(1);
//                ChangeBackGround(1);
//                break;
//            case R.id.shopcarage:
//                mVp.setCurrentItem(2);
//                ChangeBackGround(2);
//                break;
//            case R.id.orderpage:
//                mVp.setCurrentItem(3);
//                ChangeBackGround(3);
//                break;
//            case R.id.mepage:
//                mVp.setCurrentItem(4);
//                ChangeBackGround(4);
//                break;
//        }
//    }
//    public void ChangeBackGround(int index){
//        mHomeapge.setBackgroundResource(index ==0?R.drawable.common_tab_btn_home_n_hdpi:R.drawable.common_tab_btn_home);
//        mFindpage.setBackgroundResource(index ==1?R.drawable.common_tab_btn_circle_n_hdpi:R.drawable.common_tab_btn_circle_s);
//        mShopcarage.setBackgroundResource(index ==2?R.drawable.common_tab_btn_home_n_hdpi:R.drawable.common_tab_btn_home);
//        mOrderpage.setBackgroundResource(index ==3?R.drawable.common_tab_btn_list_n_hdpi:R.drawable.common_tab_btn_list_s);
//        mMepage.setBackgroundResource(index ==4?R.drawable.common_tab_btn_my_n_hdpi:R.drawable.common_tab_btn_my_s);
//    }
        mFragments = new ArrayList<>();
        mFragments.add(new HomepageFragment());
        mFragments.add(new CircleFragment());
        mFragments.add(new ShoppingCarFragment());
        mFragments.add(new IndentpageFragment());
        mFragments.add(new MypageFragment());
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ChangeBackGround(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //默认显示第一个页面
        mHomeapge.setBackgroundResource(R.drawable.common_tab_btn_home);
    }

    @OnClick({R.id.homeapge, R.id.findpage, R.id.shopcarage, R.id.orderpage, R.id.mepage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeapge:
                mVp.setCurrentItem(0);
                ChangeBackGround(0);
                break;
            case R.id.findpage:
                mVp.setCurrentItem(1);
                ChangeBackGround(1);
                break;
            case R.id.shopcarage:
                mVp.setCurrentItem(2);
                ChangeBackGround(2);
                break;
            case R.id.orderpage:
                mVp.setCurrentItem(3);
                ChangeBackGround(3);
                break;
            case R.id.mepage:
                mVp.setCurrentItem(4);
                ChangeBackGround(4);
                break;
        }
    }

    public void ChangeBackGround(int index) {
        mHomeapge.setBackgroundResource(index == 0 ? R.drawable.common_tab_btn_home : R.drawable.common_tab_btn_home_n_hdpi);
        mFindpage.setBackgroundResource(index == 1 ? R.drawable.common_tab_btn_circle_s : R.drawable.common_tab_btn_circle_n_hdpi);
        mShopcarage.setBackgroundResource(index == 2 ? R.drawable.commom_tab_btn_shoppin : R.drawable.commom_tab_btn_shoppin);
        mOrderpage.setBackgroundResource(index == 3 ? R.drawable.common_tab_btn_list_s : R.drawable.common_tab_btn_list_n_hdpi);
        mMepage.setBackgroundResource(index == 4 ? R.drawable.common_tab_btn_my_s : R.drawable.common_tab_btn_my_n_hdpi);
    }
}
