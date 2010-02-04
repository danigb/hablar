package com.calclab.hablar.search.client;

import java.util.List;

import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.roster.client.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarSearch implements EntryPoint {

    public static void install(HablarWidget widget) {
	Hablar hablar = widget.getHablar();
	final SearchPresenter search = new SearchPresenter(hablar.getEventBus(), new SearchWidget());
	List<PagePresenter<?>> rosters = hablar.getPagePresentersOfType(RosterPresenter.TYPE);
	boolean visible = rosters.size() == 0;
	if (visible)
	    hablar.addPage(search);

	String iconStyle = HablarIcons.get(IconType.search);
	String debugId = "HablarLogic-searchAction";

	for (Page<?> roster : rosters) {
	    ((RosterPresenter) roster).addAction(iconStyle, debugId, new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    GWT.log("Show search", null);
		    search.requestOpen();
		}
	    });
	}
    }

    @Override
    public void onModuleLoad() {
    }

}
