package com.github.programmerr47.awesomerssreader;

import android.test.suitebuilder.annotation.LargeTest;

import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;
import com.github.programmerr47.awesomerssreader.net.UrlRequest;

import org.junit.Assert;
import org.junit.Test;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;

public class RssCheckTest {
    @Test
    @LargeTest
    public void checkLentaOnlineRss() throws Exception {
        UrlRequest<LentaRss> request = new UrlRequest<>(LentaRss.class);
        Flowable<LentaRss> flowable = request.makeFlowable("https://m.lenta.ru/rss");
        TestSubscriber<LentaRss> testSubscriber = new TestSubscriber<>();
        flowable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors().assertValue(new Predicate<LentaRss>() {
            @Override
            public boolean test(LentaRss lentaRss) throws Exception {
                return lentaRss != null;
            }
        }).assertValue(new Predicate<LentaRss>() {
            @Override
            public boolean test(LentaRss lentaRss) throws Exception {
                return lentaRss.getItems().size() > 0;
            }
        }).assertValue(new Predicate<LentaRss>() {
            @Override
            public boolean test(LentaRss lentaRss) throws Exception {
                return isContentNotNull(lentaRss.getItems());
            }
        });
    }

    @Test
    @LargeTest
    public void checkGazetaOnlineRss() throws Exception {
        UrlRequest<GazetaRss> request = new UrlRequest<>(GazetaRss.class);
        Flowable<GazetaRss> flowable = request.makeFlowable("https://m.gazeta.ru/export/rss/lenta.xml");
        TestSubscriber<GazetaRss> testSubscriber = new TestSubscriber<>();
        flowable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors().assertValue(new Predicate<GazetaRss>() {
            @Override
            public boolean test(GazetaRss gazetaRss) throws Exception {
                return gazetaRss != null;
            }
        }).assertValue(new Predicate<GazetaRss>() {
            @Override
            public boolean test(GazetaRss gazetaRss) throws Exception {
                return gazetaRss.getItems().size() > 0;
            }
        }).assertValue(new Predicate<GazetaRss>() {
            @Override
            public boolean test(GazetaRss gazetaRss) throws Exception {
                return isContentNotNull(gazetaRss.getItems());
            }
        });
    }

    private boolean isContentNotNull(Iterable<?> iterable) {
        for (Object item : iterable) {
            if (item == null) {
                return false;
            }
        }

        return true;
    }
}
