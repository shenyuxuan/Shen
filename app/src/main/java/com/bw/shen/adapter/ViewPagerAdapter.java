package com.bw.shen.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.shen.R;
import com.bw.shen.bean.Shop;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2019-1-8.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<Shop.ResultBean> mList;
    private LayoutInflater mLayoutInflater;

    public ViewPagerAdapter(List<Shop.ResultBean> list, LayoutInflater context) {
        mList = list;
        mLayoutInflater = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.item_viewpager, container, false);
        SimpleDraweeView img = view.findViewById(R.id.my_image_view);
        //img.setImageResource(R.mipmap.sea);
        Uri parse = Uri.parse(mList.get(position%mList.size()).getImageUrl());
        img.setImageURI(parse);
        RoundingParams roundingParams1 = RoundingParams.fromCornersRadius(10f);
        //roundingParams.setRoundAsCircle(true);
        img.getHierarchy().setRoundingParams(roundingParams1);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}

