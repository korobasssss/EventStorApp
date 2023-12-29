package org.example.korobeynikova.application.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.service.EventService;
import org.example.korobeynikova.application.service.UserService;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.sql.Array;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@Component
@NoArgsConstructor
public class UserController {
    private EventService eventService;
    private UserService userService;
    private Scanner scanner;

    @Autowired
    public UserController(EventService eventService, UserService userService, Scanner scanner) {
        this.eventService = eventService;
        this.userService = userService;
        this.scanner = scanner;
    }
    public void addEventToUser(int eventId) {
        userService.addEventToUser(eventId + 1);
    }
    public void addUser() {
        System.out.println("Введите данные юзера");
        System.out.println("<имя> <логин> <пароль>");
        String[] newCustomer = scanner.nextLine().split(" ");
        if (!userService.addUser(newCustomer)) {
            System.out.println("Пользователь с таким логином уже существует");
        }
    }
    public void deleteUser() {
        userService.deleteUser();
    }

    public boolean entryUser() {
        String[] userData = new String[2];
        System.out.println("Введите логин и пароль");
        System.out.println("<login>");
        userData[0] = scanner.nextLine();
        System.out.println("<password>");
        userData[1] = scanner.nextLine();
        System.out.println(userService.entryUser(userData));
        return userService.entryUser(userData);
    }

    public void showAllUserEvents(){
        String index = userService.getUser().getEntityList();


        System.out.println(eventService.showAllUserEvents(index));
    }
    public void showAll() {
        System.out.println(userService.showAll());
    }
}
