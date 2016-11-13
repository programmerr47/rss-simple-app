package com.github.programmerr47.awesomerssreader.model.lenta;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public final class LentaNewsItem {
    @Element private String guid;
    @Element private String title;
    @Element private String link;
    @Element private String description;
    @Element private String pubDate;
    @Element private String category;
    @Element(required = false) private Enclosure enclosure;

    public LentaNewsItem(String guid, String title, String link, String description, String pubDate, String category, String thumbUrl) {
        this.guid = guid;
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.category = category;
        this.enclosure = new Enclosure(thumbUrl, 0, null);
    }

    public LentaNewsItem() {}

    public String getGuid() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getCategory() {
        return category;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    @Root(name = "enclosure")
    public static final class Enclosure {
        @Attribute private String url;
        @Attribute private int length;
        @Attribute private String type;

        public Enclosure(String url, int length, String type) {
            this.url = url;
            this.length = length;
            this.type = type;
        }

        public Enclosure() {}

        public String getUrl() {
            return url;
        }

        public int getLength() {
            return length;
        }

        public String getType() {
            return type;
        }
    }
}
