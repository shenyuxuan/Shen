package com.bw.shen;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.shen.Presenter.LoginPresenter;
import com.bw.shen.bean.Result;
import com.bw.shen.bean.UserInfo;
import com.bw.shen.core.DataCall;
import com.bw.shen.core.WDActivity;
import com.bw.shen.core.WDApplication;
import com.bw.shen.core.db.DaoMaster;
import com.bw.shen.core.db.UserInfoDao;
import com.bw.shen.core.exception.ApiException;
import com.bw.shen.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2019-1-7.
 */

public class LoginActivity extends WDActivity {

    LoginPresenter requestPresenter;
    @BindView(R.id.login_mobile)
    EditText mMobile;

    @BindView(R.id.login_pas)
    EditText mPas;

    @BindView(R.id.login_rem_pas)
    CheckBox mRemPas;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void destoryData() {
        requestPresenter.unBind();
    }

    @Override
    protected void initView() {
        requestPresenter = new LoginPresenter(new LoginCall());
//        WDApplication.getShare().getBoolean("remPas",false);
        boolean remPas = false;
        if (remPas){
            mRemPas.setChecked(true);
            mMobile.setText(WDApplication.getShare().getString("mobile",""));
            mPas.setText(WDApplication.getShare().getString("pas",""));
        }
    }

    @OnClick(R.id.login_btn)
    public void login(){
        String m = mMobile.getText().toString();
        String p = mPas.getText().toString();
        if (TextUtils.isEmpty(m)){
            Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(p)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
            return;
        }
        if (mRemPas.isChecked()){
            WDApplication.getShare().edit().putString("mobile",m)
                    .putString("pas",p).commit();
        }
        mLoadDialog.show();


        requestPresenter.reqeust(m,p);
    }

    @OnClick(R.id.login_rem_pas)
    public void remPas(){
        WDApplication.getShare().edit()
                .putBoolean("remPas",mRemPas.isChecked()).commit();
    }

    private boolean pasVisibile = false;

    @OnClick(R.id.login_pas_eye)
    public void eyePas(){
        if (pasVisibile){//密码显示，则隐藏
            mPas.setTransformationMethod(PasswordTransformationMethod.getInstance());
            pasVisibile = false;
        }else{//密码隐藏则显示
            mPas.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            pasVisibile = true;
        }
    }

    @OnClick(R.id.register_text)
    public void register(){
        intent(RegisterActivity.class);
    }


    /**
     * 登录
     */
    class LoginCall implements DataCall<Result<UserInfo>> {

        @Override
        public void success(Result<UserInfo> result) {
            mLoadDialog.cancel();
            if (result.getStatus().equals("0000")){
                result.getResult().setStatus(1);//设置登录状态，保存到数据库
                UserInfoDao userInfoDao = DaoMaster.newDevSession(getBaseContext(),UserInfoDao.TABLENAME).getUserInfoDao();
                userInfoDao.insertOrReplace(result.getResult());
                intent(ShowActivity.class);
                finish();
            }else{
                UIUtils.showToastSafe(result.getStatus()+"  "+result.getMessage());
            }
            //result.getData().setStatus(1);设置用户登录状态为1
            //userdao.insertOrReplace(result.getData());保存用户数据
            //跳转页面
        }

        @Override
        public void fail(ApiException e) {
            mLoadDialog.cancel();
            UIUtils.showToastSafe(e.getCode()+" "+e.getDisplayMessage());
        }
    }
}
