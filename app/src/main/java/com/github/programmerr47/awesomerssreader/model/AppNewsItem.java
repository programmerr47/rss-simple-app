package com.github.programmerr47.awesomerssreader.model;

import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.GAZETA;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.LENTA;
import static com.github.programmerr47.awesomerssreader.util.DateFormatter.toMs;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@Getter @Accessors(fluent = true)
@Builder
public final class AppNewsItem {
    public enum Source {
        LENTA, GAZETA
    }

    String title;
    String description;
    Source source;
    long date;
    String thumbUrl;

    public static AppNewsItem create(LentaNewsItem lentaNewsItem) {
        return AppNewsItem.builder()
                .title(lentaNewsItem.title())
                .description(lentaNewsItem.description())
                .source(LENTA)
                .date(toMs(lentaNewsItem.pubDate()))
                .thumbUrl(lentaNewsItem.enclosure() != null ? lentaNewsItem.enclosure().url() : null)
                .build();
    }

    public static AppNewsItem create(GazetaNewsItem gazetaNewsItem) {
        return AppNewsItem.builder()
                .title(gazetaNewsItem.title())
                .description(gazetaNewsItem.description())
                .source(GAZETA)
                .date(toMs(gazetaNewsItem.pubDate()))
                .thumbUrl(gazetaNewsItem.enclosure() != null ? gazetaNewsItem.enclosure().url() : null)
                .build();
    }
}
