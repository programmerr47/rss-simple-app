package com.github.programmerr47.awesomerssreader.net;

import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.net.URL;

import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("WeakerAccess")
public final class UrlRequest<R> {
    private final Class<R> responseClass;

    public Observable<R> makeObservable(final String url) {
        return Observable.fromCallable(() -> {
            try (InputStream urlStream = new URL(url).openStream()) {
                return new Persister().read(responseClass, urlStream);
            }
        });
    }
}
