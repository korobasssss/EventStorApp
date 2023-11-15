package org.example.korobeynikova.application.database.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.entity.UserEntity;
import org.example.korobeynikova.di.annotation.Component;
import org.example.korobeynikova.di.annotation.ValueInt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Component
@NoArgsConstructor
public class UserDataBase {

    private HashMap<Integer, UserEntity> dataBase;
    @ValueInt(0)
    private Integer id;

    public void setUser(UserEntity userEntity) {
        dataBase.put(id, userEntity);
        id++;
    }

    public UserEntity getUserById(Integer id) {
        return dataBase.get(id);
    }

    public void updateUser(Integer id, String newEvents) {
        dataBase.get(id).setEntityList(newEvents);
        System.out.println(dataBase);
    }
    public void deleteUserById(Integer id) {
        dataBase.remove(id);
    }

    public void changeUserById(Integer id, UserEntity newUser) {
        dataBase.remove(id);
        dataBase.put(id, newUser);
    }
}
