package com.github.programmerr47.awesomerssreader.model;

import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Collection;

import static com.github.programmerr47.awesomerssreader.ObjectFactory.lentaItem;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.LENTA;

/**
 * @author Michael Spitsin
 * @since 2016-11-12
 */
@RunWith(Parameterized.class)
public class AppNewsItemFromLentaNewsItemTest {
    private static final String TEST_URL = "https://icdn.lenta.ru/images/2016/11/12/08/20161112085645534/pic_7711efb6d2d3af307e29bbf22afd6f03.jpg";

    @Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> result = new ArrayList<>();
        result.add(new Object[]{lentaItem(null, null, null, null), -1, null});
        result.add(new Object[]{lentaItem("Title 1", null, null, null), -1, null});
        result.add(new Object[]{lentaItem(null, "Test description 1 \u2131", null, null), -1, null});
        result.add(new Object[]{lentaItem(null, null, "Sat, 12 Nov 2016 09:58:00 +0300", null), 1478933880000L, null});
        result.add(new Object[]{lentaItem(null, null, null, TEST_URL), -1, TEST_URL});
        result.add(new Object[]{lentaItem(null, null, "Sat, 12 Nov 2016 08:27:00 +0300", TEST_URL), 1478928420000L, TEST_URL});
        result.add(new Object[]{lentaItem(null, "%20", null, TEST_URL), -1, TEST_URL});
        result.add(new Object[]{lentaItem("Title $4%#*()&@(%\\", "", "Sat, 12 Nov 2016 08:58:54 +0300", TEST_URL), 1478930334000L, TEST_URL});
        return result;
    }

    @Parameter
    @SuppressWarnings("WeakerAccess")
    public LentaNewsItem lentaNewsItem;

    @Parameter(1)
    @SuppressWarnings("WeakerAccess")
    public long expectedConvertedTime;

    @Parameter(2)
    @SuppressWarnings("WeakerAccess")
    public String expectedUrl;

    @Test
    public void checkConversionToAppNewsItem() {
        AppNewsItem item = AppNewsItem.create(lentaNewsItem);
        Assert.assertEquals(item.title(), lentaNewsItem.title());
        Assert.assertEquals(item.date(), expectedConvertedTime);
        Assert.assertEquals(item.description(), lentaNewsItem.description());
        Assert.assertEquals(item.thumbUrl(), expectedUrl);
        Assert.assertEquals(item.source(), LENTA);
    }
}
