/**
 * RNBridge桥接文件
 * 用于将自定义模块桥接到RN
 */


package com.loadbundle.RNBridge;

import java.util.Collections;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class RNBridgePackage implements ReactPackage {
  @Nonnull
  @Override
  public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
      List<NativeModule> modules = new ArrayList<>();
      modules.add(new RNBridge(reactContext)); // 将新建的 MyModule 实例加入到 List 中完成注册
      return modules;
  }

  @Nonnull
  @Override
  public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
      return Collections.emptyList();
  }
}