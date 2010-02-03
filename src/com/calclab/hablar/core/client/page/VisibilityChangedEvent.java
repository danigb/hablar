package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.GwtEvent;

public class VisibilityChangedEvent extends GwtEvent<VisibilityChangedHandler> {

    public static final Type<VisibilityChangedHandler> TYPE = new Type<VisibilityChangedHandler>();
    private final Page<?> pagePresenter;

    public VisibilityChangedEvent(Page<?> page) {
	this.pagePresenter = page;
    }

    @Override
    public Type<VisibilityChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPagePresenter() {
	return pagePresenter;
    }

    @Override
    protected void dispatch(VisibilityChangedHandler handler) {
	handler.onVisibilityChanged(this);
    }

}
