package com.github.programmerr47.awesomerssreader.rsslist;

import com.github.programmerr47.awesomerssreader.net.Requests;

import io.reactivex.Observer;

import static com.github.programmerr47.awesomerssreader.util.ObservableTransformers.listMap;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

// TODO: 15/11/2016 change Interactor interface to abstract class or add abstract layer of BaseInteractor
public final class FetchAllRssInteractor implements Interactor {
    @Override
    @SuppressWarnings("unchecked")
    public void interact(Observer observer) {
        Requests.fetchAllRss()
                .compose(listMap(AppNewsAdapterItem::create))
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribe(observer);
    }
}
