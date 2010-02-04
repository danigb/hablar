package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.Display;

public interface HablarDisplay extends Display {
    public static enum Layout {
	accordion, tabs
    }

    public static final String ROL_MAIN = "Main";

}
