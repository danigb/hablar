package com.calclab.hablar.roster.client.N;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class RosterPresenter extends Page<RosterDisplay> {
    private boolean active;
    private final Msg i18n;
    private final Roster roster;
    private final HashMap<XmppURI, RosterItemPresenter> items;
    private final ChatManager manager;

    public RosterPresenter(HablarEventBus eventBus, RosterDisplay display) {
	super("Roster", eventBus, display);
	i18n = Suco.get(Msg.class);
	manager = Suco.get(ChatManager.class);
	roster = Suco.get(Roster.class);
	items = new HashMap<XmppURI, RosterItemPresenter>();
	active = true;

	setPageTitle(i18n.contacts());
	setPageIcon(HablarIcons.get(IconType.roster));
	addRosterListeners();
	addSessionListeners();

    }

    public void addAction(String iconStyle, String debugId, ClickHandler clickHandler) {
	display.addAction(iconStyle, debugId, clickHandler);
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

    private RosterItemPresenter createRosterItem(final RosterItem item) {
	RosterItemDisplay itemDisplay = display.newRosterItemDisplay();
	RosterItemPresenter presenter = new RosterItemPresenter(itemDisplay);
	display.add(itemDisplay);
	items.put(item.getJID(), presenter);
	itemDisplay.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		manager.open(item.getJID());
	    }
	});
	return presenter;
    }

    private RosterItemPresenter getPresenter(final RosterItem item) {
	RosterItemPresenter presenter = items.get(item.getJID());
	if (presenter == null) {
	    presenter = createRosterItem(item);
	}
	presenter.setItem(item);
	return presenter;
    }

    private void setSessionState(State state) {
	final boolean isActive = state == State.ready;
	if (active != isActive) {
	    active = isActive;
	    display.setActive(active);
	}
    }

}
