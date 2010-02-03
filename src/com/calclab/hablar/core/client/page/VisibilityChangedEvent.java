package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.GwtEvent;

public class VisibilityChangedEvent extends GwtEvent<VisibilityChangedHandler> {

    public static final Type<VisibilityChangedHandler> TYPE = new Type<VisibilityChangedHandler>();
    private final Page<?> page;

    public VisibilityChangedEvent(Page<?> page) {
	this.page = page;
    }

    @Override
    public Type<VisibilityChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    protected void dispatch(VisibilityChangedHandler handler) {
	handler.onVisibilityChanged(this);
    }

}
