package com.github.programmerr47.awesomerssreader.net;

import android.test.suitebuilder.annotation.LargeTest;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import org.junit.Test;

import java.util.List;

import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;

public class RssCheckTest {
    @Test
    @LargeTest
    public void checkLentaOnlineRss() throws Exception {
        TestSubscriber<LentaRss> testSubscriber = new TestSubscriber<>();
        Requests.fetchLentaRss().subscribe(testSubscriber);

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
        TestSubscriber<GazetaRss> testSubscriber = new TestSubscriber<>();
        Requests.fetchGazetaRss().subscribe(testSubscriber);

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

    @Test
    @LargeTest
    public void checkAllOnlineRss() throws Exception {
        TestSubscriber<List<AppNewsItem>> testSubscriber = new TestSubscriber<>();
        Requests.fetchAllRss().subscribe(testSubscriber);
        testSubscriber.assertNoErrors().assertValue(new Predicate<List<AppNewsItem>>() {
            @Override
            public boolean test(List<AppNewsItem> appNewsItems) throws Exception {
                return appNewsItems != null;
            }
        }).assertValue(new Predicate<List<AppNewsItem>>() {
            @Override
            public boolean test(List<AppNewsItem> appNewsItems) throws Exception {
                return appNewsItems.size() > 0;
            }
        }).assertValue(new Predicate<List<AppNewsItem>>() {
            @Override
            public boolean test(List<AppNewsItem> appNewsItems) throws Exception {
                return isContentNotNull(appNewsItems);
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
