package com.calclab.hablar.selenium.roster;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.ui.HablarLogic;
import com.calclab.hablar.basic.client.ui.utils.DebugId;
import com.calclab.hablar.selenium.PageObject;

public class RosterPageObject extends PageObject {

    @FindBy(id = "gwt-debug-RosterWidget-disabledPanel")
    private RenderedWebElement disabledLabel;

    @FindBy(id = "gwt-debug-AccordionHeaderWidget-Roster")
    private RenderedWebElement header;

    @FindBy(id = DebugId.PRE + HablarLogic.SEARCH_ICON)
    private RenderedWebElement searchIcon;

    @FindBy(id = "gwt-debug-HablarOpenChat-openAction")
    private RenderedWebElement openChatIcon;

    public RosterPageObject() {
    }

    public RenderedWebElement getDisableLabel() {
	return disabledLabel;
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getItemMenu(final String jid) {
	return null;
    }

    public WebElement getSearchIcon() {
	return searchIcon;
    }

    public WebElement OpenChatIcon() {
	return openChatIcon;
    }
}
