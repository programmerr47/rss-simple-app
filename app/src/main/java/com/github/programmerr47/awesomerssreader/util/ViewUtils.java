package com.github.programmerr47.awesomerssreader.util;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

public class ViewUtils {
    private ViewUtils() {}

    @SuppressWarnings("unchecked")
    public static <T> T bind(@NonNull View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }
}
