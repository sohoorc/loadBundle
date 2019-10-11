

package com.loadbundle;


import android.content.Context;
import android.content.Intent;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.loadbundle.utils.DispatchUtils;


public class SubSystemActivity extends ReactActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SubSystemActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {

        SubSystemDelegate delegate = new SubSystemDelegate(this, DispatchUtils.bundleName,DispatchUtils.bundleFileName);

        return delegate;
    }
}