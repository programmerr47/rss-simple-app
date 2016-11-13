package com.github.programmerr47.awesomerssreader.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael Spitsin
 * @since 2016-11-12
 */
public class DateFormatterTest {
    @Test
    public void rssDateStrToMsSmockTest() {
        Assert.assertEquals(1478149321000L, DateFormatter.toMs("Thu, 3 Nov 2016 06:02:01 +0100"));
    }

    @Test
    public void wrongFormat() {
        Assert.assertEquals(-1, DateFormatter.toMs("Thu Nov 3 2016 06:02:01 +0100"));
    }
}
