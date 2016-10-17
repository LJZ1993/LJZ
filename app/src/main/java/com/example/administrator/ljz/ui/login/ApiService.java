package com.example.administrator.ljz.ui.login;

import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/15.
 */
public interface ApiService {
    /**
     * 用户登录的接口
     *
     * @param username 用户名
     * @param pwd      密码
     * @return RxJava 对象
     */
    @POST("okhttp/UserInfoServlert")
    Observable<UserHttpResult<TokenResult>> userLogin(@Query("username") String username, @Query("pwd") String pwd);
}
