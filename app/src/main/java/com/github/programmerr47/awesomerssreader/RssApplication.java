package com.github.programmerr47.awesomerssreader;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.github.programmerr47.awesomerssreader.rsslist.BaseRssPresenter;
import com.github.programmerr47.awesomerssreader.rsslist.FetchAllRssInteractor;
import com.github.programmerr47.awesomerssreader.rsslist.Interactor;
import com.github.programmerr47.awesomerssreader.rsslist.RssListPresenterImpl;

public class RssApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public BaseRssPresenter rssPresenter() {
        return new RssListPresenterImpl(new FetchAllRssInteractor());
    }

    public static Context appContext() {
        return appContext;
    }

    public static RssApplication application(Context context) {
        return (RssApplication) context.getApplicationContext();
    }
}
