package com.calclab.hablar.chat.N;

import java.util.HashMap;
import java.util.Set;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.chat.client.ChatConfig;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.page.PagePresenter.XVis;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class ChatsPresenter {

    public static interface ChatPageFactory {

	ChatDisplay create(boolean sendButtonVisible);
    }
    private final HashMap<XmppURI, ChatPresenter> chatPages;

    private final Roster roster;
    private final ChatPageFactory factory;
    private final boolean sendButtonVisible;

    private final Hablar hablar;

    public ChatsPresenter(Hablar hablar, ChatConfig config) {
	this(hablar, config, new ChatPageFactory() {
	    @Override
	    public ChatDisplay create(boolean sendButtonVisible) {
		return new ChatWidget(sendButtonVisible);
	    }
	});
    }

    public ChatsPresenter(Hablar hablar, ChatConfig config, ChatPageFactory factory) {
	this.hablar = hablar;
	this.factory = factory;
	this.chatPages = new HashMap<XmppURI, ChatPresenter>();

	roster = Suco.get(Roster.class);
	final ChatManager chatManager = Suco.get(ChatManager.class);

	if (config.openChat != null) {
	    chatManager.open(config.openChat);
	}

	chatManager.onChatCreated(new Listener<Chat>() {
	    @Override
	    public void onEvent(Chat chat) {
		createChat(chat, XVis.closed);
	    }
	});

	chatManager.onChatOpened(new Listener<Chat>() {
	    @Override
	    public void onEvent(Chat chat) {
		ChatPresenter page = chatPages.get(chat.getURI());
		assert page != null;
		page.requestOpen();
	    }
	});
	roster.onItemChanged(new Listener<RosterItem>() {
	    public void onEvent(RosterItem item) {
		XmppURI jid = item.getJID();
		Set<XmppURI> chats = chatPages.keySet();
		for (XmppURI chatURI : chats) {
		    if (chatURI.equalsNoResource(jid)) {
			ChatPresenter page = chatPages.get(chatURI);
			page.setPresence(item.isAvailable(), item.getShow());
		    }
		}
	    }
	});

	sendButtonVisible = config.sendButtonVisible;

    }

    private void createChat(Chat chat, XVis visibility) {
	ChatDisplay display = factory.create(sendButtonVisible);
	ChatPresenter presenter = new ChatPresenter(hablar.getEventBus(), chat, display);
	chatPages.put(chat.getURI(), presenter);
	hablar.addPage(presenter);

	RosterItem item = roster.getItemByJID(chat.getURI().getJID());
	Show show = item != null ? item.getShow() : Show.unknown;
	boolean available = item != null ? item.isAvailable() : false;
	presenter.setPresence(available, show);
    }

}
