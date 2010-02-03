package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.GwtEvent;

public class OpenPageRequestEvent extends GwtEvent<OpenPageRequestHandler> {

    public static final Type<OpenPageRequestHandler> TYPE = new Type<OpenPageRequestHandler>();
    private final Page<?> page;

    public OpenPageRequestEvent(Page<?> page) {
	this.page = page;
    }

    @Override
    public Type<OpenPageRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    protected void dispatch(OpenPageRequestHandler handler) {
	handler.onOpenPageRequest(this);
    }

}
