package com.calclab.hablar.basic.client.ui.icon;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;

public class PresenceIcon {
    public static HablarIcons.IconType getIcon(boolean isAvailable, Show show) {
	if (show == Show.dnd) {
	    return HablarIcons.IconType.buddyDnd;
	} else if (show == Show.xa) {
	    return HablarIcons.IconType.buddyWait;
	} else if (show == Show.away) {
	    return HablarIcons.IconType.buddyWait;
	} else if (isAvailable) {
	    return HablarIcons.IconType.buddyOn;
	} else {
	    return HablarIcons.IconType.buddyOff;
	}
    }

    public static String getIcon(RosterItem item) {
	return HablarIcons.get(getIcon(item.isAvailable(), item.getShow()));
    }
}
