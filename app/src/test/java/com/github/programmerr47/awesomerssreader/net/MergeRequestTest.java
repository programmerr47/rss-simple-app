package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static java.util.Arrays.asList;

/**
 * @author Michael Spitsin
 * @since 2016-11-12
 */
public class MergeRequestTest {

    @Test
    public void simpleMergingTwoRss() {
        Flowable<LentaRss> lentaFlowable = lentaFlowable(
                lentaItem("Lenta Test1", "Test description lenta 1", "Sat, 12 Nov 2016 11:21:02 +0300", null),
                lentaItem("Lenta Test2", "Test description lenta 2", "Sat, 12 Nov 2016 13:21:02 +0300", null));

        Flowable<GazetaRss> gazetaFlowable = gazetaFlowable(
                gazetaItem("Gazeta Test1", "Sat, 12 Nov 2016 12:21:02 +0300", "Test description gazeta 1", null),
                gazetaItem("Gazeta Test2", "Sat, 12 Nov 2016 14:21:02 +0300", "Test description gazeta 2", null));

        Flowable<List<AppNewsItem>> mergeResult = Requests.toAppNewsFlowable(lentaFlowable, gazetaFlowable);
        TestSubscriber<List<AppNewsItem>> testSubscriber = new TestSubscriber<>();
        mergeResult.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }

    private Flowable<LentaRss> lentaFlowable(LentaNewsItem... lentaNewsItems) {
        return Flowable.fromArray(lentaRss(lentaNewsItems));
    }

    private Flowable<GazetaRss> gazetaFlowable(GazetaNewsItem... gazetaNewsItems) {
        return Flowable.fromArray(gazetaRss(gazetaNewsItems));
    }

    private LentaRss lentaRss(LentaNewsItem... lentaNewsItems) {
        return new LentaRss(asList(lentaNewsItems));
    }

    private GazetaRss gazetaRss(GazetaNewsItem... gazetaNewsItems) {
        return new GazetaRss(asList(gazetaNewsItems));
    }

    private LentaNewsItem lentaItem(String title, String description, String pubDate, String thumbUrl) {
        return new LentaNewsItem(null, title, null, description, pubDate, null, thumbUrl);
    }

    private GazetaNewsItem gazetaItem(String title, String pubDate, String description, String thumbUrl) {
        return new GazetaNewsItem(title, null, null, pubDate, description, null, thumbUrl);
    }
}
