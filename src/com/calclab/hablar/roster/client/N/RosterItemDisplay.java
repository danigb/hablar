package com.calclab.hablar.roster.client.N;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface RosterItemDisplay extends Display {

    HasClickHandlers getAction();

    HasText getJid();

    HasText getName();

    HasText getStatus();

    void setIcon(String iconStyle);

    void setStatusVisible(boolean visible);

}
