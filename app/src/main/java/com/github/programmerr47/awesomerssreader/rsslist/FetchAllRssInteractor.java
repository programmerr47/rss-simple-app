package com.github.programmerr47.awesomerssreader.rsslist;

import com.github.programmerr47.awesomerssreader.net.Requests;

import org.reactivestreams.Subscriber;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import lombok.experimental.FieldDefaults;

import static com.github.programmerr47.awesomerssreader.util.ObservableTransformers.listMap;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
// TODO: 15/11/2016 change Interactor interface to abstract class or add abstract layer of BaseInteractor
public final class FetchAllRssInteractor implements Interactor {
    Scheduler subscribeOn = Schedulers.io();
    Scheduler observeOn = AndroidSchedulers.mainThread();

    @Override
    public void interact(Observer observer) {
        Requests.fetchAllRss()
                .compose(listMap(AppNewsAdapterItem::create))
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribe(observer);
    }
}
