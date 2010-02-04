package com.calclab.hablar.editbuddy.client;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.i18n.Msg;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.pages.OverlayContainer;
import com.calclab.hablar.core.client.ui.menu.MenuAction;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyDisplay;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Presenter in MVP pattern. Controls the EditBuddy form
 */
public class EditBuddyPresenter extends PagePresenter<EditBuddyDisplay> {
    private static int index = 0;
    protected static final String[] EMPTY_ARRAY = new String[0];
    private final Hablar hablar;
    private final Msg i18n;
    private final MenuAction<RosterItem> action;
    private final Roster roster;
    private RosterItem currentItem;

    public EditBuddyPresenter(Hablar hablar, EditBuddyDisplay display) {
	super("EditButty", "" + (++index), hablar.getEventBus(), display);
	this.hablar = hablar;
	i18n = Suco.get(Msg.class);
	roster = Suco.get(Roster.class);

	this.action = new MenuAction<RosterItem>(i18n.changeNickName(), "EditBuddy-editAction") {
	    @Override
	    public void execute(RosterItem target) {
		onChangeNickName(target);
	    }
	};
	bind();
    }

    public MenuAction<RosterItem> getAction() {
	return action;
    }

    private void bind() {
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		cancelEdit();
	    }
	});
	display.getSave().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		updateCurrentItem();
	    }
	});
	display.getEnterAction().addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		updateCurrentItem();
	    }
	});
    }

    private void cancelEdit() {
	hablar.removePage(this);
    }

    private void onChangeNickName(RosterItem target) {
	this.currentItem = target;
	String nickName = target.getName();
	display.getOldNickName().setText(nickName);
	display.getNewNickName().setText(nickName);
	hablar.addPage(this, OverlayContainer.TYPE);
	display.getFirstFocusable().setFocus(true);
    }

    private void updateCurrentItem() {
	assert currentItem != null;
	String newName = display.getNewNickName().getText();
	if (!currentItem.getName().equals(newName)) {
	    roster.updateItem(currentItem.getJID(), newName, currentItem.getGroups().toArray(EMPTY_ARRAY));
	}
	hablar.removePage(this);
    }
}
