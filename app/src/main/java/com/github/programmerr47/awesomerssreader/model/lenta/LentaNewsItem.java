package com.github.programmerr47.awesomerssreader.model.lenta;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Getter @Accessors(fluent = true)
@AllArgsConstructor @NoArgsConstructor
@Root(name = "item")
public final class LentaNewsItem {
    @Element String guid;
    @Element String title;
    @Element String link;
    @Element String description;
    @Element String pubDate;
    @Element String category;
    @Element(required = false) Enclosure enclosure;

    @FieldDefaults(level = PRIVATE)
    @Getter @Accessors(fluent = true)
    @AllArgsConstructor @NoArgsConstructor
    @Root(name = "enclosure")
    public static final class Enclosure {
        @Attribute String url;
        @Attribute int length;
        @Attribute String type;
    }
}
