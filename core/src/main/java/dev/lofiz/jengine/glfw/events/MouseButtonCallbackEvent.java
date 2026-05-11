package dev.lofiz.jengine.glfw.events;

import dev.lofiz.jengine.event.Event;

public class MouseButtonCallbackEvent implements Event {

    private final long window;
    private final int button, action, mods;

    public MouseButtonCallbackEvent(long window, int button, int action, int mods) {
        this.window = window;
        this.button = button;
        this.action = action;
        this.mods = mods;
    }

    public long getWindow() {
        return window;
    }

    public int getButton() {
        return button;
    }

    public int getAction() {
        return action;
    }

    public int getMods() {
        return mods;
    }
}
