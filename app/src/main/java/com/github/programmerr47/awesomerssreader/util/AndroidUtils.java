package com.github.programmerr47.awesomerssreader.util;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AndroidUtils {
    public static float dimen(@NonNull Context context, @DimenRes int id) {
        return context.getResources().getDimension(id);
    }
}
