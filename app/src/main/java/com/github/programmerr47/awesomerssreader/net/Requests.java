package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import io.reactivex.Flowable;

public class Requests {
    private Requests() {}

    public static Flowable<LentaRss> fetchLentaRss() {
        return new UrlRequest<>(LentaRss.class).makeFlowable("https://m.lenta.ru/rss");
    }

    public static Flowable<GazetaRss> fetchGazetaRss() {
        return new UrlRequest<>(GazetaRss.class).makeFlowable("https://m.gazeta.ru/export/rss/lenta.xml");
    }
}
