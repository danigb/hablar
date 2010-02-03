package com.calclab.hablar.core.client.page;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Display;

public class PagePresenter<T extends Display> implements Page<T> {
    public static enum XVis {
	open, closed, hidden
    }
    protected final T display;
    protected final HablarEventBus eventBus;
    private final String pageType;
    private final PageID id;

    private final PageState state;

    public PagePresenter(String pageType, HablarEventBus eventBus, T display) {
	this.pageType = pageType;
	this.eventBus = eventBus;
	this.display = display;
	this.id = PageID.create();
	state = new PageState(eventBus, this);
    }

    @Override
    public T getDisplay() {
	return display;
    }

    public PageID getId() {
	return id;
    }

    @Override
    public PageState getState() {
	return state;
    }

    public String getPageType() {
	return pageType;
    }

    @Override
    public void requestHide() {
	eventBus.fireEvent(new ClosePageRequestEvent(this));
    }

    public void requestOpen() {
	eventBus.fireEvent(new OpenPageRequestEvent(this));
    }

    @Override
    public void setVisibility(XVis visibility) {
	state.setVisibility(visibility);
    }

}
