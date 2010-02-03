package com.calclab.hablar.core.client.page;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.google.gwt.core.client.GWT;

public class Page<T extends Display> implements Presenter<T> {
    public static enum XVis {
	open, closed, hidden
    }
    protected final T display;
    protected final HablarEventBus eventBus;
    private final String pageType;
    private final PageID id;

    private String pageTitle, userMessage, pageIcon;
    private XVis visibility;

    public Page(String pageType, HablarEventBus eventBus, T display) {
	this.pageType = pageType;
	this.eventBus = eventBus;
	this.display = display;
	this.id = PageID.create();
	visibility = XVis.hidden;
    }

    public void addInfoChangedHandler(final PageInfoChangedHandler handler) {
	eventBus.addHandler(PageInfoChangedEvent.TYPE, new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(PageInfoChangedEvent event) {
		GWT.log("SAME: " + (event.getPage() == Page.this), null);
		if (event.getPage() == Page.this) {
		    handler.onPageInfoChanged(event);
		}
	    }
	});
    }

    public void addVisibilityChangedHandler(final VisibilityChangedHandler handler) {
	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		if (event.getPage() == Page.this) {
		    handler.onVisibilityChanged(event);
		}
	    }
	});
    }

    @Override
    public T getDisplay() {
	return display;
    }

    public PageID getId() {
	return id;
    }

    public String getPageIcon() {
	return pageIcon;
    }

    public String getPageTitle() {
	return pageTitle;
    }

    public String getPageType() {
	return pageType;
    }

    public String getUserMessage() {
	return userMessage;
    }

    public XVis getVisibility() {
	return visibility;
    }

    public void requestOpen() {
	eventBus.fireEvent(new OpenPageRequestEvent(this));
    }

    public void setPageIcon(String pageIcon) {
	this.pageIcon = pageIcon;
	eventBus.fireEvent(new PageInfoChangedEvent(this));
    }

    public void setPageTitle(String pageTitle) {
	this.pageTitle = pageTitle;
	eventBus.fireEvent(new PageInfoChangedEvent(this));
    }

    public void setUserMessage(String userMessage) {
	this.userMessage = userMessage;
    }

    public void setVisibility(XVis visibility) {
	this.visibility = visibility;
	eventBus.fireEvent(new VisibilityChangedEvent(this));
    }

}
