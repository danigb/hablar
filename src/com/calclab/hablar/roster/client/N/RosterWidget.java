package com.calclab.hablar.roster.client.N;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterWidget extends Composite implements RosterDisplay {

    interface RosterWidgetUiBinder extends UiBinder<Widget, RosterWidget> {
    }

    private static RosterWidgetUiBinder uiBinder = GWT.create(RosterWidgetUiBinder.class);

    @UiField
    FlowPanel list;

    public RosterWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void add(RosterItemDisplay itemDisplay) {
	list.add(itemDisplay.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public RosterItemDisplay newRosterItemDisplay() {
	return new RosterItemWidget();
    }

    @Override
    public void remove(RosterItemDisplay itemDisplay) {
	list.remove(itemDisplay.asWidget());
    }

}
