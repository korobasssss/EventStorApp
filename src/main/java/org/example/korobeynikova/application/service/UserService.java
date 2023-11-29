package org.example.korobeynikova.application.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.database.db.EventDAO;
import org.example.korobeynikova.application.database.db.UserDAO;
import org.example.korobeynikova.application.entity.UserEntity;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Component
@NoArgsConstructor
public class UserService {

    private UserDAO userDAO;
    private EventDAO eventDAO;

    @Autowired
    public UserService(UserDAO userDAO, EventDAO eventDAO) {
        this.userDAO = userDAO;
        this.eventDAO = eventDAO;
    }

    public void addEventToUser(int userId, int eventId) {
        UserEntity us = userDAO.getUserById(userId);
        String newUsersEvent = us.getEntityList();
        newUsersEvent += " " + eventId;
        us.setEntityList(newUsersEvent);
        userDAO.updateUser(userId, us);
    }

    public boolean addUser(String[] newUser) {
        if (checkUnique(newUser[1])) {
            UserEntity user = new UserEntity(newUser[0], newUser[1], newUser[2]);
            userDAO.setUser(user);
            return true;
        }
        return false;
    }

    public UserEntity getUser(int id) {
        return userDAO.getUserById(id);
    }

    public void deleteUser(int id) {
        UserEntity user = userDAO.getUserById(id);
        String[] arr = user.getEntityList().split(" ");
        for (String index : arr) {
            eventDAO.deleteEvent(Integer.getInteger(index));
        }
        userDAO.deleteUserById(id);
    }

    public String showAll() {
        Map<Integer, UserEntity> allUsers = userDAO.getAllUsers();
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
        Map<Integer, UserEntity> allUsers = userDAO.getAllUsers();
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
