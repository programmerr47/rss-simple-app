package com.github.programmerr47.awesomerssreader;

import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import io.reactivex.Flowable;

import static java.util.Arrays.asList;

/**
 * @author Michael Spitsin
 * @since 2016-11-12
 */
public class ObjectFactory {
    private ObjectFactory() {}

    public static Flowable<LentaRss> lentaFlowable(LentaNewsItem... lentaNewsItems) {
        return Flowable.fromArray(lentaRss(lentaNewsItems));
    }

    public static Flowable<GazetaRss> gazetaFlowable(GazetaNewsItem... gazetaNewsItems) {
        return Flowable.fromArray(gazetaRss(gazetaNewsItems));
    }

    public static LentaRss lentaRss(LentaNewsItem... lentaNewsItems) {
        return new LentaRss(asList(lentaNewsItems));
    }

    public static GazetaRss gazetaRss(GazetaNewsItem... gazetaNewsItems) {
        return new GazetaRss(asList(gazetaNewsItems));
    }

    public static LentaNewsItem lentaItem(String title, String description, String pubDate, String thumbUrl) {
        return new LentaNewsItem(null, title, null, description, pubDate, null, thumbUrl);
    }

    public static GazetaNewsItem gazetaItem(String title, String pubDate, String description, String thumbUrl) {
        return new GazetaNewsItem(title, null, null, pubDate, description, null, thumbUrl);
    }
}
