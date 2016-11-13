package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import org.reactivestreams.Publisher;

import java.util.Comparator;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Requests {
    private Requests() {}

    public static Flowable<List<AppNewsItem>> fetchAllRss() {
        return toAppNewsFlowable(fetchLentaRss(), fetchGazetaRss());
    }

    public static Flowable<LentaRss> fetchLentaRss() {
        return new UrlRequest<>(LentaRss.class).makeFlowable("https://m.lenta.ru/rss");
    }

    public static Flowable<GazetaRss> fetchGazetaRss() {
        return new UrlRequest<>(GazetaRss.class).makeFlowable("https://m.gazeta.ru/export/rss/lenta.xml");
    }

    public static Flowable<List<AppNewsItem>> toAppNewsFlowable(Flowable<LentaRss> lentaRssFlowable, Flowable<GazetaRss> gazetaRssFlowable) {
        Flowable<AppNewsItem> fromLentaRss = lentaRssFlowable.map(new Function<LentaRss, List<LentaNewsItem>>() {
            @Override
            public List<LentaNewsItem> apply(LentaRss lentaRss) throws Exception {
                return lentaRss.getItems();
            }
        }).flatMap(new Function<List<LentaNewsItem>, Publisher<LentaNewsItem>>() {
            @Override
            public Publisher<LentaNewsItem> apply(List<LentaNewsItem> lentaNewsItems) throws Exception {
                return Flowable.fromIterable(lentaNewsItems);
            }
        }).map(new Function<LentaNewsItem, AppNewsItem>() {
            @Override
            public AppNewsItem apply(LentaNewsItem lentaNewsItem) throws Exception {
                return AppNewsItem.create(lentaNewsItem);
            }
        });

        Flowable<AppNewsItem> fromGazetaRss = gazetaRssFlowable.map(new Function<GazetaRss, List<GazetaNewsItem>>() {
            @Override
            public List<GazetaNewsItem> apply(GazetaRss gazetaRss) throws Exception {
                return gazetaRss.getItems();
            }
        }).flatMap(new Function<List<GazetaNewsItem>, Publisher<GazetaNewsItem>>() {
            @Override
            public Publisher<GazetaNewsItem> apply(List<GazetaNewsItem> items) throws Exception {
                return Flowable.fromIterable(items);
            }
        }).map(new Function<GazetaNewsItem, AppNewsItem>() {
            @Override
            public AppNewsItem apply(GazetaNewsItem gazetaNewsItem) throws Exception {
                return AppNewsItem.create(gazetaNewsItem);
            }
        });

        return Flowable.merge(fromLentaRss, fromGazetaRss)
                .toSortedList(new Comparator<AppNewsItem>() {
                    @Override
                    public int compare(AppNewsItem item1, AppNewsItem item2) {
                        long diff = item2.getDate() - item1.getDate();
                        return diff < 0 ? -1 : diff == 0 ? 0 : 1;
                    }
                })
                .toFlowable();
    }
}
