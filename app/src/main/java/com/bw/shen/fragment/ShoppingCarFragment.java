package com.bw.shen.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.shen.R;
import com.bw.shen.adapter.ShopCarAdapter;
import com.bw.shen.bean.Result;
import com.bw.shen.bean.ShopCarBean;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDFragment;
import com.bw.shen.core.exception.ApiException;
import com.bw.shen.Presenter.ShopCarPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019-1-8.
 */

public class ShoppingCarFragment extends WDFragment implements XRecyclerView.LoadingListener{
    @BindView(R.id.xrv_cart)
    XRecyclerView mXrvCart;
    @BindView(R.id.cb_all)
    CheckBox mCbAll;
    @BindView(R.id.cart_txt_sumprice)
    TextView mCartTxtSumprice;
    @BindView(R.id.btn_js)
    Button mBtnJs;
    Unbinder unbinder;

    private ShopCarAdapter adapter;
    private ShopCarPresenter mShopCarPresenter;

    @Override
    public String getPageName() {
        return "购物车的fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shoppingpage_fragment;
    }

    @Override
    protected void initView() {
      adapter=new ShopCarAdapter(getActivity());

        //布局管理器
        mXrvCart.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mXrvCart.setAdapter(adapter);
        mXrvCart.setLoadingListener(this);

        //请求数据
        mShopCarPresenter=new ShopCarPresenter(new CartCall());
        mXrvCart.refresh();
        adapter.setCartlistener(new ShopCarAdapter.Cartlistener() {
            @Override
            public void GoodsChange() {
                boolean  allshop=adapter.isAllshopselected();
                mCbAll.setChecked(allshop);
                /**
                 * 刷新适配器
                 */
                adapter.notifyDataSetChanged();
                changeprice();
            }

            @Override
            public void NumChange(int index, int num) {
                  adapter.changeshopnum(index,num);
                  changeprice();
            }
        });
    }

    @Override
    public void onRefresh() {
           if (mShopCarPresenter.isRunning()){
               mXrvCart.refreshComplete();
               return;
           }
           mShopCarPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (mShopCarPresenter.isRunning()) {
            mXrvCart.loadMoreComplete();
            return;
        }
        mShopCarPresenter.reqeust(false,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
        }


    class  CartCall implements DataCall<Result<List<ShopCarBean>>>{

    @Override
    public void success(Result<List<ShopCarBean>> data) {
        mXrvCart.refreshComplete();
        mXrvCart.loadMoreComplete();
      if (data.getStatus().equals("0000")){
          adapter.addAll(data.getResult());
          adapter.notifyDataSetChanged();
      }
    }

    @Override
    public void fail(ApiException e) {
      mXrvCart.refreshComplete();
      mXrvCart.loadMoreComplete();
    }

}
    @OnClick({R.id.cb_all, R.id.btn_js})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_all:
                boolean checked = mCbAll.isChecked();
                adapter.setAllshopselected(checked);
                adapter.notifyDataSetChanged();
                changeprice();
                break;
            case R.id.btn_js:
                break;
        }

    }


    private void changeprice() {
        float setsumprice = adapter.setsumprice();
        mCartTxtSumprice.setText("合计：￥" + setsumprice);
    }

}
