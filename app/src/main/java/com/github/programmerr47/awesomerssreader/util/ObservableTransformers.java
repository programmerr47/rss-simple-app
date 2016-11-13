package com.github.programmerr47.awesomerssreader.util;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObservableTransformers {
    public static <L, R> ObservableTransformer<List<L>, List<R>> listMap(Function<? super L, ? extends R> itemMapper) {
        return upstream -> upstream.flatMap(Observable::fromIterable)
                .map((Function<L, R>) itemMapper::apply)
                .toList()
                .toObservable();
    }
}
