package com.github.programmerr47.awesomerssreader.model.gazeta;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public final class GazetaNewsItem {
    @Element
    private String title;
    @Element
    private String link;
    @Element
    private String author;
    @Element
    private String pubDate;
    @Element
    private String description;
    @Element
    private String guid;
    @Element(required = false)
    private Enclosure enclosure;

    public GazetaNewsItem(String title, String link, String author, String pubDate, String description, String guid, String thumbUrl) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.pubDate = pubDate;
        this.description = description;
        this.guid = guid;
        this.enclosure = new Enclosure(thumbUrl, null);
    }

    public GazetaNewsItem() {
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public String getGuid() {
        return guid;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    @Root(name = "enclosure")
    public static final class Enclosure {
        @Attribute
        private String url;
        @Attribute
        private String type;

        public Enclosure(String url, String type) {
            this.url = url;
            this.type = type;
        }

        public Enclosure() {
        }

        public String getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }
    }
}
