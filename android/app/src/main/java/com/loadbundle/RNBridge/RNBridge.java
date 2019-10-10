// ToastModule.java

package com.loadbundle.RNBridge;

import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class RNBridge extends ReactContextBaseJavaModule {
  private static ReactApplicationContext context;

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";


  public RNBridge(ReactApplicationContext reactContext) {
    super(reactContext);
    context = reactContext;
  }

  @Override
  public String getName() {
    return "RNBridge";
  }

  @ReactMethod
  public void jumpNativePage(String message, int duration) {
//    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }

  @ReactMethod
  public void toast(String message, int duration) {
//    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }

  @ReactMethod
  public void downloadBundle() {

  }
}