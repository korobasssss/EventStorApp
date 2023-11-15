package org.example.korobeynikova.application.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.database.repository.EventRepository;
import org.example.korobeynikova.application.database.repository.UserRepository;
import org.example.korobeynikova.application.entity.EventEntity;
import org.example.korobeynikova.application.entity.UserEntity;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Component
@NoArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private EventRepository eventRepository;

    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public void addEventToUser(int userId, int eventId) {
        String newUsersEvent = userRepository.getUserById(userId).getEntityList();
        newUsersEvent += " " + eventId;
        userRepository.updateUser(userId, newUsersEvent);
    }

    public boolean addUser(String[] newUser) {
        if (checkUnique(newUser[1])) {
            UserEntity user = new UserEntity(newUser[0], newUser[1], newUser[2]);
            userRepository.setUser(user);
            return true;
        }
        return false;
    }

    public UserEntity getUser(int id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(int id) {
        UserEntity user = userRepository.getUserById(id);
        String[] arr = user.getEntityList().split(" ");
        for (String index : arr) {
            eventRepository.deleteEvent(Integer.getInteger(index));
        }
        userRepository.deleteUserById(id);
    }

    public String showAll() {
        HashMap<Integer, UserEntity> allUsers = userRepository.getAllUsers();
        StringBuilder mapStr = new StringBuilder("{");
        for (Integer key : allUsers.keySet()) {
            mapStr.append(key + ": " + allUsers.get(key).toString() + ",\n");
        }
        if (mapStr.length() > 1) {
            mapStr.delete(mapStr.length() - 3, mapStr.length()).append("}");
            return mapStr.toString();
        } else {
            return "{}";
        }
    }

    private boolean checkUnique(String login) {
        HashMap<Integer, UserEntity> allUsers = userRepository.getAllUsers();
        if (allUsers.isEmpty()) {
            return true;
        } else {
            for (Integer key : allUsers.keySet()) {
                if (Objects.equals(allUsers.get(key).getLogin(), login)) {
                    return false;
                }
            }
        }

        return true;
    }
}
