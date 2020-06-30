package com.javaops.webapp;

import com.javaops.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        Class<? extends Resume> resumeClass = r.getClass();
        Method method = resumeClass.getMethod("toString");
        Object methodToString = method.invoke(r);
        System.out.println(methodToString);
    }
}
