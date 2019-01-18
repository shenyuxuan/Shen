package com.bw.shen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.shen.adapter.DetailAdapter;
import com.bw.shen.bean.DetailBean;
import com.bw.shen.bean.SynchronousBean;
import com.bw.shen.detailpagemvp.presenter.DetailPresenter;
import com.bw.shen.detailpagemvp.view.DetailView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailView{

    @BindView(R.id.details_viewpager_show)
    ViewPager mDetailsViewpagerShow;
    @BindView(R.id.details_textview_shownum)
    TextView mDetailsTextviewShownum;
    @BindView(R.id.details_textview_sprice)
    TextView mDetailsTextviewSprice;
    @BindView(R.id.details_textview_sold)
    TextView mDetailsTextviewSold;
    @BindView(R.id.details_textview_title)
    TextView mDetailsTextviewTitle;
    @BindView(R.id.details_textview_Weight)
    TextView mDetailsTextviewWeight;
    @BindView(R.id.details_Image_details)
    SimpleDraweeView mDetailsImageDetails;
    @BindView(R.id.details_textview_describe)
    TextView mDetailsTextviewDescribe;
    @BindView(R.id.details_Image_describe)
    SimpleDraweeView mDetailsImageDescribe;
    @BindView(R.id.details_recview_comments)
    RecyclerView mDetailsRecviewComments;
    @BindView(R.id.details_textview_comments)
    TextView mDetailsTextviewComments;
    @BindView(R.id.details_scroll_changecolor)
    MyScrollView mDetailsScrollChangecolor;
    @BindView(R.id.details_image_return)
    ImageView mDetailsImageReturn;
    @BindView(R.id.details_text_goodsT)
    TextView mDetailsTextGoodsT;
    @BindView(R.id.details_text_detailsT)
    TextView mDetailsTextDetailsT;
    @BindView(R.id.details_text_commentsT)
    TextView mDetailsTextCommentsT;
    @BindView(R.id.details_text_goods)
    TextView mDetailsTextGoods;
    @BindView(R.id.details_text_details)
    TextView mDetailsTextDetails;
    @BindView(R.id.details_text_comments)
    TextView mDetailsTextComments;
    @BindView(R.id.details_relative_changer)
    RelativeLayout mDetailsRelativeChanger;
    @BindView(R.id.details_relat_changecolor)
    RelativeLayout mDetailsRelatChangecolor;
    @BindView(R.id.details_relative_addshoppingcar)
    RelativeLayout mDetailsRelativeAddshoppingcar;
    @BindView(R.id.details_relative_pay)
    RelativeLayout mDetailsRelativePay;
    private DetailPresenter mDetailPresenter;
    private int mCount;
    private int mCommodityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);

        final int userId = sp.getInt("userId", 0);
        final String sessionId = sp.getString("sessionId", "");
        Intent intent = getIntent();
        mCommodityId = intent.getIntExtra("commodityId", 0);
        mDetailsViewpagerShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mDetailsTextviewShownum.setText((position + 1) + "/" + mCount);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        Toast.makeText(this,""+commodityId,Toast.LENGTH_SHORT).show();

        mDetailPresenter = new DetailPresenter();
        mDetailPresenter.attach(this);

        mDetailPresenter.get(userId,sessionId, mCommodityId);


        mDetailsRelativeAddshoppingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    @Override
    public void onSuccessDetail(DetailBean detailBean) {
        String picture = detailBean.getResult().getPicture();
        final String[] split = picture.split("\\,");

        DetailBean.ResultBean list = detailBean.getResult();

//        Toast.makeText(this,""+list.getCategoryName(),Toast.LENGTH_SHORT).show();

        DetailAdapter adapter = new DetailAdapter(split, this);

        String categoryName = list.getCategoryName();
        double price = list.getPrice();
        int commentNum = list.getSaleNum();
        String describe = list.getDescribe();
        String details = list.getDetails();
        mDetailsTextviewSprice.setText("￥" + price);
        mDetailsTextviewTitle.setText(categoryName);

        mDetailsTextviewDescribe.setText(describe);
        mDetailsTextviewComments.setText(details);

        Toast.makeText(this,""+commentNum,Toast.LENGTH_SHORT).show();

        mDetailsTextviewShownum.setText("已售"+commentNum+"件");

        mCount = adapter.getCount();

        mDetailsImageDetails.setImageURI(split[0]);
        mDetailsImageDescribe.setImageURI(split[1]);


        mDetailsViewpagerShow.setAdapter(adapter);

    }

    @Override
    public void synchronous(SynchronousBean bean) {
        Toast.makeText(this,""+bean.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(Exception e) {

    }
}
