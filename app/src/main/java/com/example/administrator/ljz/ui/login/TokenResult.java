package com.example.administrator.ljz.ui.login;

/**
 * Created by Administrator on 2016/10/15.
 */
public class TokenResult {
    private String token;

    public TokenResult(String result) {
        this.token = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenResult{" +
                "token='" + token + '\'' +
                '}';
    }
}
