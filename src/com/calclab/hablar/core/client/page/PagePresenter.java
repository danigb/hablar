package com.calclab.hablar.core.client.page;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.core.client.GWT;

public class PagePresenter<T extends Display> implements Page<T> {
    public static enum XVis {
	open, closed, hidden
    }
    protected final T display;
    protected final HablarEventBus eventBus;
    private final String pageType;
    private final PageID id;

    private String pageTitle, userMessage, pageIcon;
    private XVis visibility;

    public PagePresenter(String pageType, HablarEventBus eventBus, T display) {
	this.pageType = pageType;
	this.eventBus = eventBus;
	this.display = display;
	this.id = PageID.create();
	visibility = XVis.hidden;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.calclab.hablar.core.client.page.Page#addInfoChangedHandler(com.calclab
     * .hablar.core.client.page.PageInfoChangedHandler)
     */
    public void addInfoChangedHandler(final PageInfoChangedHandler handler) {
	eventBus.addHandler(PageInfoChangedEvent.TYPE, new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(PageInfoChangedEvent event) {
		GWT.log("SAME: " + (event.getPagePresenter() == PagePresenter.this), null);
		if (event.getPagePresenter() == PagePresenter.this) {
		    handler.onPageInfoChanged(event);
		}
	    }
	});
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.calclab.hablar.core.client.page.Page#addVisibilityChangedHandler(
     * com.calclab.hablar.core.client.page.VisibilityChangedHandler)
     */
    public void addVisibilityChangedHandler(final VisibilityChangedHandler handler) {
	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		if (event.getPagePresenter() == PagePresenter.this) {
		    handler.onVisibilityChanged(event);
		}
	    }
	});
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.calclab.hablar.core.client.page.Page#getDisplay()
     */
    @Override
    public T getDisplay() {
	return display;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.calclab.hablar.core.client.page.Page#getId()
     */
    public PageID getId() {
	return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.calclab.hablar.core.client.page.Page#getPageIcon()
     */
    public String getPageIcon() {
	return pageIcon;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.calclab.hablar.core.client.page.Page#getPageTitle()
     */
    public String getPageTitle() {
	return pageTitle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.calclab.hablar.core.client.page.Page#getPageType()
     */
    public String getPageType() {
	return pageType;
    }

    public String getUserMessage() {
	return userMessage;
    }

    public XVis getVisibility() {
	return visibility;
    }

    @Override
    public void requestHide() {
	eventBus.fireEvent(new ClosePageRequestEvent(this));
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.calclab.hablar.core.client.page.Page#setVisibility(com.calclab.hablar
     * .core.client.page.PagePresenter.XVis)
     */
    public void setVisibility(XVis visibility) {
	this.visibility = visibility;
	eventBus.fireEvent(new VisibilityChangedEvent(this));
    }

}
