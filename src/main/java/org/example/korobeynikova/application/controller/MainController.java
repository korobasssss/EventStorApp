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
            System.out.println("/event_make/ - создать событие");
            System.out.println("/event_edit/ - редактировать событие");
            System.out.println("/events_show/ - вывести список всех событий");
            System.out.println("/users_events_show/ - вывести список всех событий пользователя");
            System.out.println("/users_show/ - вывести список всех пользователей");
            System.out.println("/user_add/ - добавить пользователя");
            System.out.println("/user_delete/ - удалить пользователя");
            System.out.println("----------------------------------------------------------------");

            switch (scanner.nextLine()) {
                case "/event_make/":
                    eventController.addEvent();
                    break;
                case "/event_edit/":
                    eventController.updateEventData();
                    break;
                case "/events_show/":
                    eventController.showAllEvents();
                    break;
                case "/users_events_show/":
                    userController.showAllUserEvents();
                    break;
                case "/users_show/":
                    userController.showAll();
                    break;
                case "/user_add/":
                    userController.addUser();
                    break;
                case "/user_delete/":
                    userController.deleteUser();
                    break;
                default: {
                    System.out.println("Запрос введен неправильно");
                }
            }
        }
    }
}
