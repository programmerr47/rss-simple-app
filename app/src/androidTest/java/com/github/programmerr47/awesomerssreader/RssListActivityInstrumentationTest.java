package com.github.programmerr47.awesomerssreader;

import android.support.annotation.IdRes;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.programmerr47.awesomerssreader.rsslist.RssListActivity;

import junit.framework.AssertionFailedError;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.github.programmerr47.awesomerssreader.RecyclerViewMatcher.withRecyclerView;
import static java.util.concurrent.TimeUnit.SECONDS;

@RunWith(AndroidJUnit4.class)
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

    @Test
    public void expandItemAndSeeDescription() {
        waitId(R.id.title, SECONDS.toMillis(5));
        onView(withRecyclerView(R.id.list).atPosition(0)).perform(click());
        onView(withRecyclerView(R.id.list).atPosition(0)).check(selectedDescendantsMatch(withId(R.id.description), isDisplayed()));
    }

    private void waitId(@IdRes int id, long millis) {
        onView(isRoot()).perform(ActionViews.waitId(id, millis));
    }
}
