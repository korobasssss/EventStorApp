package org.example.korobeynikova.application.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.korobeynikova.application.service.EventService;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Scanner;

@Getter
@Setter
@Component
@NoArgsConstructor
public class EventController {

    private EventService eventService;
    private UserController userController;
    private Scanner scanner;

    @Autowired
    public EventController(EventService eventService, UserController userController, Scanner scanner) {
        this.eventService = eventService;
        this.userController = userController;
        this.scanner = scanner;
    }

    public void addEvent() {
        String[] newEvent = new String[4];

        System.out.println("Введите тип события");
        System.out.println("<MEET / BIRTHDAY>");
        newEvent[0] = scanner.nextLine();
        System.out.println("Введите название события");
        System.out.println("<название>");
        newEvent[1] = scanner.nextLine();
        System.out.println("Введите время события");
        System.out.println("<гггг-мм-дд>");
        newEvent[2] = scanner.nextLine();
        System.out.println("Введите описание события");
        System.out.println("<описание>");
        newEvent[3] = scanner.nextLine();

        eventService.addEvent(newEvent);
        userController.addEventToUser(eventService.getLastEventsId());
    }

    public void updateEventData() {
        String[] newEvent = new String[4];

        System.out.println("Введите новые данные события");

        System.out.println("Введите тип события");
        System.out.println("<MEET / BIRTHDAY>");
        newEvent[0] = scanner.nextLine();
        System.out.println("Введите название события");
        System.out.println("<название>");
        newEvent[1] = scanner.nextLine();
        System.out.println("Введите время события");
        System.out.println("<гггг-мм-дд>");
        newEvent[2] = scanner.nextLine();
        System.out.println("Введите описание события");
        System.out.println("<описание>");
        newEvent[3] = scanner.nextLine();

        System.out.println("Введите id события, которое надо отредактировать");
        int id = scanner.nextInt();

        eventService.updateEventData(newEvent, id);
    }

    public void showAllEvents() {
        System.out.println(eventService.showAllEvents());
    }
}
