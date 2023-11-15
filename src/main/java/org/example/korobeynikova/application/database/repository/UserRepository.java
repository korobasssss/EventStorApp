package org.example.korobeynikova.application.database.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.database.db.UserDataBase;
import org.example.korobeynikova.application.entity.EventEntity;
import org.example.korobeynikova.application.entity.UserEntity;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Component
@NoArgsConstructor
public class UserRepository {

    private UserDataBase userDataBase;

    @Autowired
    public UserRepository(UserDataBase userDB) {
        this.userDataBase = userDB;
    }

    public void setUser(UserEntity userEntity) {
        userDataBase.setUser(userEntity);
    }

    public UserEntity getUserById(Integer id) {
        return userDataBase.getUserById(id);
    }

    public void updateUser(Integer id, String newEvents) {
        userDataBase.updateUser(id, newEvents);
    }
    public void deleteUserById(Integer id) {
        userDataBase.deleteUserById(id);
    }

    public void changeUserById(Integer id, UserEntity newUser) {
        userDataBase.changeUserById(id, newUser);
    }

    public HashMap<Integer, UserEntity> getAllUsers() {
        return userDataBase.getDataBase();
    }
}
