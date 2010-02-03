package com.calclab.hablar.core.client;

import com.calclab.hablar.basic.client.DefaultEventBus;
import com.calclab.hablar.basic.client.ui.HablarWidget.Layout;
import com.calclab.hablar.core.client.pages.OverlayContainer;
import com.calclab.hablar.core.client.pages.accordion.AccordionContainer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarWidget extends Composite implements HablarDisplay {
    private LayoutPanel panel;
    private final Hablar hablar;

    public HablarWidget(Layout layout) {
	initWidget(panel = new LayoutPanel());
	hablar = new Hablar(new DefaultEventBus(), this);
	hablar.addContainer(new OverlayContainer(panel));
	if (layout == Layout.accordion) {
	    hablar.addContainer(new AccordionContainer(panel));
	}
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    public Hablar getHablar() {
	return hablar;
    }

    public LayoutPanel getPanel() {
	return panel;
    }

}
