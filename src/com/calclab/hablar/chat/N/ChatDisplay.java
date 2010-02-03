package com.calclab.hablar.chat.N;

import com.calclab.hablar.chat.client.ui.ChatPageView.MessageType;
import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface ChatDisplay extends Display {

    void clearAndFocus();

    HasClickHandlers getAction();

    HasText getBody();

    HasKeyDownHandlers getTextBox();

    void setControlsVisible(boolean visible);

    void showMessage(String name, String body, MessageType messageType);

}
