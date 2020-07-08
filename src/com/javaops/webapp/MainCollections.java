package com.javaops.webapp;

import com.javaops.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume RES_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RES_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RES_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RES_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RES_1);
        collection.add(RES_2);
        collection.add(RES_3);

        for (Resume resume : collection) {
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
//                collection.remove(resume);
            }
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }

        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RES_1);
        map.put(UUID_2, RES_2);
        map.put(UUID_3, RES_3);

        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}