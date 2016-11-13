package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import java.util.List;

import io.reactivex.Observable;

public class Requests {
    private Requests() {
    }

    public static Observable<List<AppNewsItem>> fetchAllRss() {
        return toAppNewsObservable(fetchLentaRss(), fetchGazetaRss());
    }

    @SuppressWarnings("WeakerAccess")
    public static Observable<LentaRss> fetchLentaRss() {
        return new UrlRequest<>(LentaRss.class).makeObservable("https://m.lenta.ru/rss");
    }

    @SuppressWarnings("WeakerAccess")
    public static Observable<GazetaRss> fetchGazetaRss() {
        return new UrlRequest<>(GazetaRss.class).makeObservable("https://m.gazeta.ru/export/rss/lenta.xml");
    }

    @SuppressWarnings("WeakerAccess")
    public static Observable<List<AppNewsItem>> toAppNewsObservable(Observable<LentaRss> lentaRssObservable, Observable<GazetaRss> gazetaRssObservable) {
        Observable<AppNewsItem> fromLentaRss = lentaRssObservable
                .map(LentaRss::getItems)
                .flatMap(Observable::fromIterable)
                .map(AppNewsItem::create);
        Observable<AppNewsItem> fromGazetaRss = gazetaRssObservable
                .map(GazetaRss::getItems)
                .flatMap(Observable::fromIterable)
                .map(AppNewsItem::create);

        return Observable.merge(fromLentaRss, fromGazetaRss)
                .toSortedList((item1, item2) -> Long.valueOf(item2.getDate()).compareTo(item1.getDate()))
                .toObservable();
    }
}
