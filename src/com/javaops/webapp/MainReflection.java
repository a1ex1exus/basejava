package com.javaops.webapp;

import com.javaops.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");

        Class<? extends Resume> resumeClass = resume.getClass();
        Method method = resumeClass.getMethod("toString");
        Object methodToString = method.invoke(resume);
        System.out.println(methodToString);
    }
}