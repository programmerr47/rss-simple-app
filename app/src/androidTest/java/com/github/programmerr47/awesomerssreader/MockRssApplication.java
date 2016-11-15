package com.github.programmerr47.awesomerssreader;

import com.github.programmerr47.awesomerssreader.rsslist.BaseRssPresenter;

public final class MockRssApplication extends RssApplication {
    @Override
    public BaseRssPresenter rssPresenter() {
        return new MockRssListPresenter();
    }
}
