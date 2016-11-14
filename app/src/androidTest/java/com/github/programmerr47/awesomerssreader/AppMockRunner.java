package com.github.programmerr47.awesomerssreader;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class AppMockRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // TODO: 14/11/2016 Place mock application here
        return super.newApplication(cl, Application.class.getName(), context);
    }
}
