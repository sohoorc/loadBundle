//package com.loadbundle.react;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//
//import com.facebook.react.ReactActivity;
//import com.facebook.react.ReactActivityDelegate;
//
//import javax.annotation.Nullable;
//
//public class BaseReactActivity extends ReactActivity {
//    @Override
//    protected ReactActivityDelegate createReactActivityDelegate() {
//        String localBundleName = getBundleName();
//        if (!TextUtils.isEmpty(localBundleName)) {
//            JsLoaderUtil.jsState.bundleName = localBundleName;
//        }
//        return new ReactActivityDelegate(this, getMainComponentName()) {
//            @Override
//            protected void onCreate(Bundle savedInstanceState) {
//                JsLoaderUtil.load(getApplication(),
//                        () -> super.onCreate(savedInstanceState));
//            }
//        };
//    }
//
//
//    @Nullable
//    @Override
//    protected String getMainComponentName() {
//        return JsLoaderUtil.jsState.componentName;
//    }
//
//    protected String getBundleName() {
//        return "";
//    }
//
//}