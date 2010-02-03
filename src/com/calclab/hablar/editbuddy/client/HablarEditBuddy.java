package com.calclab.hablar.editbuddy.client;

import java.util.List;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyWidget;
import com.calclab.hablar.roster.client.RosterView;
import com.calclab.hablar.roster.client.N.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;

public class HablarEditBuddy implements EntryPoint {

    public static void install(HablarWidget widget) {
	EditBuddyPresenter logic = new EditBuddyPresenter(widget.getHablar(), new EditBuddyWidget());
	install(logic, widget.getHablar());
    }

    private static void install(EditBuddyPresenter logic, Hablar hablar) {
	List<PagePresenter<?>> rosters = hablar.getPagePresentersOfType(RosterView.TYPE);
	for (Page<?> page : rosters) {
	    RosterPresenter roster = (RosterPresenter) page;
	    roster.getItemMenu().addAction(logic.getAction());
	}
    }

    @Override
    public void onModuleLoad() {
    }

}
