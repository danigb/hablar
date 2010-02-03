package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.GwtEvent;

public class OpenPageRequestEvent extends GwtEvent<OpenPageRequestHandler> {

    public static final Type<OpenPageRequestHandler> TYPE = new Type<OpenPageRequestHandler>();
    private final PagePresenter<?> pagePresenter;

    public OpenPageRequestEvent(PagePresenter<?> page) {
	this.pagePresenter = page;
    }

    @Override
    public Type<OpenPageRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public PagePresenter<?> getPagePresenter() {
	return pagePresenter;
    }

    @Override
    protected void dispatch(OpenPageRequestHandler handler) {
	handler.onOpenPageRequest(this);
    }

}
