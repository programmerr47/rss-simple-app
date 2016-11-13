package com.github.programmerr47.awesomerssreader.util.picasso;

import com.squareup.picasso.Picasso;

import lombok.experimental.UtilityClass;

import static com.github.programmerr47.awesomerssreader.RssApplication.appContext;

@UtilityClass
public class PicassoUtil {
    public static Picasso picasso() {
        return Picasso.with(appContext());
    }
}
