package com.github.programmerr47.awesomerssreader.model;

public final class AppNewsItem {
    public enum Source {
        LENTA, GAZETA
    }

    private final String title;
    private final String description;
    private final Source source;
    private final long date;
    private final String thumbUrl;

    public AppNewsItem(Builder builder) {
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

        public AppNewsItem createAppNewsItem() {
            return new AppNewsItem(this);
        }
    }
}
