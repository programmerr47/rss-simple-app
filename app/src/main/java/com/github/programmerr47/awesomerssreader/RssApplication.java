package com.github.programmerr47.awesomerssreader;

import android.app.Application;
import android.content.Context;

/**
 * @author Michael Spitsin
 * @since 2016-11-07
 */
public class RssApplication extends Application {
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
