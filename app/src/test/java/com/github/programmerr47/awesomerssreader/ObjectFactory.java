package com.github.programmerr47.awesomerssreader;

import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import io.reactivex.Observable;
import lombok.experimental.UtilityClass;

import static java.util.Arrays.asList;

@UtilityClass
public class ObjectFactory {
    public static Observable<LentaRss> lentaObservable(LentaNewsItem... lentaNewsItems) {
        return Observable.just(lentaRss(lentaNewsItems));
    }

    public static Observable<GazetaRss> gazetaObservable(GazetaNewsItem... gazetaNewsItems) {
        return Observable.just(gazetaRss(gazetaNewsItems));
    }

    @SuppressWarnings("WeakerAccess")
    public static LentaRss lentaRss(LentaNewsItem... lentaNewsItems) {
        return new LentaRss(asList(lentaNewsItems));
    }

    @SuppressWarnings("WeakerAccess")
    public static GazetaRss gazetaRss(GazetaNewsItem... gazetaNewsItems) {
        return new GazetaRss(asList(gazetaNewsItems));
    }

    public static LentaNewsItem lentaItem(String title, String description, String pubDate, String thumbUrl) {
        return new LentaNewsItem(null, title, null, description, pubDate, null, new LentaNewsItem.Enclosure(thumbUrl, 0, null));
    }

    public static GazetaNewsItem gazetaItem(String title, String pubDate, String description, String thumbUrl) {
        return new GazetaNewsItem(title, null, null, pubDate, description, null, new GazetaNewsItem.Enclosure(thumbUrl, null));
    }
}
