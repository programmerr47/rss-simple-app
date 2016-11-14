package com.github.programmerr47.awesomerssreader.rsslist;

import com.github.programmerr47.awesomerssreader.net.Requests;

import io.reactivex.disposables.Disposable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.github.programmerr47.awesomerssreader.util.ObservableTransformers.listMap;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public final class RssListPresenterImpl extends BaseRssPresenter {
    private Disposable currentDisposable;

    @Override
    public void onCreate(RssListView view) {
        super.onCreate(view);
        fetchRss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        updateCurrentDisposable(null);
    }

    @Override
    public void fetchRss() {
        updateCurrentDisposable(Requests.fetchAllRss()
                .compose(listMap(AppNewsAdapterItem::create))
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribe(
                        view::showList,
                        throwable -> {
                            view.showError();
                            view.hideProgress();
                        },
                        view::hideProgress));
    }

    private void updateCurrentDisposable(Disposable newDisposable) {
        if (currentDisposable != null && !currentDisposable.isDisposed()) {
            currentDisposable.dispose();
        }

        currentDisposable = newDisposable;
    }
}
