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
import android.widget.EditText;

import com.bw.shen.Presenter.PublishCirclePresenter;
import com.bw.shen.adapter.ImageAdapter;
import com.bw.shen.adapter.ImagesAdapter;
import com.bw.shen.bean.Result;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDActivity;
import com.bw.shen.core.exception.ApiException;
import com.bw.shen.fragment.CircleFragment;
import com.bw.shen.utils.StringUtils;
import com.bw.shen.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCircleActivity extends WDActivity implements DataCall<Result> {

    @BindView(R.id.bo_text)
    EditText mText;

//    @BindView(R.id.bo_image)
//    ImageView mImage;

    PublishCirclePresenter presenter;

    @BindView(R.id.bo_image_list)
    RecyclerView mImageList;

    ImagesAdapter mImageAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_circle;
    }

    @Override
    protected void initView() {
        mImageAdapter = new ImagesAdapter();
        mImageAdapter.setSign(1);
        mImageAdapter.add(R.drawable.fb);
        mImageList.setLayoutManager(new GridLayoutManager(this,3));
        mImageList.setAdapter(mImageAdapter);

        presenter = new PublishCirclePresenter(this);
    }

    @Override
    protected void destoryData() {

    }

    @OnClick(R.id.back)
    public void back(){
        finish();
    }

    @OnClick(R.id.send)
    public void publish(){
        presenter.reqeust(LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId(),
                1,mText.getText().toString(),mImageAdapter.getList());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null,requestCode,data);
            if (!StringUtils.isEmpty(filePath)) {
                mImageAdapter.add(filePath);
                mImageAdapter.notifyDataSetChanged();
//                Bitmap bitmap = UIUtils.decodeFile(new File(filePath),200);
//                mImage.setImageBitmap(bitmap);
            }
        }

    }

    @Override
    public void success(Result data) {
        if (data.getStatus().equals("0000")){
            CircleFragment.addCircle = true;
            finish();
        }else{
            UIUtils.showToastSafe(data.getStatus()+"  "+data.getMessage());
        }
    }

    @Override
    public void fail(ApiException e) {
        UIUtils.showToastSafe(e.getCode()+"  "+e.getDisplayMessage());
    }

}
