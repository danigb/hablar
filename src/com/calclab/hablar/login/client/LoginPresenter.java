package com.calclab.hablar.login.client;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class LoginPresenter extends PagePresenter<LoginDisplay> {
    private static int index = 0;
    private final Session session;

    public LoginPresenter(HablarEventBus eventBus, LoginDisplay display) {
	super("Login", "" + (++index), eventBus, display);
	this.session = Suco.get(Session.class);
	getState().setPageIcon(HablarIcons.get(IconType.off));

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (session.getState() == State.disconnected) {
		    login();
		} else {
		    logout();
		}
	    }
	});

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		setState(session.getState());
	    }
	});
	setState(session.getState());
    }

    private void setState(final State state) {
	String actionText, pageTitle, pageIcon;
	boolean actionEnabled;
	if (state == State.ready) {
	    actionText = i18n().logout();
	    actionEnabled = true;
	    final String userName = session.getCurrentUser().getNode();
	    pageTitle = i18n().connectedAs(userName);
	    pageIcon = HablarIcons.get(IconType.on);
	} else if (state == State.disconnected) {
	    actionText = i18n().login();
	    actionEnabled = true;
	    pageTitle = i18n().disconnected();
	    pageIcon = HablarIcons.get(IconType.off);
	} else {
	    pageTitle = actionText = i18n().waitDots();
	    actionEnabled = false;
	    pageIcon = HablarIcons.get(IconType.off);
	}
	display.getActionText().setText(actionText);
	display.setActionEnabled(actionEnabled);
	display.addMessage("Session: " + state.toString());
	getState().setPageTitle(pageTitle);
	getState().setPageIcon(pageIcon);
	getState().setUserMessage("Session state: " + state);
    }

    protected void login() {
	String user = display.getUser().getText();
	String password = display.getPassword().getText();
	session.login(XmppURI.uri(user), password);
    }

    protected void logout() {
	session.logout();
    }
}
