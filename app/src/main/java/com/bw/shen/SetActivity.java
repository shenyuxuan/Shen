package com.bw.shen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.bw.shen.core.WDActivity;
import com.bw.shen.core.db.DaoMaster;
import com.bw.shen.core.db.DaoSession;
import com.bw.shen.core.db.UserInfoDao;

import butterknife.BindView;
import butterknife.OnClick;

public class SetActivity extends WDActivity {


    @BindView(R.id.btn_set)
    Button btnSet;
    private UserInfoDao loginDao;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }

    @OnClick(R.id.btn_set)
    public void onViewClicked() {
        DaoSession daoSession = DaoMaster.newDevSession(this, UserInfoDao.TABLENAME);
        loginDao = daoSession.getUserInfoDao();
        loginDao.delete(LOGIN_USER);
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
