package com.calclab.hablar.core.client.pages.accordion;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.basic.client.ui.pages.panel.AccordionPanel;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.pages.HeaderPresenter;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class AccordionContainer implements PagesContainer {
    private static final String TYPE = "Accordion";
    private static final double SIZE_HEADER = 24;
    private final AccordionPanel accordion;

    public AccordionContainer(LayoutPanel container) {
	accordion = new AccordionPanel();
	container.add(accordion);
	container.setWidgetLeftRight(accordion, 0, PX, 0, PX);
	container.setWidgetTopBottom(accordion, 0, PX, 0, PX);
	container.forceLayout();
    }

    @Override
    public boolean add(PagePresenter<?> page) {
	HeaderPresenter header = new HeaderPresenter(page.getState(), new AccordionHeaderWidget(page.getPageType()));
	accordion.add(page.getDisplay().asWidget(), header.getDisplay().asWidget(), SIZE_HEADER);
	return true;
    }

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public Widget getWidget() {
	return accordion;
    }

    @Override
    public boolean open(Page<?> page) {
	Widget widget = page.getDisplay().asWidget();
	boolean hasWidget = accordion.hasWidget(widget);
	if (hasWidget) {
	    accordion.showWidget(widget);
	}
	return hasWidget;
    }

    @Override
    public boolean remove(Page<?> page) {
	return accordion.remove(page.getDisplay().asWidget());
    }
}
