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
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.loadbundle.MainActivity;
import com.loadbundle.SubSystemActivity;
import com.loadbundle.utils.DispatchUtils;
import com.loadbundle.utils.ZipUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
      DispatchUtils.bundleUrl = url;
      DispatchUtils.bundleName = bundleName;
//    SubSystemActivity.start(context);
    // 检查是否下载过，如果已经下载过则直接打开
    String f = context.getFilesDir().getAbsolutePath() + "/" + bundleName;
    this.dlBack(url,context);
    File file = new File((f));
    if (file.exists()) {
        SubSystemActivity.start(context);
    } else {
        this.download(bundleName,url);
    }
//    Toast.makeText(getReactApplicationContext(), bundleName, duration).show();

  }

  @ReactMethod
  public void toast(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }

    public static List<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){Log.e("error","空目录");return null;}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            s.add(files[i].getAbsolutePath());
            Log.d(TAG,"文件目录中包含文件"+files[i].getAbsolutePath());
        }
        return s;
    }

  @ReactMethod
  public void downloadBundle() {

  }

  /**
   * 下载对应的bundle
   *
   * @param bundleName
   */
  private void download(final String bundleName,String url) {
      String downloadUrl = url+"/"+bundleName+".bundle.zip";

    FileDownloader.setup(context);
      Log.d(TAG, "---------url是--------"+downloadUrl);
      this.getFilesAllName(context.getFilesDir().getAbsolutePath());

    FileDownloader.getImpl().create(downloadUrl).setPath(context.getFilesDir().getAbsolutePath(), true)

            .setListener(new FileDownloadListener() {
              @Override
              protected void started(BaseDownloadTask task) {
                super.started(task);
                Log.d(TAG,"开始下载数据----------------");
              }

              @Override
              protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

              }

              @Override
              protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

              }

              @Override
              protected void completed(BaseDownloadTask task) {

                try {
                  //下载之后解压，然后打开
                  ZipUtils.unzip(context.getFilesDir().getAbsolutePath() + "/" + bundleName + ".bundle.zip",context.getFilesDir().getAbsolutePath());
                    Log.d(TAG,"--------数据下载完成----------------");

                  DispatchUtils.bundleName = bundleName;
                  SubSystemActivity.start(context);

                } catch (Exception e) {
                  e.printStackTrace();
                }

              }

              @Override
              protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

              }

              @Override
              protected void error(BaseDownloadTask task, Throwable e) {
                Log.d(TAG, "error");
              }

              @Override
              protected void warn(BaseDownloadTask task) {

              }
            }).start();
  }

    public void dlBack(String path, Context context)throws Exception
    {
        URL url = new URL(path);
        InputStream is = url.openStream();
        //截取最后的文件名
        String end = path.substring(path.lastIndexOf("."));
        //打开手机对应的输出流,输出到文件中
        OutputStream os = context.openFileOutput("bundleTest.bundle", Context.MODE_PRIVATE);
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