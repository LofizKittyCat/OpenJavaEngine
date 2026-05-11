package dev.lofiz.jengine.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private static final Map<Class<?>, List<EventListener<?>>> listeners = new HashMap<>();

    public static <T extends Event> void subscribe(Class<T> type, EventListener<T> listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Event> void publish(T event) {
        List<EventListener<?>> list = listeners.get(event.getClass());
        if(list == null) return;

        for(EventListener<?> listener : list) {
            ((EventListener<T>) listener).handle(event);
        }
    }
}
