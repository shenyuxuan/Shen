package com.bw.shen.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.shen.DetailsActivity;
import com.bw.shen.Presenter.BannerPresenter;
import com.bw.shen.R;
import com.bw.shen.SearchActivity;
import com.bw.shen.adapter.MAdapter;
import com.bw.shen.adapter.PAdapter;
import com.bw.shen.adapter.RxAdapter;
import com.bw.shen.bean.Banner;
import com.bw.shen.bean.Result;
import com.bw.shen.bean.Shop;
import com.bw.shen.bean.Shops;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.exception.ApiException;
import com.bw.shen.homepagemvp.presenter.HomepagePresenter;
import com.bw.shen.homepagemvp.view.HomepageView;
import com.bw.shen.utils.OkHttpUtils;
import com.bw.shen.utils.recyclerview.SpacingItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2018-12-29.
 */

public class HomepageFragment extends Fragment implements HomepageView {
//    @BindView(R.id.txt_search)
//    TextView mTxtSearch;
    @BindView(R.id.etxt_search)
    EditText mEtxtSearch;

    @BindView(R.id.banner)
    MZBannerView mMZBanner;
    Unbinder unbinder;
//    @BindView(R.id.classify)
//    ImageView mClassify;
    @BindView(R.id.classify)
    ImageView mClassify;
    @BindView(R.id.txt_search)
    TextView mTxtSearch;

    //////////////////////////////////////////////
    private RecyclerView rx;
    private RecyclerView ml;
    private RecyclerView pz;
    private HomepagePresenter mHomepagePresenter;
     BannerPresenter bannerPresenter;
    private RxAdapter mRxAdapter;
    private MAdapter mMlAdapter;
    private PAdapter mPzAdapter;
    private RadioGroup popupwindow_item_class;
    private RadioGroup popupwindow_item_title;
    //热销新品
    List<Shops.ResultBean.RxxpBean.CommodityListBean> mListrx;
    List<Shops.ResultBean.MlssBean.CommodityListBeanXX> mListml;
    List<Shops.ResultBean.PzshBean.CommodityListBeanX> mListpz;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment, null, false);
        rx = view.findViewById(R.id.rxrecyView);
        ml = view.findViewById(R.id.mlrecyView);
        pz = view.findViewById(R.id.pzrecyView);
        mListrx = new ArrayList<>();
        mListml = new ArrayList<>();
        mListpz = new ArrayList<>();
        //品质生活
        GridLayoutManager pzmanager=new GridLayoutManager(getContext(),2);
        pz.setLayoutManager(pzmanager);
        pz.addItemDecoration(new SpacingItemDecoration(10));

        //热销新品
        GridLayoutManager rxmanager=new GridLayoutManager(getContext(),3);
        rx.setLayoutManager(rxmanager);
        rx.addItemDecoration(new SpacingItemDecoration(10));
        //魔力时尚
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(getContext());
        ml.setLayoutManager(manager1);


        mRxAdapter = new RxAdapter(getContext(), mListrx);
        mMlAdapter = new MAdapter(getContext(), mListml);
        mPzAdapter = new PAdapter(getContext(), mListpz);


        // 设置数据
        bannerPresenter = new BannerPresenter(new MyBanner());
        bannerPresenter.reqeust();

        rx.setAdapter(mRxAdapter);
        ml.setAdapter(mMlAdapter);
        pz.setAdapter(mPzAdapter);
        mHomepagePresenter = new HomepagePresenter();
        mHomepagePresenter.attach(this);
        mHomepagePresenter.get();
        mHomepagePresenter.getml();
        mHomepagePresenter.getpz();

        //点击商品 跳转对应详情页面
        mMlAdapter.setonItemClickListener(new MAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                int commodityId = mListml.get(position).getCommodityId();

                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityId);

                startActivity(intent);
            }
        });
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 热销的成功方法
     *
     * @param shops
     */
    @Override
    public void onSuccessRx(Shops shops) {
        if (shops != null) {
            Shops.ResultBean resultBean = shops.getResult();
            List<Shops.ResultBean.RxxpBean> rxshop = resultBean.getRxxp();
            List<Shops.ResultBean.RxxpBean.CommodityListBean> commodityListrx = rxshop.get(0).getCommodityList();
            mListrx.clear();
            mListrx.addAll(commodityListrx);
            mRxAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 魔力时尚的成功方法
     *
     * @param shops
     */
    @Override
    public void onSuccessMl(Shops shops) {
        if (shops != null) {
            Shops.ResultBean resultBean = shops.getResult();
            List<Shops.ResultBean.MlssBean> mlshop = resultBean.getMlss();
            List<Shops.ResultBean.MlssBean.CommodityListBeanXX> commodityListml = mlshop.get(0).getCommodityList();
            mListml.clear();
            mListml.addAll(commodityListml);
            mMlAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 品质生活的成功方法
     *
     * @param shops
     */
    @Override
    public void onSuccessPz(Shops shops) {
        if (shops != null) {
            Shops.ResultBean resultBean = shops.getResult();
            List<Shops.ResultBean.PzshBean> pzshop = resultBean.getPzsh();
            List<Shops.ResultBean.PzshBean.CommodityListBeanX> commodityListpz = pzshop.get(0).getCommodityList();
            mListpz.clear();
            mListpz.addAll(commodityListpz);
            mPzAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * popwindows 关键字搜索
     * @param view
     */
    @OnClick({R.id.classify, R.id.txt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.classify:

                final View popu = View.inflate(getActivity(), R.layout.popupwindow_item, null);
                //创建PopupWindow
                PopupWindow window = new PopupWindow(popu, 1000, 300, true);
                //配置PopupWindow
                window.setBackgroundDrawable(new ColorDrawable());
                window.setOutsideTouchable(true);
                window.setTouchable(true);
                window.showAsDropDown(mClassify);
                //获取PopupWindow的id
                popupwindow_item_class = popu.findViewById(R.id.popupwindow_item_class);
                popupwindow_item_title = popu.findViewById(R.id.popupwindow_item_title);
                //设置点击事件
                popupwindow_item_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = popu.findViewById(popupwindow_item_class.getCheckedRadioButtonId());
                    }
                });
                popupwindow_item_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = popu.findViewById(popupwindow_item_title.getCheckedRadioButtonId());
                    }
                });
                break;
            case R.id.txt_search:
                String string = mEtxtSearch.getText().toString();
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("keyword", string);
                startActivity(intent);
                break;
        }
    }


    class BannerViewHolder implements MZViewHolder<Banner> {
        private SimpleDraweeView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, Banner banner) {
            mImageView.setImageURI(Uri.parse(banner.getImageUrl()));
        }


    }
    class MyBanner implements DataCall<Result<List<Banner>>> {

        @Override
        public void success(Result<List<Banner>> data) {
            if (data.getStatus().equals("0000")){
                mMZBanner.setIndicatorVisible(false);
                mMZBanner.setPages(data.getResult(), new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                mMZBanner.start();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

/////////////////////////////////////////////////////////

//    /**
//     * 轮播图
//     */
//
//    public class GlideImageLoder extends ImageLoader {
//
//        @Override
//        public void displayImage(Context context, Object path, ImageView imageView) {
//            Glide.with(context).load(path).into(imageView);
//        }
//    }
//
//    private void jiexi() {
//
//        OkHttpUtils.doGet(url, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String string = response.body().string();
//                Message message = new Message();
//                message.obj = string;
//                handler.sendMessage(message);
//            }
//        });


    }

    /////////////////////////////////////////////////
