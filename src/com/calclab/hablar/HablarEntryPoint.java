package com.calclab.hablar;

import com.calclab.hablar.chat.N.HablarChat;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.login.client.N.HablarLogin;
import com.calclab.hablar.openchat.client.HablarOpenChat;
import com.calclab.hablar.roster.client.N.HablarRoster;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final HablarConfig config = HablarConfig.getFromMeta();
	final HablarWidget widget = new HablarWidget(config.layout);

	HablarChat.install(widget);

	if (config.hasLogin) {
	    HablarLogin.install(widget);
	}

	if (config.hasRoster) {
	    HablarRoster.install(widget);
	    HablarOpenChat.install(widget);
	    // HablarEditBuddy.install(widget);
	}

	if (config.inline == null) {
	    createDialog(widget, config);
	} else {
	    addHablarToDiv(widget, config);
	}
    }

    private void addHablarToDiv(final HablarWidget hablar, final HablarConfig config) {
	setSize(hablar, config);
	RootPanel rootPanel = RootPanel.get(config.inline);
	if (rootPanel != null) {
	    rootPanel.add(hablar);
	} else {
	    throw new RuntimeException("The div with id " + config.inline + " is not found.");
	}
    }

    private DialogBox createDialog(final HablarWidget widget, HablarConfig config) {
	DialogBox dialog = new DialogBox();
	dialog.setText("Hablar");
	setSize(dialog, config);
	dialog.show();
	dialog.center();
	return dialog;
    }

    private void setSize(final Widget widget, final HablarConfig config) {
	if (config.width != null) {
	    widget.setWidth(config.width);
	}
	if (config.height != null) {
	    widget.setHeight(config.height);
	}
    }

}
