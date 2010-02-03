package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.GwtEvent;

public class PageInfoChangedEvent extends GwtEvent<PageInfoChangedHandler> {

    public static final Type<PageInfoChangedHandler> TYPE = new Type<PageInfoChangedHandler>();
    private final Page<?> page;

    public PageInfoChangedEvent(Page<?> page) {
	this.page = page;
    }

    @Override
    public Type<PageInfoChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    protected void dispatch(PageInfoChangedHandler handler) {
	handler.onPageInfoChanged(this);
    }

}
