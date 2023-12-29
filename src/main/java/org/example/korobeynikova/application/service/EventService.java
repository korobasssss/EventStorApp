package org.example.korobeynikova.application.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.database.db.EventDAO;
import org.example.korobeynikova.application.entity.EventEntity;
import org.example.korobeynikova.application.entity.enums.EventType;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.util.Map;

@Getter
@Setter
@Component
@NoArgsConstructor
public class EventService {

    private EventDAO eventDAO;

    @Autowired
    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public void addEvent(String[] newEvent) {
        EventEntity event = makeEvent(newEvent);
        eventDAO.addEvent(event);
    }

    public int getLastEventsId() {
        return eventDAO.size() - 1;
    }

    public void updateEventData(String[] newEvent, int id) {
        EventEntity event = makeEvent(newEvent);
        eventDAO.changeEventById(id, event);
    }

    public String showAllEvents() {
        Map<Integer, EventEntity> allEvents = eventDAO.getAllEvents();
        StringBuilder mapStr = new StringBuilder("{");
        for (Integer key : allEvents.keySet()) {
            mapStr.append(key + ": " + allEvents.get(key).toString() + ",\n");
        }
        if (mapStr.length() > 1) {
            mapStr.delete(mapStr.length() - 3, mapStr.length()).append("}");
            return mapStr.toString();
        } else {
            return "{}";
        }
    }

    public String showAllUserEvents(String index) {
        StringBuilder mapUserEventsStr = new StringBuilder("{");
        String[] arr = index.split(" ");
        for (String i : arr) {
            mapUserEventsStr.append(i + ": " + eventDAO.getEventById(Integer.valueOf(i)).toString() + ", \n");
        }
        if (mapUserEventsStr.length() > 1) {
            mapUserEventsStr.delete(mapUserEventsStr.length() - 3, mapUserEventsStr.length()).append("}");
            return mapUserEventsStr.toString();
        } else {
            return "{}";
        }
    }

    private EventEntity makeEvent (String[] newEvent) {
        return  new EventEntity(EventType.valueOf(newEvent[0]),
                newEvent[1],
                newEvent[2]);
    }
}