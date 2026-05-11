package dev.lofiz.jengine.event;

@FunctionalInterface
public interface EventListener<T extends Event> {

    void handle(T event);
}
