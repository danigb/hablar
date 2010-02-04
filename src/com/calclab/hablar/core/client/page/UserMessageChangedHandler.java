package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.EventHandler;

public interface UserMessageChangedHandler extends EventHandler {

    void onUserMessageChanged(UserMessageChangedEvent event);

}
