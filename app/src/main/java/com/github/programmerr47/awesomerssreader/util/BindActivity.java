package com.github.programmerr47.awesomerssreader.util;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

public abstract class BindActivity extends AppCompatActivity {
    @SuppressWarnings("unchecked")
    protected <T> T bind(@IdRes int id) {
        return (T) findViewById(id);
    }
}
