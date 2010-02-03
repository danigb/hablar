package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.PagePresenter.XVis;

/**
 * All page presenters must implements this interface
 * 
 */
public interface Page<T extends Display> extends Presenter<T> {

    public void addInfoChangedHandler(final PageInfoChangedHandler handler);

    public void addVisibilityChangedHandler(final VisibilityChangedHandler handler);

    /**
     * All pages have a unique PageID reference
     */
    public PageID getId();

    public String getPageIcon();

    public String getPageTitle();

    public String getPageType();

    public String getUserMessage();

    public XVis getVisibility();

    public void requestHide();

    public void requestOpen();

    public void setPageIcon(String pageIcon);

    public void setPageTitle(String pageTitle);

    public void setUserMessage(String userMessage);

    public void setVisibility(XVis visibility);

}