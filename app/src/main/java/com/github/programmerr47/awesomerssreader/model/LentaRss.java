package com.github.programmerr47.awesomerssreader.model;

import com.github.programmerr47.awesomerssreader.model.LentaNewsItem;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class LentaRss {
    @ElementList(inline = true)
    @Path("channel")
    private List<LentaNewsItem> items;

    public List<LentaNewsItem> getItems() {
        return items;
    }
}
