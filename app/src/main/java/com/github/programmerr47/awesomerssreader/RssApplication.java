package com.github.programmerr47.awesomerssreader;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class RssApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context appContext() {
        return appContext;
    }
}
