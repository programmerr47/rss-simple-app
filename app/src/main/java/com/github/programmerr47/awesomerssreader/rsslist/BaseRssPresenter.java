package com.github.programmerr47.awesomerssreader.rsslist;

import java.util.List;

public abstract class BaseRssPresenter {
    private static final RssListView DUMMY = new RssListViewDummy();

    protected RssListView view = DUMMY;

    public void onCreate(RssListView view) {
        this.view = view != null ? view : DUMMY;
    }

    public void onDestroy() {
        view = DUMMY;
    }

    public abstract void fetchRss();

    private static final class RssListViewDummy implements RssListView {
        @Override public void showList(List<AppNewsAdapterItem> items) {}
        @Override public void showProgress() {}
        @Override public void hideProgress() {}
        @Override public void showError() {}
    }
}
