package com.calclab.hablar.login.client.N;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends Composite implements LoginDisplay {

    interface LoginWidgetUiBinder extends UiBinder<Widget, LoginWidget> {
    }

    private static LoginWidgetUiBinder uiBinder = GWT.create(LoginWidgetUiBinder.class);

    @UiField
    Button button;

    @UiField
    TextBox user, password;

    public LoginWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getAction() {
	return button;
    }

    @Override
    public HasText getActionText() {
	return button;
    }

    @Override
    public HasText getPassword() {
	return password;
    }

    @Override
    public HasText getUser() {
	return user;
    }

    @Override
    public void setActionEnabled(boolean enabled) {
	button.setEnabled(enabled);
    }

}
