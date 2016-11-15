package com.github.programmerr47.awesomerssreader;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source;
import com.github.programmerr47.awesomerssreader.rsslist.AppNewsAdapterItem;
import com.github.programmerr47.awesomerssreader.rsslist.BaseRssPresenter;
import com.github.programmerr47.awesomerssreader.rsslist.RssListView;

import io.reactivex.Observable;

import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.GAZETA;
import static com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source.LENTA;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class MockRssListPresenter extends BaseRssPresenter {
    @Override
    public void onCreate(RssListView view) {
        super.onCreate(view);
        fetchRss();
    }

    @Override
    public void fetchRss() {
        Observable.just(asList(
                newsItem("Test 1", "Test description 1", LENTA, 4),
                newsItem("Test 2", "Test description 2", GAZETA, 3),
                newsItem("Test 3", "Test description 3", LENTA, 2),
                newsItem("Test 4", "Test description 4", GAZETA, 1)))
                .delay(3, SECONDS)
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribe(
                        view::showList,
                        throwable -> {
                            view.showError();
                            view.hideProgress();
                        },
                        view::hideProgress);
    }

    private AppNewsAdapterItem newsItem(String title, String description, Source source, long date) {
        return AppNewsAdapterItem.create(AppNewsItem.builder()
                .title(title)
                .description(description)
                .source(source)
                .date(date)
                .build());
    }
}
