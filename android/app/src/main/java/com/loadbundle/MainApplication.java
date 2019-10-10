package com.loadbundle;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.loadbundle.RNBridge.RNBridgePackage;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
              new RNBridgePackage()
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
  }
}
//
//  /**
//   * 下载对应的bundle
//   *
//   * @param bundleName
//   */
//  private void download(final String bundleName) {
//    FileDownloader.setup(this);
//    FileDownloader.getImpl().create("http://192.168.100.14:8080/download/bundle/" + bundleName).setPath(this.getFilesDir().getAbsolutePath(), true)
//
//            .setListener(new FileDownloadListener() {
//              @Override
//              protected void started(BaseDownloadTask task) {
//                super.started(task);
//              }
//
//              @Override
//              protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//              }
//
//              @Override
//              protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//              }
//
//              @Override
//              protected void completed(BaseDownloadTask task) {
//
//                try {
//                  //下载之后解压，然后打开
//                  ZipUtils.unzip(MainActivity.this.getFilesDir().getAbsolutePath() + "/" + bundleName + ".zip", MainActivity.this.getFilesDir().getAbsolutePath());
//
//                  DispatchUtils.dispatchModel = bundleName;
//                  DispatchActivity.start(MainActivity.this);
//
//                } catch (Exception e) {
//                  e.printStackTrace();
//                }
//
//              }
//
//              @Override
//              protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//              }
//
//              @Override
//              protected void error(BaseDownloadTask task, Throwable e) {
//                Log.d(TAG, "error");
//              }
//
//              @Override
//              protected void warn(BaseDownloadTask task) {
//
//              }
//            }).start();
//  }
//}
