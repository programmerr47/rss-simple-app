package com.github.programmerr47.awesomerssreader.model;

import com.github.programmerr47.awesomerssreader.model.gazeta.GazetaNewsItem;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.util.DateFormatter;

import java.util.Date;

import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.GAZETA;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.LENTA;
import static com.github.programmerr47.awesomerssreader.util.DateFormatter.toMs;

public final class AppNewsItem {
    public enum Source {
        LENTA, GAZETA
    }

    private final String title;
    private final String description;
    private final Source source;
    private final long date;
    private final String thumbUrl;

    public static AppNewsItem create(LentaNewsItem lentaNewsItem) {
        return new AppNewsItem.Builder()
                .title(lentaNewsItem.getTitle())
                .description(lentaNewsItem.getDescription())
                .source(LENTA)
                .date(toMs(lentaNewsItem.getPubDate()))
                .thumbUrl(lentaNewsItem.getEnclosure() != null ? lentaNewsItem.getEnclosure().getUrl() : null)
                .build();
    }

    public static AppNewsItem create(GazetaNewsItem gazetaNewsItem) {
        return new AppNewsItem.Builder()
                .title(gazetaNewsItem.getTitle())
                .description(gazetaNewsItem.getDescription())
                .source(GAZETA)
                .date(toMs(gazetaNewsItem.getPubDate()))
                .thumbUrl(gazetaNewsItem.getEnclosure() != null ? gazetaNewsItem.getEnclosure().getUrl() : null)
                .build();
    }

    private AppNewsItem(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.source = builder.source;
        this.date = builder.date;
        this.thumbUrl = builder.thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Source getSource() {
        return source;
    }

    public long getDate() {
        return date;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public static final class Builder {
        private String title;
        private String description;
        private AppNewsItem.Source source;
        private long date;
        private String thumbUrl;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder source(AppNewsItem.Source source) {
            this.source = source;
            return this;
        }

        public Builder date(long date) {
            this.date = date;
            return this;
        }

        public Builder thumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
            return this;
        }

        public AppNewsItem build() {
            return new AppNewsItem(this);
        }
    }
}
