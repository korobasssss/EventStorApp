package org.example.korobeynikova.application.database.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.entity.EventEntity;
import org.example.korobeynikova.di.annotation.Component;
import org.example.korobeynikova.di.annotation.ValueInt;

import java.util.HashMap;

import static org.example.korobeynikova.application.entity.enums.EventType.MEET;

@Getter
@Setter
@Component
@NoArgsConstructor
public class EventDataBase {
    private HashMap<Integer, EventEntity> dataBase;
    @ValueInt(0)
    private Integer id;

    public void setEvent(EventEntity event) {
        dataBase.put(id, event);
        id++;
    }

    public EventEntity getEventById (Integer id) {
        return dataBase.get(id);
    }

    public void changeEventById (Integer id, EventEntity event) {
        dataBase.remove(id);
        dataBase.put(id, event);
    }

    public int size() {
        return dataBase.size();
    }

    public void deleteEvent (Integer id) {
        dataBase.remove(id);
    }
}
