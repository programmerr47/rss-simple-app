package com.github.programmerr47.awesomerssreader.rsslist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.github.programmerr47.awesomerssreader.R;
import com.github.programmerr47.awesomerssreader.RssApplication;
import com.github.programmerr47.awesomerssreader.util.BindActivity;
import com.github.programmerr47.awesomerssreader.util.recyclerdecorations.SpaceDecoration;

import java.util.List;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.github.programmerr47.awesomerssreader.util.AndroidUtils.dimen;

public class RssListActivity extends BindActivity implements RssListView {
    private final RssListAdapter adapter = new RssListAdapter();
    private RecyclerView listView;
    private ProgressBar progressView;

    private BaseRssPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_rss_list);
        progressView = bind(R.id.progress);
        prepareRecycler();

        presenter = RssApplication.application(this).rssPresenter();
        presenter.onCreate(this);
    }

    private void prepareRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listView = bind(R.id.list);
        listView.setLayoutManager(layoutManager);
        listView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        listView.addItemDecoration(new SpaceDecoration((int) dimen(this, R.dimen.margin_big)));
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDestroy();
    }

    @Override
    public void showList(List<AppNewsAdapterItem> items) {
        adapter.updateItems(items);
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(GONE);
    }

    @Override
    public void showError() {
        Snackbar.make(listView, R.string.message_error, LENGTH_INDEFINITE)
                .setAction(R.string.action_retry, view -> presenter.fetchRss())
                .show();
    }
}
