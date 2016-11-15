package com.github.programmerr47.awesomerssreader.rsslist;

import java.util.List;

public interface RssListView {
    void showList(List<AppNewsAdapterItem> items);
    void showProgress();
    void hideProgress();
    void showError();
}
