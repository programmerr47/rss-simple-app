package com.github.programmerr47.awesomerssreader;

import android.support.annotation.IdRes;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.github.programmerr47.awesomerssreader.rsslist.RssListActivity;

import junit.framework.AssertionFailedError;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Michael Spitsin
 * @since 2016-11-08
 */
public class RssListActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<RssListActivity> activityTestRule =
            new ActivityTestRule<>(RssListActivity.class);

    @Test
    public void validateLoadedScrollableListAfterSomeTime() {
        waitId(R.id.title, SECONDS.toMillis(5));
        onView(withId(R.id.list)).perform(scrollToPosition(10));
    }

    @Test(expected = AssertionFailedError.class)
    public void noProgressAfterSomeTime() {
        waitId(R.id.title, SECONDS.toMillis(5));
        onView(withId(R.id.progress)).check(matches(isDisplayed()));
    }

    private void waitId(@IdRes int id, long millis) {
        onView(isRoot()).perform(ActionViews.waitId(id, millis));
    }
}
