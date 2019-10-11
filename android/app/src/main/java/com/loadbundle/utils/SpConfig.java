package com.loadbundle.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.loadbundle.MainApplication;


public class SpConfig {

    private SpConfig() {
    }

    public static void setLoadRuntime(boolean isLoadRuntime) {
        saveBoolean("isLoadRuntime", isLoadRuntime);
    }

    public static boolean getLoadRuntime() {
        return getBoolean("isLoadRuntime");
    }

    public static void setIndex(int index) {
        saveInt("index", index);
    }

    public static int getIndex() {
        return getInt("index");
    }


    private static SharedPreferences getSp() {
        return MainApplication.getApp().getSharedPreferences("colonel_tool", Context.MODE_PRIVATE);
    }


    private static String getString(String key) {
        return getSp().getString(key, "");
    }

    private static int getInt(String key) {
        return getSp().getInt(key, 0);
    }

    private static boolean getBoolean(String key) {
        return getSp().getBoolean(key, false);
    }

    private static long getLong(String key) {
        return getSp().getLong(key, 0);
    }

    private static void saveString(String key, String value) {
        getSp().edit().putString(key, value).apply();
    }

    private static void saveInt(String key, int value) {
        getSp().edit().putInt(key, value).apply();
    }

    private static void saveBoolean(String key, boolean value) {
        getSp().edit().putBoolean(key, value).apply();
    }

    private static void saveLong(String key, long value) {
        getSp().edit().putLong(key, value).apply();
    }
}