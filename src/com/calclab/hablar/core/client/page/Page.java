package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.PagePresenter.XVis;

/**
 * All page presenters must implements this interface
 * 
 */
public interface Page<T extends Display> extends Presenter<T> {

    /**
     * All pages have a unique PageID reference
     */
    public PageID getId();

    public PageState getState();

    public String getPageType();

    public void requestHide();

    public void requestOpen();

    public void setVisibility(XVis closed);

}