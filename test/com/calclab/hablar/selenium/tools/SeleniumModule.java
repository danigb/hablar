package com.calclab.hablar.selenium.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.calclab.hablar.selenium.chat.ChatPageObject;
import com.calclab.hablar.selenium.login.LoginPageObject;
import com.calclab.hablar.selenium.openchat.OpenChatPageObject;
import com.calclab.hablar.selenium.roster.RosterPageObject;
import com.calclab.hablar.selenium.search.SearchPageObject;
import com.calclab.suco.client.ioc.decorator.Singleton;
import com.calclab.suco.client.ioc.module.AbstractModule;
import com.calclab.suco.client.ioc.module.Factory;

public class SeleniumModule extends AbstractModule {

    public SeleniumModule() {
    }

    @Override
    protected void onInstall() {

	register(Singleton.class, new Factory<WebDriver>(WebDriver.class) {
	    @Override
	    public WebDriver create() {
		return new FirefoxDriver(SeleniumConstants.FIREFOX_PROFILE_NAME);
	    }
	});

	register(Singleton.class, new Factory<GenericWebTester>(GenericWebTester.class) {
	    @Override
	    public GenericWebTester create() {
		return new GenericWebTester($(WebDriver.class),
			"http://localhost:8888/Hablar.html?gwt.codesvr=127.0.1.1:9997");
	    }
	});

	register(Singleton.class, new Factory<I18nHelper>(I18nHelper.class) {
	    @Override
	    public I18nHelper create() {
		return new I18nHelper();
	    }
	});

	register(Singleton.class, new Factory<ElementLocatorFactory>(ElementLocatorFactory.class) {
	    @Override
	    public ElementLocatorFactory create() {
		// The number is the timeout
		return new AjaxElementLocatorFactory($(WebDriver.class), SeleniumConstants.TIMEOUT);
	    }
	});

	register(Singleton.class, new Factory<LoginPageObject>(LoginPageObject.class) {
	    @Override
	    public LoginPageObject create() {
		final LoginPageObject login = new LoginPageObject();
		PageFactory.initElements($(ElementLocatorFactory.class), login);
		return login;
	    }
	});

	register(Singleton.class, new Factory<SearchPageObject>(SearchPageObject.class) {
	    @Override
	    public SearchPageObject create() {
		final SearchPageObject search = new SearchPageObject();
		PageFactory.initElements($(ElementLocatorFactory.class), search);
		return search;
	    }
	});

	register(Singleton.class, new Factory<OpenChatPageObject>(OpenChatPageObject.class) {
	    @Override
	    public OpenChatPageObject create() {
		OpenChatPageObject page = new OpenChatPageObject();
		PageFactory.initElements($(ElementLocatorFactory.class), page);
		return page;
	    }
	});

	register(Singleton.class, new Factory<RosterPageObject>(RosterPageObject.class) {
	    @Override
	    public RosterPageObject create() {
		final RosterPageObject roster = new RosterPageObject();
		PageFactory.initElements($(ElementLocatorFactory.class), roster);
		return roster;
	    }
	});

	register(Singleton.class, new Factory<ChatPageObject>(ChatPageObject.class) {
	    @Override
	    public ChatPageObject create() {
		final ChatPageObject chat = new ChatPageObject();
		PageFactory.initElements($(ElementLocatorFactory.class), chat);
		return chat;
	    }
	});

    }
}
