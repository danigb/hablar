package com.calclab.hablar.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;

/**
 * 
 * Emite i18n messages
 * 
 * @see <a href='http://code.google.com/intl/es-ES/webtoolkit/doc/latest/DevGuideI18n.html#DevGuideA
 *      n n o t a t i o n s ' > I18nAnnotations</a>
 * @see <a href=
 *      'http://code.google.com/intl/en/webtoolkit/doc/latest/DevGuideUiBinderI1
 *      8 n . h t m l ' >UiBinderAnnotations</a>
 */
@DefaultLocale("en_GB")
// Line below defaults to I18N_default.xlf, I18N_de.xlf, etc
// GenerateKeys defaults to MD5 hash of text and meaning
@GenerateKeys
public interface Mes extends Messages {
    public static class sa {
	private static Mes instance = null;

	public static synchronized Mes ge() {
	    // FIXME: A proposal to use a short and readable method call
	    // (refactorize to propose another):
	    // Mes.sa.ge.login();
	    if (instance == null) {
		instance = (Mes) GWT.create(Mes.class);
	    }
	    return instance;
	}
    }

    @DefaultMessage(Msg.CONNECTED_AS + " {0}")
    @Description("The specified user has connected")
    String conectedAs(@Example("john.doe") String userName);

    @DefaultMessage(Msg.CONTACTS)
    @Description("The roster panel title")
    String contacts();

    @DefaultMessage(Msg.DISCONNECTED)
    String disconnected();

    @DefaultMessage("Sign in")
    String login();

    @DefaultMessage("Sign out")
    String logout();

    @DefaultMessage(Msg.ROSTER_DISABLED)
    String rosterDisabled();

    @DefaultMessage("Wait...")
    String waitDots();
}