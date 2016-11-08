package com.github.programmerr47.awesomerssreader.rsslist;

import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.programmerr47.awesomerssreader.R;
import com.github.programmerr47.awesomerssreader.model.LentaRss;
import com.github.programmerr47.awesomerssreader.net.UrlRequest;
import com.github.programmerr47.awesomerssreader.util.BindActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

//TODO add progress bar
public class RssListActivity extends BindActivity {
    private final RssListAdapter adapter = new RssListAdapter();
    private RecyclerView listView;

    private Disposable currentDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_rss_list);
        prepareRecycler();
    }

    private void prepareRecycler() {
        listView = bind(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchRss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateCurrentDisposable(null);
    }

    private void fetchRss() {
        updateCurrentDisposable(new UrlRequest<>(LentaRss.class)
                .makeObservable("https://m.lenta.ru/rss")
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribe(new Consumer<LentaRss>() {
                    @Override
                    public void accept(LentaRss lentaRss) throws Exception {
                        adapter.updateItems(lentaRss.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showErrorSnackbar();
                    }
                }));
    }

    private void showErrorSnackbar() {
        Snackbar.make(listView, R.string.message_error, LENGTH_INDEFINITE)
                .setAction(R.string.action_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fetchRss();
                    }
                })
                .show();
    }

    private void updateCurrentDisposable(Disposable newDisposable) {
        if (currentDisposable != null && !currentDisposable.isDisposed()) {
            currentDisposable.dispose();
        }

        currentDisposable = newDisposable;
    }
}
