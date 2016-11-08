package com.github.programmerr47.awesomerssreader;

import android.support.annotation.IdRes;
import android.support.test.espresso.PerformException;
import android.support.test.rule.ActivityTestRule;

import com.github.programmerr47.awesomerssreader.rsslist.RssListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
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

    @Test(expected = PerformException.class)
    public void noListAtTheBeginning() {
        waitId(R.id.title, 2);
    }

    @Test
    public void validateLoadedScrollableListAfterSomeTime() {
        waitId(R.id.title, SECONDS.toMillis(5));
        onView(withId(R.id.list)).perform(scrollToPosition(10));
    }

    private void waitId(@IdRes int id, long millis) {
        onView(isRoot()).perform(ActionViews.waitId(id, millis));
    }
}
