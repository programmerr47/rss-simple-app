package com.github.programmerr47.awesomerssreader.net;

import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public final class UrlRequest<R> {
    private final Class<R> responseClass;

    public UrlRequest(Class<R> responseClass) {
        this.responseClass = responseClass;
    }

    public Observable<R> makeObservable(final String url) {
        return Observable.fromCallable(new Callable<R>() {
            @Override
            public R call() throws Exception {
                InputStream urlStream = new URL(url).openStream();
                try {
                    return new Persister().read(responseClass, urlStream);
                } finally {
                    urlStream.close();
                }
            }
        });
    }
}
