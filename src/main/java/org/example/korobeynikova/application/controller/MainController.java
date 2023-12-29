package org.example.korobeynikova.application.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.util.Scanner;

@Getter
@Setter
@Component
@NoArgsConstructor
public class MainController {
    private EventController eventController;
    private UserController userController;
    private Scanner scanner;


    @Autowired
    public MainController(EventController eventController, UserController userController, Scanner scanner) {
        this.eventController = eventController;
        this.userController = userController;
        this.scanner = scanner;
    }

    public void run(){
        while(true){
            System.out.println("----------------------------------------------------------------");
            System.out.println("Программа-органайзер для событий! ");
            System.out.println("Выберите действие: ");
            System.out.println("----------------------------------------------------------------");
            System.out.println("/event/make/ - создать событие");
            System.out.println("/event/edit/ - редактировать событие");
            System.out.println("/events/show/ - вывести список всех событий");
            System.out.println("/users/events/show/ - вывести список всех событий пользователя");
            System.out.println("/users/show/ - вывести список всех пользователей");
            System.out.println("/user/add/ - добавить пользователя");
            System.out.println("/user/delete/ - удалить пользователя");
            System.out.println("/user/entry/ - войти в аккаунт");
            System.out.println("----------------------------------------------------------------");

            switch (scanner.nextLine()) {
                case "/event/make/":
                    eventController.addEvent();
                    break;
                case "/event/edit/":
                    eventController.updateEventData();
                    break;
                case "/events/show/":
                    eventController.showAllEvents();
                    break;
                case "/users/events/show/":
                    userController.showAllUserEvents();
                    break;
                case "/users/show/":
                    userController.showAll();
                    break;
                case "/user/add/":
                    userController.addUser();
                    break;
                case "/user/delete/":
                    userController.deleteUser();
                    break;
                case "/user/entry/":
                    userController.entryUser();
                    break;
                default: {
                    System.out.println("Запрос введен неправильно");
                }
            }
        }
    }
}
