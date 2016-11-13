package com.github.programmerr47.awesomerssreader.model;

import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

import lombok.val;

import static com.github.programmerr47.awesomerssreader.ObjectFactory.gazetaItem;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.GAZETA;
import static java.util.Arrays.asList;

@RunWith(Parameterized.class)
public class AppNewsItemFromGazetaNewsItemTest {
    private static final String TEST_URL = "https://img.gazeta.ru/files3/707/10188707/TASS_17586415-pic60-60x60-29486.jpg";

    @Parameters
    public static Collection<Object[]> data() {
        return asList(
                new Object[]{gazetaItem(null, null, null, null), -1, null},
                new Object[]{gazetaItem("Title 1", null, null, null), -1, null},
                new Object[]{gazetaItem(null, "Sat, 12 Nov 2016 09:58:00 +0300", null, null), 1478933880000L, null},
                new Object[]{gazetaItem(null, null, "Test description 1 \u2131", null), -1, null},
                new Object[]{gazetaItem(null, null, null, TEST_URL), -1, TEST_URL},
                new Object[]{gazetaItem(null, "Sat, 12 Nov 2016 08:27:00 +0300", null, TEST_URL), 1478928420000L, TEST_URL},
                new Object[]{gazetaItem(null, null, "%20", TEST_URL), -1, TEST_URL},
                new Object[]{gazetaItem("Title $4%#*()&@(%\\", "Sat, 12 Nov 2016 08:58:54 +0300", "", TEST_URL), 1478930334000L, TEST_URL});
    }

    @Parameter
    @SuppressWarnings("WeakerAccess")
    public GazetaNewsItem gazetaNewsItem;

    @Parameter(1)
    @SuppressWarnings("WeakerAccess")
    public long expectedConvertedTime;

    @Parameter(2)
    @SuppressWarnings("WeakerAccess")
    public String expectedUrl;

    @Test
    public void checkConversionToAppNewsItem() {
        val item = AppNewsItem.create(gazetaNewsItem);
        Assert.assertEquals(item.title(), gazetaNewsItem.title());
        Assert.assertEquals(item.date(), expectedConvertedTime);
        Assert.assertEquals(item.description(), gazetaNewsItem.description());
        Assert.assertEquals(item.thumbUrl(), expectedUrl);
        Assert.assertEquals(item.source(), GAZETA);
    }
}
