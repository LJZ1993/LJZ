package com.example.administrator.ljz.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ljz.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/15.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.ILoginView {
    LoginPresenter mLoginPresenter;
    @InjectView(R.id.m_login_bt1)
    Button mLoginBt1;
    @InjectView(R.id.m_login_bt2)
    Button mLoginBt2;
    @InjectView(R.id.m_login_tv)
    TextView mLoginTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public Context getCurContext() {
        return this;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showInfo(String info) {
        //从p层中获取到底数据进行显示
        Toast.makeText(this, info, Toast.LENGTH_LONG);
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG);

    }

    @Override
    public void toMain() {

    }

    @Override
    public void toRegister() {

    }

    //获取V层数据交给P层，P层再交给M层处理
    @Override
    public UserInfo getUserLoginInfo() {
        return new UserInfo("", "");
    }

    @OnClick({R.id.m_login_bt1, R.id.m_login_bt2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.m_login_bt1:
                mLoginPresenter.login();//调用这个方法做什么？
                break;
            case R.id.m_login_bt2:
                break;
        }
    }
}
