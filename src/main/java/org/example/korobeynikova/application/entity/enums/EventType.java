package org.example.korobeynikova.application.entity.enums;

public enum EventType {
    MEET ("MEET"),
    BIRTHDAY ("BIRTHDAY");

    private final String type;

    EventType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
