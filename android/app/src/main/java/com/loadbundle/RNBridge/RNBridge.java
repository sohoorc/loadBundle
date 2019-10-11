// ToastModule.java

package com.loadbundle.RNBridge;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.loadbundle.SubSystemActivity;
import com.loadbundle.utils.DispatchUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static android.content.ContentValues.TAG;


public class RNBridge extends ReactContextBaseJavaModule {
  private static ReactApplicationContext context;

//  private static final String SubSystemActivity = "SubSystemActivity";
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
  public void jumpNativePage(String url, String bundleName) throws Exception {
    DispatchUtils.bundleName = bundleName;
    DispatchUtils.bundleUrl = url;
    SubSystemActivity.start(context);
    // 检查是否下载过，如果已经下载过则直接打开
//    String f = context.getFilesDir().getAbsolutePath() + "/" + bundleName;
    Log.d(TAG, "url是--------"+url);
//    downLoad(url,context);
//    File file = new File((f));
//    if (file.exists()) {
//      DispatchUtils.dispatchModel = bundle.name;
//      DispatchActivity.start(MainActivity.this);
//    } else {
//      download(bundle.name);
//    }
//    Toast.makeText(getReactApplicationContext(), bundleName, duration).show();
  }

  @ReactMethod
  public void toast(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }


  @ReactMethod
  public void downloadBundle() {

  }

  public void downLoad(String path, Context context)throws Exception
  {
    URL url = new URL(path);
    InputStream is = url.openStream();
    //截取最后的文件名
    String end = path.substring(path.lastIndexOf("."));
    //打开手机对应的输出流,输出到文件中
    OutputStream os = context.openFileOutput("Cache_"+System.currentTimeMillis()+end, Context.MODE_PRIVATE);
    byte[] buffer = new byte[1024];
    int len = 0;
    //从输入六中读取数据,读到缓冲区中
    while((len = is.read(buffer)) > 0)
    {
      os.write(buffer,0,len);
    }
    //关闭输入输出流
    is.close();
    os.close();
  }
}