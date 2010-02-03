package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.EventHandler;

public interface ClosePageRequestHandler extends EventHandler {

    void onClosePageRequest(ClosePageRequestEvent event);

}
