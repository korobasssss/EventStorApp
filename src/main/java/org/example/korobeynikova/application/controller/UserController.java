package org.example.korobeynikova.application.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.service.EventService;
import org.example.korobeynikova.application.service.UserService;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

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
        System.out.println("Введите ID юзера");
        System.out.println("<ID юзера>");
        userService.addEventToUser(scanner.nextInt(), eventId);
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
        System.out.println("Введите id юзера");
        System.out.println("<id>");
        int id = scanner.nextInt();
        userService.deleteUser(id);
    }

    public void showAllUserEvents(){
        System.out.println("Введите id юзера");
        System.out.println("<id>");
        int id = scanner.nextInt();
        String index = userService.getUser(id).getEntityList();
        System.out.println(eventService.showAllUserEvents(index));
    }
    public void showAll() {
        System.out.println(userService.showAll());
    }
}
