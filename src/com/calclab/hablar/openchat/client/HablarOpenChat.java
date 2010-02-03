package com.calclab.hablar.openchat.client;

import java.util.List;

import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.OverlayContainer;
import com.calclab.hablar.openchat.client.ui.OpenChatPresenter;
import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.calclab.hablar.roster.client.RosterView;
import com.calclab.hablar.roster.client.N.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarOpenChat implements EntryPoint {

    public static void install(final HablarWidget widget) {
	final Hablar hablar = widget.getHablar();
	final OpenChatPresenter openChat = new OpenChatPresenter(hablar, new OpenChatWidget());

	String iconStyle = HablarIcons.get(IconType.chatAdd);
	List<Page<?>> rosters = hablar.getPagesOfType(RosterView.TYPE);
	for (Page<?> roster : rosters) {
	    ((RosterPresenter) roster).addAction(iconStyle, "HablarOpenChat-openAction", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    hablar.addPage(openChat, OverlayContainer.TYPE);
		}
	    });
	}
    }

    @Override
    public void onModuleLoad() {
    }

}
