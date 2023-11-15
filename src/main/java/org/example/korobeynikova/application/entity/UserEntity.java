package org.example.korobeynikova.application.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.di.annotation.Entity;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@NoArgsConstructor
public class UserEntity {
    @Getter
    private String name;
    @Getter
    private String login;
    @Getter
    private String password;
    @Getter
    private String entityList; // хранятся айдишники на событие

    public UserEntity(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public UserEntity(String name, String login, String password, String entityList) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.entityList = entityList;
    }

    @Override
    public String toString() {
        return "UserEntity {" + name + ", " +
                                login + ", " +
                                password + ", " +
                                entityList;
    }

    public void addEventId(List<Integer> id) {
        entityList += " id";
    }
}
