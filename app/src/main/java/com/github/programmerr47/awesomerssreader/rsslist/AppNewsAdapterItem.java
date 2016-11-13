package com.github.programmerr47.awesomerssreader.rsslist;

import com.github.programmerr47.awesomerssreader.model.AppNewsItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@RequiredArgsConstructor(access = PRIVATE)
@Getter @Accessors(fluent = true)
public final class AppNewsAdapterItem {
    final AppNewsItem item;
    @Setter boolean isDescriptionShown;

    public static AppNewsAdapterItem create(AppNewsItem item) {
        return new AppNewsAdapterItem(item);
    }
}
