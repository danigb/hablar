package com.calclab.hablar.chat.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.google.gwt.core.client.EntryPoint;

public class HablarChat implements EntryPoint {

    public static void install(Hablar hablar, ChatConfig config) {
	new ChatManagerController(hablar, config);
    }

    public static void install(HablarWidget widget) {
	install(widget.getHablar(), ChatConfig.getFromMeta());
    }

    @Override
    public void onModuleLoad() {

    }

}
