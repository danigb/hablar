package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.LoggerEventBus;
import com.calclab.hablar.core.client.pages.OverlayContainer;
import com.calclab.hablar.core.client.pages.accordion.AccordionContainer;
import com.calclab.hablar.core.client.pages.tabs.TabsContainer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarWidget extends Composite implements HablarDisplay {
    private LayoutPanel panel;
    private final Hablar hablarPresenter;

    public HablarWidget(HablarDisplay.Layout layout) {
	initWidget(panel = new LayoutPanel());
	hablarPresenter = new HablarPresenter(new LoggerEventBus(), this);
	hablarPresenter.addContainer(new OverlayContainer(panel));
	if (layout == HablarDisplay.Layout.accordion) {
	    hablarPresenter.addContainer(new AccordionContainer(panel));
	} else if (layout == HablarDisplay.Layout.tabs) {
	    hablarPresenter.addContainer(new TabsContainer(panel));
	}
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    public Hablar getHablar() {
	return hablarPresenter;
    }

    public LayoutPanel getPanel() {
	return panel;
    }

}
