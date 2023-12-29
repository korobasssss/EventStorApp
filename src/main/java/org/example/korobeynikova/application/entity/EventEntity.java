package org.example.korobeynikova.application.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.entity.enums.EventType;
import org.example.korobeynikova.di.annotation.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NoArgsConstructor
public class EventEntity {


    @Getter
    @Setter
    private EventType type;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String data;

    public EventEntity(EventType type, String title, String data) {
        this.type = type;
        this.title = title;
        this.data = data;
    }
    @Override
    public String toString() {
        return "Event { " + type + ", " +
                title + ", " +
                data + "}";
    }

}
