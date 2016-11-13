package com.github.programmerr47.awesomerssreader.util;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.val;

import static com.github.programmerr47.awesomerssreader.util.ObservableTransformers.listMap;
import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

public class ObservableTransformersTest {
    @Test
    public void checkListMap() {
        val origin = asList(1, 5, 2, 4, 3);
        val expected = asList(
                expected(1, "Test 1"), expected(5, "Test 5"),
                expected(2, "Test 2"), expected(4, "Test 4"),
                expected(3, "Test 3"));

        val testObserver = new TestObserver<List<Expected>>();
        Observable.just(origin).compose(listMap(Expected::of)).subscribe(testObserver);
        testObserver.assertNoErrors().assertValue(actual -> actual.equals(expected));
    }

    private Expected expected(int value, String str) {
        return new Expected(value, str);
    }

    @FieldDefaults(level = PRIVATE, makeFinal = true)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode
    private static final class Expected {
        int value;
        String someString;

        static Expected of(int value) {
            return new Expected(value, "Test " + value);
        }
    }
}
