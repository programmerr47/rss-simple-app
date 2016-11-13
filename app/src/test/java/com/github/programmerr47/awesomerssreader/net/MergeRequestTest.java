package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.ObjectFactory;
import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;
import com.github.programmerr47.awesomerssreader.util.DateFormatter;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;

import static com.github.programmerr47.awesomerssreader.ObjectFactory.gazetaFlowable;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.gazetaItem;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.lentaFlowable;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.lentaItem;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.GAZETA;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.LENTA;
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
        testSubscriber.assertValue(new Predicate<List<AppNewsItem>>() {
            @Override
            public boolean test(List<AppNewsItem> appNewsItems) throws Exception {
                return appNewsItems.size() == 4 &&
                        checkAppNewsItem(appNewsItems.get(0), "Gazeta Test2", "Test description gazeta 2", GAZETA, 1478949662000L, null) &&
                        checkAppNewsItem(appNewsItems.get(1), "Lenta Test2", "Test description lenta 2", LENTA, 1478946062000L, null) &&
                        checkAppNewsItem(appNewsItems.get(2), "Gazeta Test1", "Test description gazeta 1", GAZETA, 1478942462000L, null) &&
                        checkAppNewsItem(appNewsItems.get(3), "Lenta Test1", "Test description lenta 1", LENTA, 1478938862000L, null);
            }
        });
    }

    private boolean checkAppNewsItem(AppNewsItem item, String title, String description, Source source, long date, String thumbUrl) {
        return equals(item.getTitle(), title) &&
                equals(item.getDescription(), description) &&
                equals(item.getSource(), source) &&
                equals(item.getDate(), date) &&
                equals(item.getThumbUrl(), thumbUrl);
    }

    private boolean equals(Object left, Object right) {
        return left == null && right == null ||
                left != null && right != null && left.equals(right);
    }
}
