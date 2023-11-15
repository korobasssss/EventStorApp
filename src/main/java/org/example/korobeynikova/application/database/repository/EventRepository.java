package org.example.korobeynikova.application.database.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.database.db.EventDataBase;
import org.example.korobeynikova.application.entity.EventEntity;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.util.HashMap;

@Getter
@Setter
@Component
@NoArgsConstructor
public class EventRepository {

    private EventDataBase eventDataBase;

    @Autowired
    public EventRepository(EventDataBase eventDB) {
        this.eventDataBase = eventDB;
    }
    public void setEvent(EventEntity event) {
        eventDataBase.setEvent(event);
    }

    public EventEntity getEventById(Integer id) {
        return eventDataBase.getEventById(id);
    }

    public void changeEventById (Integer id, EventEntity event) {
        eventDataBase.changeEventById(id, event);
    }

    public HashMap<Integer, EventEntity> getAllEvents() {
        return eventDataBase.getDataBase();
    }

    public int getSize() {
        return eventDataBase.size();
    }

    public void deleteEvent (Integer id) {
        eventDataBase.deleteEvent(id);
    }

}
