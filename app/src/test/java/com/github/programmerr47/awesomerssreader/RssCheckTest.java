package com.github.programmerr47.awesomerssreader;

import android.test.suitebuilder.annotation.LargeTest;

import com.github.programmerr47.awesomerssreader.model.LentaRss;
import com.github.programmerr47.awesomerssreader.net.UrlRequest;

import org.junit.Assert;
import org.junit.Test;

import io.reactivex.functions.Consumer;

public class RssCheckTest {
    @Test
    @LargeTest
    public void checkLentaOnlineRss() throws Exception {
        UrlRequest<LentaRss> request = new UrlRequest<>(LentaRss.class);
        request
                .makeObservable("https://m.lenta.ru/rss")
                .subscribe(new Consumer<LentaRss>() {
                    @Override
                    public void accept(LentaRss lentaRss) throws Exception {
                        Assert.assertNotNull(lentaRss);
                        Assert.assertTrue(lentaRss.getItems().size() > 0);
                        assertContentNotNull(lentaRss.getItems());
                    }
                });
    }

    private static void assertContentNotNull(Iterable<?> iterable) {
        for (Object item : iterable) {
            Assert.assertNotNull(item);
        }
    }
}
