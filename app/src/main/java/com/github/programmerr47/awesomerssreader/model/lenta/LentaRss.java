package com.github.programmerr47.awesomerssreader.model.lenta;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public final class LentaRss {
    @ElementList(inline = true)
    @Path("channel")
    private List<LentaNewsItem> items;

    public LentaRss(List<LentaNewsItem> items) {
        this.items = items;
    }

    public LentaRss() {}

    public List<LentaNewsItem> getItems() {
        return items;
    }
}
