package com.github.programmerr47.awesomerssreader.model.gazeta;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public final class GazetaNewsItem {
    @Element private String title;
    @Element private String link;
    @Element private String author;
    @Element private String pubDate;
    @Element private String description;
    @Element private String guid;
}
