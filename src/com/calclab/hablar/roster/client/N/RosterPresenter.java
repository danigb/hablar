package com.calclab.hablar.roster.client.N;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class RosterPresenter extends Page<RosterDisplay> {
    private boolean active;
    private final Msg i18n;
    private final Roster roster;
    private final HashMap<XmppURI, RosterItemPresenter> items;

    public RosterPresenter(HablarEventBus eventBus, RosterDisplay display) {
	super("Roster", eventBus, display);
	i18n = Suco.get(Msg.class);
	roster = Suco.get(Roster.class);
	items = new HashMap<XmppURI, RosterItemPresenter>();

	setPageTitle(i18n.contacts());
	setPageIcon(HablarIcons.get(IconType.roster));
	addRosterListeners();
	addSessionListeners();

    }

    private void addRosterListeners() {
	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(final Collection<RosterItem> items) {
		GWT.log("Retrieved roster", null);
		for (final RosterItem item : items) {
		    getPresenter(item);
		}
	    }

	});

	roster.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getPresenter(item);
		// FIXME: i18n
		setUserMessage(item.getName() + " has been added to Contacts.");
	    }
	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getPresenter(item).setItem(item);
	    }
	});

	roster.onItemRemoved(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		display.remove(getPresenter(item).getDisplay());
		// FIXME: i18n
		setUserMessage(item.getJID().getNode() + " has been removed from Contacts.");
	    }
	});
    }

    private void addSessionListeners() {
	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		State state = session.getState();
		setSessionState(state);
	    }

	});
	setSessionState(session.getState());
    }

    private RosterItemPresenter getPresenter(final RosterItem item) {
	RosterItemPresenter presenter = items.get(item.getJID());
	if (presenter == null) {
	    RosterItemDisplay itemDisplay = display.newRosterItemDisplay();
	    presenter = new RosterItemPresenter(itemDisplay);
	    display.add(itemDisplay);
	    items.put(item.getJID(), presenter);
	}
	presenter.setItem(item);
	return presenter;
    }

    private void setSessionState(State state) {
	final boolean isActive = state == State.ready;
	if (active != isActive) {
	    active = isActive;
	    IconType name = active ? IconType.on : IconType.off;
	    setPageIcon(HablarIcons.get(name));
	}
    }

}
