package com.loadbundle;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class SubSystemDelegate extends ReactActivityDelegate {

    private Activity activity;
    private String bundleName;


    public SubSystemDelegate(Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
        this.activity = activity;
        this.bundleName = mainComponentName;
    }

    @Override
    protected ReactNativeHost getReactNativeHost() {

        ReactNativeHost mReactNativeHost = new ReactNativeHost(activity.getApplication()) {
            @Override
            public boolean getUseDeveloperSupport() {
                return BuildConfig.DEBUG;
            }

            @Override
            protected List<ReactPackage> getPackages() {
                return Arrays.<ReactPackage>asList(
                        new MainReactPackage()
                );
            }

            @Nullable
            @Override
            protected String getJSBundleFile() {
                String file = activity.getFilesDir().getAbsolutePath()  + "/" + bundleName + ".bundle";
//                String file = activity.getFilesDir().getAbsolutePath() + "/test.bundle.bundle";
//                String file = "http://10.10.1.155:5000/main.bundle.js";
                Log.d(TAG, "文件路径是--------"+file);
                return file;
            }

            @Nullable
            @Override
            protected String getBundleAssetName() {
                Log.d(TAG, "getBundleAssetName: "+bundleName);
                return bundleName + ".bundle";
//                return "test.bundle.bundle";
            }

            @Override
            protected String getJSMainModuleName() {
                Log.d(TAG, "getJSMainModuleName: "+bundleName);
                return "index";
//                return bundleName;
            }
        };
        return mReactNativeHost;
    }
}