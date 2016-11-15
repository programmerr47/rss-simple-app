package com.github.programmerr47.awesomerssreader.rsslist;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.observers.LambdaObserver;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@RequiredArgsConstructor
public final class RssListPresenterImpl extends BaseRssPresenter {
    final Interactor interactor;
    Disposable currentDisposable;

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
        view.showProgress();
        val observer = new LambdaObserver<List<AppNewsAdapterItem>>(
                view::showList,
                throwable -> {
                    view.showError();
                    view.hideProgress();
                },
                view::hideProgress,
                Functions.emptyConsumer());
        updateCurrentDisposable(observer);
        interactor.interact(observer);
    }

    private void updateCurrentDisposable(Disposable newDisposable) {
        if (currentDisposable != null && !currentDisposable.isDisposed()) {
            currentDisposable.dispose();
        }

        currentDisposable = newDisposable;
    }
}
