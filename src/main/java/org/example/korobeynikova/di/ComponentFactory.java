package org.example.korobeynikova.di;

import org.example.korobeynikova.di.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class ComponentFactory {

    private final String strPackage = "org.example.korobeynikova.application";
    private Map<String, Object> components;
    private Set<Class<?>> classes;


    public ComponentFactory() {
        this.components = new HashMap<>();
        this.classes = AccessingAllClassesInPackage.findAllClassesUsingClassLoader(strPackage);
        this.components.put("Scanner", new Scanner(System.in));
    }

    public void makeAllComponents(){
        for (Class<?> custClass: classes) {
            if (Stream.of(Component.class).anyMatch(custClass::isAnnotationPresent)) {
                String id = custClass.getName();
                if(!components.containsKey(id)) {
                    components.put(id, makeComponent(custClass));
                }
            }
        }
    }

    public Object makeComponent(Class<?> custClass) {
        Object exemplar = null;
        try {
            if (Stream.of(custClass.getConstructors()).anyMatch(constr -> constr.isAnnotationPresent(Autowired.class))) {
                exemplar = custClass.getConstructor().newInstance();
                Field[] fields = custClass.getDeclaredFields();
                for (Field field: fields) {
                    field.setAccessible(true);
                    if (components.containsKey(field.getType().getName())) {
                        field.set(exemplar, components.get(field.getType().getName()));
                    } else if (Stream.of(Component.class, DAO.class).anyMatch(field.getType()::isAnnotationPresent)) {
                        Object objectForField = makeComponent(field.getType());
                        components.put(field.getType().getName(), objectForField);
                        field.set(exemplar, objectForField);
                    } else if (field.isAnnotationPresent(ValueInt.class)){
                        field.set(exemplar, field.getAnnotation(ValueInt.class).value());
                    } else if (field.getName().equals("scanner")){
                        field.set(exemplar, components.get("Scanner"));
                    }
                }
            } else {
                exemplar = custClass.getConstructor().newInstance();
                Field[] fields = custClass.getDeclaredFields();
                for (Field field: fields) {
                    field.setAccessible(true);
                    if (field.getName().equals("dataBase")) {
                        field.set(exemplar, new HashMap<String, Object>());
                    } else if (field.isAnnotationPresent(ValueInt.class)) {
                        field.set(exemplar, field.getAnnotation(ValueInt.class).value());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return exemplar;
    }

    public Object getComponent(String name) {
        return components.get(name);
    }

}

