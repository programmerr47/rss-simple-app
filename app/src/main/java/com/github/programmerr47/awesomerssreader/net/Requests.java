package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

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
        Observable<AppNewsItem> fromLentaRss = lentaRssObservable.map(new Function<LentaRss, List<LentaNewsItem>>() {
            @Override
            public List<LentaNewsItem> apply(LentaRss lentaRss) throws Exception {
                return lentaRss.getItems();
            }
        }).flatMap(new Function<List<LentaNewsItem>, ObservableSource<LentaNewsItem>>() {
            @Override
            public ObservableSource<LentaNewsItem> apply(List<LentaNewsItem> lentaNewsItems) throws Exception {
                return Observable.fromIterable(lentaNewsItems);
            }
        }).map(new Function<LentaNewsItem, AppNewsItem>() {
            @Override
            public AppNewsItem apply(LentaNewsItem lentaNewsItem) throws Exception {
                return AppNewsItem.create(lentaNewsItem);
            }
        });

        Observable<AppNewsItem> fromGazetaRss = gazetaRssObservable.map(new Function<GazetaRss, List<GazetaNewsItem>>() {
            @Override
            public List<GazetaNewsItem> apply(GazetaRss gazetaRss) throws Exception {
                return gazetaRss.getItems();
            }
        }).flatMap(new Function<List<GazetaNewsItem>, ObservableSource<GazetaNewsItem>>() {
            @Override
            public ObservableSource<GazetaNewsItem> apply(List<GazetaNewsItem> items) throws Exception {
                return Observable.fromIterable(items);
            }
        }).map(new Function<GazetaNewsItem, AppNewsItem>() {
            @Override
            public AppNewsItem apply(GazetaNewsItem gazetaNewsItem) throws Exception {
                return AppNewsItem.create(gazetaNewsItem);
            }
        });

        return Observable.merge(fromLentaRss, fromGazetaRss)
                .toSortedList(new Comparator<AppNewsItem>() {
                    @Override
                    public int compare(AppNewsItem item1, AppNewsItem item2) {
                        long diff = item2.getDate() - item1.getDate();
                        return diff < 0 ? -1 : diff == 0 ? 0 : 1;
                    }
                })
                .toObservable();
    }
}
