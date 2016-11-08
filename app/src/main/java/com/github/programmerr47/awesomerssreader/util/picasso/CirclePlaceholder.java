package com.github.programmerr47.awesomerssreader.util.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import static com.github.programmerr47.awesomerssreader.util.picasso.CircleTransformation.circleTransformation;

public enum CirclePlaceholder {
    INSTANCE;

    private final LruCache<Integer, Drawable> cachedHolders = new LruCache<>(10);

    public static CirclePlaceholder circlePlaceholder() {
        return CirclePlaceholder.INSTANCE;
    }

    public Drawable get(@NonNull Context context, @DrawableRes int drawableId) {
        Drawable cached = cachedHolders.get(drawableId);
        if (cached == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
            cached = new BitmapDrawable(context.getResources(), circleTransformation().transform(bitmap));
            cachedHolders.put(drawableId, cached);
        }
        return cached;
    }
}
