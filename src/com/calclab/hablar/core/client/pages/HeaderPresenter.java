package com.calclab.hablar.core.client.pages;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.PageInfoChangedEvent;
import com.calclab.hablar.core.client.page.PageInfoChangedHandler;
import com.calclab.hablar.core.client.page.PageState;
import com.calclab.hablar.core.client.page.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.VisibilityChangedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.XVis;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HeaderPresenter implements Presenter<HeaderDisplay> {

    private final HeaderDisplay display;
    private String currentStyle;

    public HeaderPresenter(final PageState state, final HeaderDisplay display) {
	this.display = display;

	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		state.getPage().requestOpen();
	    }
	});

	display.getClose().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		event.preventDefault();
		state.getPage().requestHide();
	    }
	});

	state.addVisibilityChangedHandler(new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		XVis visibility = state.getVisibility();
		visibilityChanged(visibility);
	    }

	});
	visibilityChanged(state.getVisibility());

	state.addInfoChangedHandler(new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(PageInfoChangedEvent event) {
		display.setIconStyle(state.getPageIcon());
		display.getHeaderTitle().setText(state.getPageTitle());
	    }
	});

	display.setIconStyle(state.getPageIcon());
	display.getHeaderTitle().setText(state.getPageTitle());
	display.setCloseIconVisible(state.isCloseable());
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
