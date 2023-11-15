package org.example.korobeynikova.di;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class AccessingAllClassesInPackage {
    public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        Set<Class<?>> result = new HashSet<>();
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.endsWith(".class")) {
                    result.add(getClass(line, packageName));
                } else {
                    result.addAll(findAllClassesUsingClassLoader(packageName + "." + line));
                }
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
        return result;
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}