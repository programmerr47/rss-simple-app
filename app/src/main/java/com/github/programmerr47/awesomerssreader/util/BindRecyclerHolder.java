package com.github.programmerr47.awesomerssreader.util;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lombok.RequiredArgsConstructor;

public abstract class BindRecyclerHolder extends RecyclerView.ViewHolder {
    public BindRecyclerHolder(View itemView) {
        super(itemView);
    }

    protected <T> T bind(@IdRes int id) {
        return ViewUtils.bind(itemView, id);
    }
}
