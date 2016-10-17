package com.example.administrator.ljz.clearEditText;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/10/15.
 */
public class ClearEditText extends EditText implements OnFocusChangeListener, TextWatcher {
    Drawable[] mDrawableList;
    Drawable mDrawable;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDrawableList = getCompoundDrawables();
        int length = mDrawableList.length;
        mDrawable = mDrawableList[2];
        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(android.R.drawable.ic_btn_speak_now);
        }
        mDrawable.setBounds(0,0,mDrawable.getIntrinsicWidth(),mDrawable.getIntrinsicHeight());
        //TextWarcher对应的方法，就如btn.setOnclickListener一样
        addTextChangedListener(this);
        setOnFocusChangeListener(this);
        //设置右边图片的可见性，默认为false
        setDelectIconVisible(false);
    }

    private void setDelectIconVisible(boolean visible) {
        Drawable drawable = visible ? mDrawable : null;
        setCompoundDrawables(mDrawableList[0], mDrawableList[1], drawable, mDrawableList[3]);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            //有焦点时并且输入框中有内容时显示
            setDelectIconVisible(this.getText().length() > 0);
        } else {
            setDelectIconVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}
