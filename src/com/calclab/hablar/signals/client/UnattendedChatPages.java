package com.calclab.hablar.signals.client;

import java.util.HashSet;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.UserMessageChangedHandler;
import com.calclab.hablar.core.client.page.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.VisibilityChangedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.XVis;

/**
 * A registry of unattended chat pages. It listen to events and tracks which
 * chat are unattended
 * 
 */
public class UnattendedChatPages {

    private final HablarEventBus eventBus;
    private final HashSet<Page<?>> unattendedChatPages;

    public UnattendedChatPages(HablarEventBus hablarEventBus) {
	this.eventBus = hablarEventBus;
	unattendedChatPages = new HashSet<Page<?>>();
	bind();
    }

    public int getSize() {
	return unattendedChatPages.size();
    }

    public void onChatClosed(final Page<?> chatPage) {
	if (unattendedChatPages.remove(chatPage)) {
	}
    }

    public void onNewMsg(final Page<?> chatPage) {
	XVis visibility = chatPage.getState().getVisibility();
	if (visibility != XVis.open && unattendedChatPages.add(chatPage)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	}
    }

    private void bind() {
	eventBus.addHandler(UserMessageChangedEvent.TYPE, new UserMessageChangedHandler() {

	    @Override
	    public void onUserMessageChanged(UserMessageChangedEvent event) {
		Page<?> page = event.getPage();
		if (isChatPage(page)) {
		    onNewMsg(page);
		}
	    }
	});

	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		Page<?> page = event.getPage();
		if (isChatPage(page)) {
		    onChatVisibilityChanged(page);
		}
	    }

	});

    }

    private boolean isChatPage(final Page<?> page) {
	return page.getPageType() == ChatPresenter.TYPE;
    }

    private void onChatVisibilityChanged(Page<?> page) {
	XVis visibility = page.getState().getVisibility();
	if (visibility == XVis.open && unattendedChatPages.remove(page)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	} else if (unattendedChatPages.remove(page)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	}
    }

}
