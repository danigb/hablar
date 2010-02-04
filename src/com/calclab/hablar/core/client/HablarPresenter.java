package com.calclab.hablar.core.client;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.FocusPageRequestEvent;
import com.calclab.hablar.core.client.page.events.FocusPageRequestHandler;
import com.calclab.hablar.core.client.page.events.HidePageRequestEvent;
import com.calclab.hablar.core.client.page.events.HidePageRequestHandler;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.google.gwt.core.client.GWT;

public class HablarPresenter implements Hablar {
    private final HablarDisplay display;
    private final HablarEventBus eventBus;
    private final ArrayList<PagePresenter<?>> pagePresenters;
    private final ArrayList<PagesContainer> containers;
    private Page<?> currentPage;

    public HablarPresenter(HablarEventBus eventBus, HablarDisplay display) {
	this.eventBus = eventBus;
	this.display = display;
	this.pagePresenters = new ArrayList<PagePresenter<?>>();
	this.containers = new ArrayList<PagesContainer>();

	eventBus.addHandler(FocusPageRequestEvent.TYPE, new FocusPageRequestHandler() {
	    @Override
	    public void onFocusPageRequest(FocusPageRequestEvent event) {
		focusPage(event.getPagePresenter());
	    }
	});

	eventBus.addHandler(HidePageRequestEvent.TYPE, new HidePageRequestHandler() {
	    @Override
	    public void onHidePageRequest(HidePageRequestEvent event) {
		hidePage(event.getPage());
	    }
	});
    }

    public void addContainer(PagesContainer container) {
	GWT.log("CONTAINER: " + container.getRol(), null);
	containers.add(0, container);
    }

    public void addPage(PagePresenter<?> page) {
	pagePresenters.add(page);
	for (PagesContainer container : containers) {
	    if (container.add(page)) {
		GWT.log("ADD " + page.getId(), null);
		return;
	    }
	}
    }

    public void addPage(PagePresenter<?> page, String containerType) {
	PagesContainer container = getContainer(containerType);
	container.add(page);
    }

    @Override
    public HablarDisplay getDisplay() {
	return display;
    }

    public HablarEventBus getEventBus() {
	return eventBus;
    }

    public List<PagePresenter<?>> getPagePresentersOfType(String type) {
	ArrayList<PagePresenter<?>> list = new ArrayList<PagePresenter<?>>();
	for (PagePresenter<?> page : pagePresenters) {
	    if (type.equals(page.getPageType())) {
		list.add(page);
	    }
	}
	return list;
    }

    public void removePage(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.remove(page)) {
		return;
	    }
	}
    }

    private PagesContainer getContainer(String type) {
	for (PagesContainer c : containers) {
	    if (type.equals(c.getRol())) {
		return c;
	    }
	}
	assert false : "Container not found.";
	return null;
    }

    protected void focusPage(PagePresenter<?> page) {
	for (PagesContainer container : containers) {
	    if (container.focus(page)) {
		if (currentPage != null)
		    currentPage.setVisibility(Visibility.notFocused);
		currentPage = page;
		page.setVisibility(Visibility.focused);
		return;
	    }
	}
    }

    protected void hidePage(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.hide(page)) {
		page.setVisibility(Visibility.hidden);
		return;
	    }
	}
    }
}
