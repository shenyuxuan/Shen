package com.bw.shen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bw.shen.DetailsActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by lenovo on 2019-1-4.
 */

public class DetailAdapter extends PagerAdapter{
    private Context mContext;
    private  String[] images;

    public DetailAdapter(String[] images, DetailsActivity con) {
        this.images = images;
        mContext = con;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);

        simpleDraweeView.setImageURI(images[position]);
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
