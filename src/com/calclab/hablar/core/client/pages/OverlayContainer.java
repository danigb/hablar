package com.calclab.hablar.core.client.pages;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class OverlayContainer implements PagesContainer {
    private static final String TYPE = "Overlay";
    private final LayoutPanel panel;
    private Page<?> currentPage;

    public OverlayContainer(LayoutPanel container) {
	this.panel = new LayoutPanel();
	container.add(panel);
	container.setWidgetLeftRight(panel, 0, PX, 0, PX);
	container.setWidgetTopBottom(panel, 0, PX, 0, PX);
	container.forceLayout();
	panel.setVisible(false);
    }

    @Override
    public boolean add(Page<?> page) {
	assert currentPage == null : "Only one page in overlay";
	this.currentPage = page;
	return true;
    }

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public Widget getWidget() {
	return panel;
    }

    @Override
    public boolean open(Page<?> page) {
	return false;
    }

}
