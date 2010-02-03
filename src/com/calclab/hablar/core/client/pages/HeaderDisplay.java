package com.calclab.hablar.core.client.pages;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface HeaderDisplay extends Display {
    HasText getHeaderTitle();

    HasClickHandlers getOpen();

    void removeStyle(String styleName);

    void setIconStyle(String iconStyle);

    void setStyle(String styleName);
}
