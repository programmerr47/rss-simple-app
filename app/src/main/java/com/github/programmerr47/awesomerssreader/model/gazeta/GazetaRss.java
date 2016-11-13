package com.github.programmerr47.awesomerssreader.model.gazeta;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public final class GazetaRss {
    @ElementList(inline = true)
    @Path("channel")
    private List<GazetaNewsItem> items;

    public GazetaRss(List<GazetaNewsItem> items) {
        this.items = items;
    }

    public GazetaRss() {}

    public List<GazetaNewsItem> getItems() {
        return items;
    }
}
