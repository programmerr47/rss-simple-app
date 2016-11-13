package com.github.programmerr47.awesomerssreader.model.lenta;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Getter @Accessors(fluent = true)
@AllArgsConstructor @NoArgsConstructor
@Root(strict = false)
public final class LentaRss {
    @ElementList(inline = true) @Path("channel") List<LentaNewsItem> items;
}
