package com.github.programmerr47.awesomerssreader.net;

import android.test.suitebuilder.annotation.LargeTest;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import org.junit.Test;

import java.util.List;

import io.reactivex.observers.TestObserver;

public class RssCheckTest {
    @Test
    @LargeTest
    public void checkLentaOnlineRss() throws Exception {
        TestObserver<LentaRss> testObserver = new TestObserver<>();
        Requests.fetchLentaRss().subscribe(testObserver);

        testObserver.assertNoErrors()
                .assertValue(lentaRss -> lentaRss != null)
                .assertValue(lentaRss -> lentaRss.items().size() > 0)
                .assertValue(lentaRss -> isContentNotNull(lentaRss.items()));
    }

    @Test
    @LargeTest
    public void checkGazetaOnlineRss() throws Exception {
        TestObserver<GazetaRss> testObserver = new TestObserver<>();
        Requests.fetchGazetaRss().subscribe(testObserver);

        testObserver.assertNoErrors()
                .assertValue(gazetaRss -> gazetaRss != null)
                .assertValue(gazetaRss -> gazetaRss.items().size() > 0)
                .assertValue(gazetaRss -> isContentNotNull(gazetaRss.items()));
    }

    @Test
    @LargeTest
    public void checkAllOnlineRss() throws Exception {
        TestObserver<List<AppNewsItem>> testObserver = new TestObserver<>();
        Requests.fetchAllRss().subscribe(testObserver);
        testObserver.assertNoErrors()
                .assertValue(appNewsItems -> appNewsItems != null)
                .assertValue(appNewsItems -> appNewsItems.size() > 0)
                .assertValue(this::isContentNotNull);
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
