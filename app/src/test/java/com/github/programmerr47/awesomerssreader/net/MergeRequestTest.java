package com.github.programmerr47.awesomerssreader.net;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source;

import org.junit.Test;

import java.util.List;

import io.reactivex.observers.TestObserver;
import lombok.val;

import static com.github.programmerr47.awesomerssreader.ObjectFactory.gazetaItem;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.gazetaObservable;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.lentaItem;
import static com.github.programmerr47.awesomerssreader.ObjectFactory.lentaObservable;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.GAZETA;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.LENTA;

public class MergeRequestTest {
    @Test
    public void simpleMergingTwoRss() {
        val lentaObservable = lentaObservable(
                lentaItem("Lenta Test1", "Test description lenta 1", "Sat, 12 Nov 2016 11:21:02 +0300", null),
                lentaItem("Lenta Test2", "Test description lenta 2", "Sat, 12 Nov 2016 13:21:02 +0300", null));

        val gazetaObservable = gazetaObservable(
                gazetaItem("Gazeta Test1", "Sat, 12 Nov 2016 12:21:02 +0300", "Test description gazeta 1", null),
                gazetaItem("Gazeta Test2", "Sat, 12 Nov 2016 14:21:02 +0300", "Test description gazeta 2", null));

        val mergeResult = Requests.toAppNewsObservable(lentaObservable, gazetaObservable);
        val testObserver = new TestObserver<List<AppNewsItem>>();
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
        return equals(item.title(), title) &&
                equals(item.description(), description) &&
                equals(item.source(), source) &&
                equals(item.date(), date) &&
                equals(item.thumbUrl(), thumbUrl);
    }

    private boolean equals(Object left, Object right) {
        return left == null && right == null ||
                left != null && right != null && left.equals(right);
    }
}
