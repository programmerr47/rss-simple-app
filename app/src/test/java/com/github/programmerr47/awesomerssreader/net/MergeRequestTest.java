package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source;
import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaRss;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaRss;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static com.github.programmerr47.awesomerssreader.ObjectFactory.gazetaObservable;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.gazetaItem;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.lentaObservable;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.lentaItem;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.GAZETA;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.LENTA;

/**
 * @author Michael Spitsin
 * @since 2016-11-12
 */
public class MergeRequestTest {
    @Test
    public void simpleMergingTwoRss() {
        Observable<LentaRss> lentaObservable = lentaObservable(
                lentaItem("Lenta Test1", "Test description lenta 1", "Sat, 12 Nov 2016 11:21:02 +0300", null),
                lentaItem("Lenta Test2", "Test description lenta 2", "Sat, 12 Nov 2016 13:21:02 +0300", null));

        Observable<GazetaRss> gazetaObservable = gazetaObservable(
                gazetaItem("Gazeta Test1", "Sat, 12 Nov 2016 12:21:02 +0300", "Test description gazeta 1", null),
                gazetaItem("Gazeta Test2", "Sat, 12 Nov 2016 14:21:02 +0300", "Test description gazeta 2", null));

        Observable<List<AppNewsItem>> mergeResult = Requests.toAppNewsObservable(lentaObservable, gazetaObservable);
        TestObserver<List<AppNewsItem>> testObserver = new TestObserver<>();
        mergeResult.subscribe(testObserver);
        testObserver
                .assertNoErrors()
                .assertValue(appNewsItems -> appNewsItems.size() == 4)
                .assertValue(appNewsItems -> checkAppNewsItem(appNewsItems.get(0), "Gazeta Test2", "Test description gazeta 2", GAZETA, 1478949662000L, null))
                .assertValue(appNewsItems -> checkAppNewsItem(appNewsItems.get(1), "Lenta Test2", "Test description lenta 2", LENTA, 1478946062000L, null))
                .assertValue(appNewsItems -> checkAppNewsItem(appNewsItems.get(2), "Gazeta Test1", "Test description gazeta 1", GAZETA, 1478942462000L, null))
                .assertValue(appNewsItems -> checkAppNewsItem(appNewsItems.get(3), "Lenta Test1", "Test description lenta 1", LENTA, 1478938862000L, null));
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
