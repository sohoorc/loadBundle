package com.loadbundle;

import com.facebook.react.AsyncReactActivity;

public class Buz1Activity extends AsyncReactActivity {
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "bundleTest";
    }


    @Override
    protected String getScriptPath() {
        return "bundleTest20196666.bundle";
    }

    @Override
    protected ScriptType getScriptPathType() {
//        return ScriptType.ASSET;
        return null;
    }

}
