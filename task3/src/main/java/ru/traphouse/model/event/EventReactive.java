package ru.traphouse.model.event;

public interface EventReactive {
    /**
     * Изменяет состояние сущности, если ивент относится к ней,
     * иначе ничего не меняется
     */
    void applyEvent(InteractionEvent event);
}
