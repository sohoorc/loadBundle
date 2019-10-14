package com.loadbundle;

import android.annotation.SuppressLint;

import com.facebook.react.ReactActivity;

import javax.annotation.Nullable;

@SuppressLint("Registered")
public class BuzActivity extends ReactActivity{

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "bundleDemo1";
    }

}