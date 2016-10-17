package com.example.administrator.ljz.ui.login;

import android.content.Context;

/**
 * Created by Administrator on 2016/10/15.
 */
public class LoginContract {
    interface ILoginView {
        Context getCurContext();//获取上下文对象,用于保存数据等

        void showProgress();//可以显示进度条

        void hideProgress();//可以隐藏进度条

        void showInfo(String info);//提示用户,提升友好交互

        void showErrorMsg(String msg);//发生错误就显示错误信息

        void toMain();//跳转到主页面

        void toRegister();//跳转到注册页面

        UserInfo getUserLoginInfo();//获取用户登录信息
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface ILoginPresenter {
        void login();//唯一的桥梁就是登录了
    }

    /**
     * 逻辑处理层
     */
    interface ILoginModel {
        void login(UserInfo userInfo, OnHttpCallBack<TokenResult> callBack);//登录

        void saveUserInfo(Context context, UserInfo user, String token);//登录成功就保存用户信息
    }
}
