package com.calclab.hablar.search.client.N;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.mvp.Presenter;

public class SearchResultItemPresenter implements Presenter<SearchResultItemDisplay> {

    private final SearchResultItemDisplay display;

    public SearchResultItemPresenter(SearchResultItem item, SearchResultItemDisplay display) {
	this.display = display;
	display.setItem(item);
    }

    @Override
    public SearchResultItemDisplay getDisplay() {
	return display;
    }

}
