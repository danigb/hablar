package com.calclab.hablar.signals.client;

import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.EventBus;
import com.calclab.suco.client.Suco;
import com.google.gwt.user.client.ui.HasText;

public class WindowTitlePresenter {
    public WindowTitlePresenter(EventBus eventBus, final HasText display) {
	final Msg i18n = Suco.get(Msg.class);

	eventBus.addHandler(UnattendedChatsChangedEvent.TYPE, new UnattendedMessageHandler() {
	    @Override
	    public void handleUnattendedMessage(UnattendedChatsChangedEvent event) {
		final int size = event.getUnattendedChatPages().getSize();
		String message = size == 0 ? "" : i18n.unreadChats(size);
		String oldTitle = display.getText();
		String newTitle = WindowTextHelper.updateTitle(message, oldTitle);
		display.setText(newTitle);
	    }
	});

    }

}
