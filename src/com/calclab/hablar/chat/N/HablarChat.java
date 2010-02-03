package com.calclab.hablar.chat.N;

import com.calclab.hablar.chat.client.ChatConfig;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;

public class HablarChat {

    public static void install(Hablar hablar, ChatConfig config) {
	new ChatsPresenter(hablar, config);
    }

    public static void install(HablarWidget widget) {
	install(widget.getHablar(), ChatConfig.getFromMeta());
    }

}
