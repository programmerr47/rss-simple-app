package com.github.programmerr47.awesomerssreader.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateFormatter {
    private static final SimpleDateFormat RSS_DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);

    public static long toMs(String formatted) {
        try {
            return RSS_DATE_FORMAT.parse(formatted).getTime();
        } catch (Exception e) {
            return -1;
        }
    }
}
