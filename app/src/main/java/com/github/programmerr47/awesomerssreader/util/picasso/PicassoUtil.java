package com.github.programmerr47.awesomerssreader.util.picasso;

import com.squareup.picasso.Picasso;

import static com.github.programmerr47.awesomerssreader.RssApplication.appContext;

/**
 * @author Michael Spitsin
 * @since 2016-11-07
 */
public class PicassoUtil {
    private PicassoUtil() {}

    public static Picasso picasso() {
        return Picasso.with(appContext());
    }
}
