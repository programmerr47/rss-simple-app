package com.github.programmerr47.awesomerssreader.rsslist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import lombok.val;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RssListPresenterImplTest {
    @Mock
    private RssListView rssListView;

    @Test
    public void fetchingInOnCreate() {
        Interactor mockInteractor = mock(Interactor.class);
        val presenter = new RssListPresenterImpl(mockInteractor);
        presenter.onCreate(rssListView);
        verify(mockInteractor, times(1)).interact(any());
    }

    @Test
    public void successFetching() {
        val presenter = new RssListPresenterImpl(observer -> {
            observer.onNext(any());
            observer.onComplete();
        });
        presenter.onCreate(rssListView);
        InOrder inOrder = inOrder(rssListView);
        inOrder.verify(rssListView, times(1)).showProgress();
        inOrder.verify(rssListView, times(1)).showList(any());
        inOrder.verify(rssListView, times(1)).hideProgress();
    }

    @Test
    public void errorFetching() {
        val presenter = new RssListPresenterImpl(observer -> {
            observer.onError(new Throwable());
        });
        presenter.onCreate(rssListView);
        InOrder inOrder = inOrder(rssListView);
        inOrder.verify(rssListView, times(1)).showProgress();
        inOrder.verify(rssListView, times(1)).hideProgress();
        verify(rssListView, times(1)).showError();
    }
}
