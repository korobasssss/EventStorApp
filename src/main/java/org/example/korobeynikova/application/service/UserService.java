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

    private int id = 0;

    @Autowired
    public UserService(UserDAO userDAO, EventDAO eventDAO) {
        this.userDAO = userDAO;
        this.eventDAO = eventDAO;
    }

    public void addEventToUser(int eventId) {
        UserEntity us = userDAO.getUserById(id);
        String newUsersEvent = us.getEntityList();
        if (Objects.equals(newUsersEvent, "")) {
            newUsersEvent = String.valueOf(eventId);
        } else {
            newUsersEvent += " " + eventId;
        }
        us.setEntityList(newUsersEvent);
        userDAO.addEventToUser(this.id, eventId);
    }

    public boolean addUser(String[] newUser) {
        if (checkUnique(newUser[1])) {
            UserEntity user = new UserEntity(newUser[0], newUser[1], newUser[2], "");
            userDAO.addUser(user);
            return true;
        }
        return false;
    }

    public UserEntity getUser() {
        return userDAO.getUserById(this.id);
    }

    public void deleteUser() {
        UserEntity user = userDAO.getUserById(id);
        if (user.getEntityList() != null) {
            String[] arr = user.getEntityList().split(" ");
            for (String index : arr) {
                int intIndex = Integer.parseInt(index);
                eventDAO.deleteEvent(intIndex);
            }
        }
        userDAO.deleteUserById(this.id);
    }

    public boolean entryUser(String[] userData) {
        Map<Integer, UserEntity> user = userDAO.getAllUsers();
        int id = 1;
        for (UserEntity userEntity : user.values()) {
            if (Objects.equals(userData[0], userEntity.getLogin()) &&
                    Objects.equals(userData[1], userEntity.getPassword())) {
                    this.id = id;
                    return true;
            } else {
                id++;
            }
        }
        return false;
    }

    public Map<Integer, UserEntity> showAll() {
        Map<Integer, UserEntity> allUsers = userDAO.getAllUsers();
        return allUsers;
//        StringBuilder mapStr = new StringBuilder("{");
//        for (Integer key : allUsers.keySet()) {
//            mapStr.append(key + ": " + allUsers.get(key).toString() + ",\n");
//        }
//        if (mapStr.length() > 1) {
//            mapStr.delete(mapStr.length() - 3, mapStr.length()).append("}");
//            return mapStr.toString();
//        } else {
//            return "{}";
//        }
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
