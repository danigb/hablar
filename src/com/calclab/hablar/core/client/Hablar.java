package com.calclab.hablar.core.client;

import java.util.ArrayList;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.OpenPageRequestEvent;
import com.calclab.hablar.core.client.page.OpenPageRequestHandler;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.Page.XVis;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.google.gwt.core.client.GWT;

public class Hablar implements Presenter<HablarDisplay> {
    private final HablarDisplay display;
    private final HablarEventBus eventBus;
    private final ArrayList<Page<?>> pages;
    private final ArrayList<PagesContainer> containers;
    private Page<?> currentPage;

    public Hablar(HablarEventBus eventBus, HablarDisplay display) {
	this.eventBus = eventBus;
	this.display = display;
	this.pages = new ArrayList<Page<?>>();
	this.containers = new ArrayList<PagesContainer>();

	eventBus.addHandler(OpenPageRequestEvent.TYPE, new OpenPageRequestHandler() {
	    @Override
	    public void onOpenPageRequest(OpenPageRequestEvent event) {
		openPage(event.getPage());
	    }
	});
    }

    public void addContainer(PagesContainer container) {
	GWT.log("CONTAINER: " + container.getType(), null);
	containers.add(0, container);
    }

    public void addPage(Page<?> page) {
	pages.add(page);
	for (PagesContainer container : containers) {
	    if (container.add(page)) {
		return;
	    }
	}
    }

    @Override
    public HablarDisplay getDisplay() {
	return display;
    }

    public HablarEventBus getEventBus() {
	return eventBus;
    }

    protected void openPage(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.open(page)) {
		if (currentPage != null)
		    currentPage.setVisibility(XVis.closed);
		currentPage = page;
		page.setVisibility(XVis.open);
		return;
	    }
	}
    }
}
