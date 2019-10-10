package com.loadbundle;

import android.app.Activity;

import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

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

                String file = activity.getFilesDir().getAbsolutePath() + "/" + bundleName + "/" + bundleName + ".bundle";
//                String file = "http://10.10.1.155:5000/bundle/main.bundle.js";
                return file;
            }

            @Nullable
            @Override
            protected String getBundleAssetName() {
                return bundleName + ".bundle";
//                return "loadBundle";
            }

            @Override
            protected String getJSMainModuleName() {
//                return "index";
                return "loadBundle";
            }
        };
        return mReactNativeHost;
    }
}