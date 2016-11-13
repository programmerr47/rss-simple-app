package com.github.programmerr47.awesomerssreader.util.recyclerdecorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceDecoration extends RecyclerView.ItemDecoration {
    private final int spaceBetween;

    public SpaceDecoration(int spaceBetween) {
        if (spaceBetween < 0) {
            throw new IllegalArgumentException("Space between items must be zero or positive");
        }

        this.spaceBetween = spaceBetween;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        if (position >= 0) {
            outRect.top = spaceBetween / 2;
            outRect.bottom = spaceBetween - outRect.top;
            outRect.left = spaceBetween / 2;
            outRect.right = spaceBetween - outRect.left;
        } else {
            outRect.top = 0;
            outRect.bottom = 0;
            outRect.left = 0;
            outRect.right = 0;
        }
    }
}
