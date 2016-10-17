package com.example.administrator.ljz.ui.login;

import android.app.Activity;

/**
 * Created by Administrator on 2016/10/15.
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {
    public LoginContract.ILoginView mILoginView;
    public LoginContract.ILoginModel mILoginModel;
    public LoginPresenter(LoginContract.ILoginView iLoginView) {
        //子--》f父 不用强制类型转行
        //父--》子  强制类型转换
        this.mILoginView=iLoginView;
        mILoginModel=new LoginModel();
    }

    @Override
    public void login() {
        mILoginView.showProgress();
        mILoginModel.login(mILoginView.getUserLoginInfo(), new OnHttpCallBack<TokenResult>() {
            @Override
            public void onSuccessful(TokenResult tokenResult) {

            }

            @Override
            public void onFaild(String errorMsg) {

            }
        });

    }
}
