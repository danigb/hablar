package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.GwtEvent;

public class ClosePageRequestEvent extends GwtEvent<ClosePageRequestHandler> {

    public static final Type<ClosePageRequestHandler> TYPE = new Type<ClosePageRequestHandler>();
    private final Page<?> page;

    public ClosePageRequestEvent(Page<?> page) {
	this.page = page;
    }

    @Override
    public Type<ClosePageRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    protected void dispatch(ClosePageRequestHandler handler) {
	handler.onClosePageRequest(this);
    }

}
