package com.bw.shen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bw.shen.bean.UserInfo;
import com.bw.shen.core.db.DaoMaster;
import com.bw.shen.core.db.DaoSession;
import com.bw.shen.core.db.UserInfoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.time)
    TextView txtTime;
//    @BindView(R.id.btn_goto_main)
//    Button btnGotoMain;
    int times = 5;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                if (times == 0){
                    List<UserInfo> logins = dao.loadAll();
                    for (int i = 0; i < logins.size(); i++) {
                        int statues = logins.get(i).getStatus();
                        if (statues == 1){
                            startActivity(new Intent(WelcomeActivity.this,ShowActivity.class));
                            finish();
                            return;
                        }
                    }
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                    return;
                }else{
                    times--;
                    txtTime.setText(times+"s");
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }
        }
    };
    private UserInfoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //查询
        DaoSession daoSession = DaoMaster.newDevSession(WelcomeActivity.this, UserInfoDao.TABLENAME);
        dao = daoSession.getUserInfoDao();

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<UserInfo> loginList = dao.loadAll();
                for (int i = 0; i < loginList.size(); i++) {
                    int statues = loginList.get(i).getStatus();
                    if (statues == 1){
                        startActivity(new Intent(WelcomeActivity.this,ShowActivity.class));
                        finish();
                        return;
                    }
                }
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        });
        //发送线程
        handler.sendEmptyMessageDelayed(1,1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }
//    @OnClick(R.id.btn_goto_main)
//    public void onViewClicked() {
//        List<Login> loginList = dao.loadAll();
//        for (int i = 0; i < loginList.size(); i++) {
//            int statues = loginList.get(i).getStatues();
//            if (statues == 1){
//                startActivity(new Intent(JumpActivity.this,MainActivity.class));
//                finish();
//                return;
//            }
//        }
//        startActivity(new Intent(JumpActivity.this,LoginActivity.class));
//        finish();
//    }

}
