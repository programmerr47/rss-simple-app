package com.github.programmerr47.awesomerssreader.util;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;

/**
 * @author Michael Spitsin
 * @since 2016-11-09
 */
public class AndroidUtils {
    private AndroidUtils() {}

    public static float dimen(@NonNull Context context, @DimenRes int id) {
        return context.getResources().getDimension(id);
    }
}
