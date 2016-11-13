package com.github.programmerr47.awesomerssreader.rsslist;

import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.programmerr47.awesomerssreader.R;
import com.github.programmerr47.awesomerssreader.model.AppNewsItem;
import com.github.programmerr47.awesomerssreader.model.AppNewsItem.Source;
import com.github.programmerr47.awesomerssreader.util.AndroidUtils;
import com.github.programmerr47.awesomerssreader.util.BindRecyclerHolder;
import com.squareup.picasso.RequestCreator;

import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;

import static com.github.programmerr47.awesomerssreader.util.AndroidUtils.dimen;
import static com.github.programmerr47.awesomerssreader.util.picasso.CirclePlaceholder.circlePlaceholder;
import static com.github.programmerr47.awesomerssreader.util.picasso.CircleTransformation.circleTransformation;
import static com.github.programmerr47.awesomerssreader.util.picasso.PicassoUtil.picasso;
import static lombok.AccessLevel.PRIVATE;

@SuppressWarnings("WeakerAccess")
public class RssListAdapter extends RecyclerView.Adapter<RssListAdapter.RssItemHolder> {
    private List<AppNewsItem> lentaNews = Collections.emptyList();

    @Override
    public RssItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RssItemHolder(inflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(RssItemHolder holder, int position) {
        AppNewsItem item = lentaNews.get(position);

        RequestCreator requestCreator;
        if (item.thumbUrl() != null) {
            Drawable circleNewsPlaceholder = circlePlaceholder().get(holder.itemView.getContext(), R.drawable.news_placeholder);
            requestCreator = picasso()
                    .load(item.thumbUrl())
                    .placeholder(circleNewsPlaceholder)
                    .error(circleNewsPlaceholder);
        } else {
            requestCreator = picasso().load(R.drawable.news_placeholder);
        }
        int imageSize = holder.getImageSize();
        requestCreator
                .resize(imageSize, imageSize)
                .transform(circleTransformation())
                .centerCrop()
                .into(holder.image);

        holder.title.setText(item.title());
        holder.setSource(item.source());
    }

    @Override
    public int getItemCount() {
        return lentaNews.size();
    }

    @SuppressWarnings("WeakerAccess")
    public void updateItems(List<AppNewsItem> newNews) {
        this.lentaNews = newNews;
        notifyDataSetChanged();
    }

    @FieldDefaults(level = PRIVATE, makeFinal = true)
    static final class RssItemHolder extends BindRecyclerHolder {
        ImageView image = bind(R.id.image);
        TextView title = bind(R.id.title);
        TextView source = bind(R.id.source);

        public RssItemHolder(View itemView) {
            super(itemView);
        }

        public int getImageSize() {
            return (int) dimen(itemView.getContext(), R.dimen.item_news_image_size);
        }

        public void setSource(Source source) {
            this.source.setText(userFriendlySource(source));
        }

        @StringRes
        private int userFriendlySource(Source source) {
            switch (source) {
                case LENTA:
                    return R.string.source_lenta;
                case GAZETA:
                    return R.string.source_gazeta;
                default:
                    throw new IllegalArgumentException("There is no user frendly form for source: " + source);
            }
        }
    }
}
