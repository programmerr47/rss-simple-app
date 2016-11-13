package com.github.programmerr47.awesomerssreader.util;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("WeakerAccess")
public class ViewUtils {
    @SuppressWarnings("unchecked")
    public static <T> T bind(@NonNull View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }
}
