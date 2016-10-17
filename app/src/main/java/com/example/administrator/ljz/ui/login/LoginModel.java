package com.example.administrator.ljz.ui.login;

import android.content.Context;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/15.
 */
public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public void login(UserInfo userInfo, final OnHttpCallBack<TokenResult> callBack) {
        Observable<UserHttpResult<TokenResult>> observable = new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .userLogin("", "");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult<TokenResult>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFaild(e.toString());

                    }

                    @Override
                    public void onNext(UserHttpResult<TokenResult> tokenResultUserHttpResult) {
                        callBack.onSuccessful(tokenResultUserHttpResult.getData());
                    }
                });

    }

    @Override
    public void saveUserInfo(Context context, UserInfo user, String token) {

    }
}
