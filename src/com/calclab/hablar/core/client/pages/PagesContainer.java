package com.calclab.hablar.core.client.pages;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.user.client.ui.Widget;

public interface PagesContainer {

    boolean add(Page<?> page);

    String getType();

    Widget getWidget();

    boolean open(Page<?> page);
}
