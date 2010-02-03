package com.calclab.hablar.chat.N;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.icon.PresenceIcon;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.chat.client.ui.ChatMessageFormatter;
import com.calclab.hablar.chat.client.ui.ChatPageView;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class ChatPresenter extends PagePresenter<ChatDisplay> {

    public static final String TYPE = "Chat";

    public ChatPresenter(HablarEventBus eventBus, final Chat chat, final ChatDisplay display) {
	super(TYPE, eventBus, display);
	final Msg i18n = Suco.get(Msg.class);
	final XmppURI fromURI = chat.getURI();
	final String name = getName(fromURI);

	getState().setPageTitle(name);
	getState().setPageIcon(HablarIcons.get(IconType.buddyOff));
	chat.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		final String body = ChatMessageFormatter.format(message.getBody());
		if (body != null) {
		    display.showMessage(name, body, ChatPageView.MessageType.incoming);
		    getState().setPageTitle(i18n.newChatFrom(name, ChatMessageFormatter.ellipsis(body, 25)));
		}
	    }
	});
	chat.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State state) {
		setState(state);
	    }
	});
	setState(chat.getState());

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		sendMessage(chat, display);
	    }

	});
	display.getTextBox().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    event.stopPropagation();
		    event.preventDefault();
		    sendMessage(chat, display);
		}
	    }
	});
    }

    public void setPresence(boolean available, Show show) {
	getState().setPageIcon(PresenceIcon.getIcon(available, show));
    }

    private String getName(final XmppURI fromURI) {
	final String name;
	Roster roster = Suco.get(Roster.class);
	RosterItem itemByJID = roster.getItemByJID(fromURI);
	if (itemByJID != null) {
	    name = itemByJID.getName();
	} else {
	    name = fromURI.getShortName();
	}
	return name;
    }

    private void sendMessage(final Chat chat, final ChatDisplay display) {
	String text = display.getBody().getText().trim();
	if (!text.isEmpty()) {
	    final String body = ChatMessageFormatter.format(text);
	    display.showMessage("me", body, ChatPageView.MessageType.sent);
	    chat.send(new Message(text));
	    display.clearAndFocus();
	}
    }

    private void setState(final State state) {
	final boolean visible = state == State.ready;
	display.setControlsVisible(visible);
    }

}
