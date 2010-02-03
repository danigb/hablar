package com.calclab.hablar.core.client.pages;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PageInfoChangedEvent;
import com.calclab.hablar.core.client.page.PageInfoChangedHandler;
import com.calclab.hablar.core.client.page.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.VisibilityChangedHandler;
import com.calclab.hablar.core.client.page.Page.XVis;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HeaderPresenter implements Presenter<HeaderDisplay> {

    private final HeaderDisplay display;
    private String currentStyle;

    public HeaderPresenter(final Page<?> page, final HeaderDisplay display) {
	this.display = display;

	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		page.requestOpen();
	    }
	});

	page.addVisibilityChangedHandler(new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		XVis visibility = event.getPage().getVisibility();
		visibilityChanged(visibility);
	    }

	});
	visibilityChanged(page.getVisibility());

	page.addInfoChangedHandler(new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(PageInfoChangedEvent event) {
		GWT.log("INFO CHANGED: " + event.getPage().getPageTitle(), null);
		display.setIconStyle(event.getPage().getPageIcon());
	    }
	});

	display.setIconStyle(page.getPageIcon());
	display.getHeaderTitle().setText(page.getPageTitle());

    }

    public HeaderDisplay getDisplay() {
	return display;
    }

    private void visibilityChanged(XVis visibility) {
	if (currentStyle != null)
	    display.removeStyle(currentStyle);
	String newStyle = visibility.toString();
	display.setStyle(newStyle);
	currentStyle = newStyle;
    }

}
