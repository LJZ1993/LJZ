package com.example.administrator.ljz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.ljz.bottomloading.BottomLoadingActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "MainActivity-------";
    @InjectView(R.id.m_main_tv)
    TextView mMainTv;
    @InjectView(R.id.m_main_bt)
    Button mMainBt;
    @InjectView(R.id.m_main_et1)
    EditText mMainEt1;
    @InjectView(R.id.m_main_et2)
    EditText mMainEt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mMainTv.setOnFocusChangeListener(this);
        mMainBt.setOnFocusChangeListener(this);
        mMainEt1.setOnFocusChangeListener(this);

    }

    @OnClick(R.id.m_main_bt)
    public void onClick() {
        startActivity(new Intent(MainActivity.this, BottomLoadingActivity.class));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.m_main_bt:
                Log.w(TAG, "-------onFocusChangeListener-----m_main_bt" + hasFocus);
                break;
            case R.id.m_main_tv:
                Log.w(TAG, "-------onFocusChangeListener-----m_main_tv" + hasFocus);

                break;
            case R.id.m_main_et1:
                Log.w(TAG, "-------onFocusChangeListener-----m_main_et1" + hasFocus);

                break;
            case R.id.m_main_et2:
                Log.w(TAG, "-------onFocusChangeListener-----m_main_et2" + hasFocus);

                break;
        }

    }
}
