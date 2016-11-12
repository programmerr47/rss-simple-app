package com.github.programmerr47.awesomerssreader.rsslist;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.programmerr47.awesomerssreader.R;
import com.github.programmerr47.awesomerssreader.model.lenta.LentaNewsItem;
import com.github.programmerr47.awesomerssreader.util.BindRecyclerHolder;
import com.squareup.picasso.RequestCreator;

import java.util.Collections;
import java.util.List;

import static com.github.programmerr47.awesomerssreader.util.AndroidUtils.dimen;
import static com.github.programmerr47.awesomerssreader.util.picasso.CirclePlaceholder.circlePlaceholder;
import static com.github.programmerr47.awesomerssreader.util.picasso.CircleTransformation.circleTransformation;
import static com.github.programmerr47.awesomerssreader.util.picasso.PicassoUtil.picasso;

public class RssListAdapter extends RecyclerView.Adapter<RssListAdapter.RssItemHolder> {
    private List<LentaNewsItem> lentaNews = Collections.emptyList();

    @Override
    public RssItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RssItemHolder(inflater.inflate(R.layout.item_news, null));
    }

    @Override
    public void onBindViewHolder(RssItemHolder holder, int position) {
        LentaNewsItem item = lentaNews.get(position);

        RequestCreator requestCreator;
        if (item.getEnclosure() != null && item.getEnclosure().getUrl() != null) {
            Drawable circleNewsPlaceholder = circlePlaceholder().get(holder.itemView.getContext(), R.drawable.news_placeholder);
            requestCreator = picasso()
                    .load(item.getEnclosure().getUrl())
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

        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return lentaNews.size();
    }

    public void updateItems(List<LentaNewsItem> newNews) {
        this.lentaNews = newNews;
        notifyDataSetChanged();
    }

    static final class RssItemHolder extends BindRecyclerHolder {
        private final ImageView image = bind(R.id.image);
        private final TextView title = bind(R.id.title);

        public RssItemHolder(View itemView) {
            super(itemView);
        }

        public int getImageSize() {
            return (int) dimen(itemView.getContext(), R.dimen.item_news_image_size);
        }
    }
}
