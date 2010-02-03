package com.calclab.hablar.roster.client.N;

import com.calclab.hablar.core.client.mvp.Display;

public interface RosterDisplay extends Display {

    void add(RosterItemDisplay itemDisplay);

    RosterItemDisplay newRosterItemDisplay();

    void remove(RosterItemDisplay itemDisplay);

}
