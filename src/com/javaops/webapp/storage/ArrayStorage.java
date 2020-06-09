package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private static final int STORAGE_LIMIT = 10000;

    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index != -1) {
            System.out.println("A resume " + r + " already exists. You can update it.");
        } else {
            if (size < STORAGE_LIMIT) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Not enough space to insert a new resume." +
                        "\n First delete an unnecessary resume and try again.");
            }
        }
    }

    public void update(Resume r) {
        int i = getIndex(r.getUuid());
        if (i != -1) {
            storage[i] = r;
            System.out.println("The resume " + r + " updated.");

        } else {
            System.out.println("The resume " + r + " not found. \n" +
                    "Unable to update a resume that does not exist. \n" +
                    "Please repeat input.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not exist.");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("The resume " + uuid + " has been found and deleted.");
        } else {
            System.out.println("The resume " + uuid + " not found. \n" +
                    "Unable to delete a resume that does not exist. \n" +
                    "Please repeat input.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equalsIgnoreCase(uuid)) {
                return i;
            }
        }
        return -1;
    }
}


