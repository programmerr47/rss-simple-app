package com.github.programmerr47.awesomerssreader.net;

import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public final class UrlRequest<R> {
    private final Class<R> responseClass;

    public UrlRequest(Class<R> responseClass) {
        this.responseClass = responseClass;
    }

    public Flowable<R> makeFlowable(final String url) {
        return Flowable.fromCallable(new Callable<R>() {
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
