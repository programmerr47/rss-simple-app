package com.github.programmerr47.awesomerssreader.util.recyclerdecorations;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import lombok.val;

import static org.mockito.Mockito.doReturn;

@RunWith(RobolectricTestRunner.class)
public class SpaceDecorationTest {
    private final Rect rect = new Rect();
    private final Context mockContext = Robolectric.setupActivity(ListActivity.class);
    private final RecyclerView spyRecycler = Mockito.spy(new RecyclerView(mockContext));

    @Test
    @MediumTest
    public void positiveEvenSpacingForExistingItem() {
        val decoration = new SpaceDecoration(24);
        doReturn(1).when(spyRecycler).getChildAdapterPosition(null);
        decoration.getItemOffsets(rect, null, spyRecycler, null);
        Assert.assertEquals(rect, new Rect(12, 12, 12, 12));
    }

    @Test
    @MediumTest
    public void positiveEvenSpacingForNotExistingItem() {
        val decoration = new SpaceDecoration(24);
        doReturn(-1).when(spyRecycler).getChildAdapterPosition(null);
        decoration.getItemOffsets(rect, null, spyRecycler, null);
        Assert.assertEquals(rect, new Rect());
    }

    @Test
    @MediumTest
    public void positiveOddSpacingForExistingItem() {
        val decoration = new SpaceDecoration(25);
        doReturn(1).when(spyRecycler).getChildAdapterPosition(null);
        decoration.getItemOffsets(rect, null, spyRecycler, null);
        Assert.assertEquals(rect, new Rect(12, 12, 13, 13));
    }

    @Test
    @MediumTest
    public void positiveOddSpacingForNotExistingItem() {
        val decoration = new SpaceDecoration(25);
        doReturn(-1).when(spyRecycler).getChildAdapterPosition(null);
        decoration.getItemOffsets(rect, null, spyRecycler, null);
        Assert.assertEquals(rect, new Rect());
    }

    @Test
    @MediumTest
    public void zeroSpacingForExistingItem() {
        val decoration = new SpaceDecoration(0);
        doReturn(1).when(spyRecycler).getChildAdapterPosition(null);
        decoration.getItemOffsets(rect, null, spyRecycler, null);
        Assert.assertEquals(rect, new Rect());
    }

    @Test
    @MediumTest
    public void zeroSpacingForNotExistingItem() {
        val decoration = new SpaceDecoration(0);
        doReturn(-1).when(spyRecycler).getChildAdapterPosition(null);
        decoration.getItemOffsets(rect, null, spyRecycler, null);
        Assert.assertEquals(rect, new Rect());
    }

    @Test(expected = IllegalArgumentException.class)
    @MediumTest
    public void negativeSpacing() {
        new SpaceDecoration(-1);
    }
}
