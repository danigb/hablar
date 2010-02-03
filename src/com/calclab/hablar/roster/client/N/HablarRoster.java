package com.calclab.hablar.roster.client.N;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class HablarRoster {

    public static void createLoginPage(final Hablar hablar, Session session) {
	final RosterPresenter roster = new RosterPresenter(hablar.getEventBus(), new RosterWidget());
	hablar.addPage(roster);

	session.onStateChanged(new Listener<Session>() {

	    @Override
	    public void onEvent(Session session) {
		setState(roster, session.getState());
	    }
	});
	setState(roster, session.getState());
    }

    public static void install(HablarWidget widget) {
	createLoginPage(widget.getHablar(), Suco.get(Session.class));
    }

    private static void setState(final RosterPresenter roster, State state) {
	if (state == State.ready) {
	    roster.requestOpen();
	}
    }

}
