package com.calclab.hablar.core.client;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.ClosePageRequestEvent;
import com.calclab.hablar.core.client.page.ClosePageRequestHandler;
import com.calclab.hablar.core.client.page.OpenPageRequestEvent;
import com.calclab.hablar.core.client.page.OpenPageRequestHandler;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.PagePresenter.XVis;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.google.gwt.core.client.GWT;

public class Hablar implements Presenter<HablarDisplay> {
    private final HablarDisplay display;
    private final HablarEventBus eventBus;
    private final ArrayList<PagePresenter<?>> pagePresenters;
    private final ArrayList<PagesContainer> containers;
    private Page<?> currentPagePresenter;

    public Hablar(HablarEventBus eventBus, HablarDisplay display) {
	this.eventBus = eventBus;
	this.display = display;
	this.pagePresenters = new ArrayList<PagePresenter<?>>();
	this.containers = new ArrayList<PagesContainer>();

	eventBus.addHandler(OpenPageRequestEvent.TYPE, new OpenPageRequestHandler() {
	    @Override
	    public void onOpenPageRequest(OpenPageRequestEvent event) {
		openPage(event.getPagePresenter());
	    }
	});

	eventBus.addHandler(ClosePageRequestEvent.TYPE, new ClosePageRequestHandler() {
	    @Override
	    public void onClosePageRequest(ClosePageRequestEvent event) {
		removePage(event.getPage());
	    }
	});
    }

    public void addContainer(PagesContainer container) {
	GWT.log("CONTAINER: " + container.getType(), null);
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
	    if (type.equals(c.getType())) {
		return c;
	    }
	}
	assert false : "Container not found.";
	return null;
    }

    protected void openPage(PagePresenter<?> page) {
	for (PagesContainer container : containers) {
	    if (container.open(page)) {
		if (currentPagePresenter != null)
		    currentPagePresenter.setVisibility(XVis.closed);
		currentPagePresenter = page;
		page.setVisibility(XVis.open);
		return;
	    }
	}
    }
}
