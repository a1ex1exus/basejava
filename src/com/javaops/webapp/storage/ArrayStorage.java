package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("The array has been cleared");
    }

    private int findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equalsIgnoreCase(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int existing = findResume(uuid);
        if (existing != -1) {
            System.out.println("A resume with that name already exists. You can update it.");
        } else {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Not enough space to insert a new resume." +
                        "\n First delete an unnecessary resume and try again.");
            }
        }
    }

    public void update(Resume r) {
        int i = findResume(r.getUuid());
        storage[i] = r;
        System.out.println("The resume " + r + " updated.");
    }

    public Resume get(String uuid) {
        int i = findResume(uuid);
        if (i != -1) {
            return storage[i];
        } else {
            System.out.println("No resume found.");
            return null;
        }
    }

    public void delete(String uuid) {
        int i = findResume(uuid);
        if (i == -1) {
            System.out.println("The resume " + uuid + " not found. \n" +
                    "Unable to delete a resume that does not exist. \n" +
                    "Please repeat input.");
        } else {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("The resume " + uuid + " has been found and deleted.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
