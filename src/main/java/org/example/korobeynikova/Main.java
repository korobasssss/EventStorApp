package org.example.korobeynikova;

import org.example.korobeynikova.application.controller.MainController;
import org.example.korobeynikova.application.service.EventService;
import org.example.korobeynikova.application.service.UserService;
import org.example.korobeynikova.di.ComponentFactory;


public class Main {
    public static void main(String[] args) {

        ComponentFactory componentFactory = new ComponentFactory();
        componentFactory.makeAllComponents();

        MainController mainController = (MainController) (componentFactory.getComponent("ru.vsu.cs.korobeynikova.application.controller.MainController"));
        mainController.run();
    }

    public void makePersons() {

    }

}