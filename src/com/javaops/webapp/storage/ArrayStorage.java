package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;
import java.util.Scanner;

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

    public void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if ((storage[i]).getUuid().equalsIgnoreCase(r.getUuid())) {
                System.out.println("The resume " + r + " already exists. You can update it.");
                return;
            }
        }
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Not enough space to insert a new resume." +
                    "\n First delete an unnecessary resume and try again.");
        }
    }

    public void update(Resume r) {
        Scanner sc = new Scanner(System.in);
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("The resume " + r + " will be updated. Enter the desired change: ");
                Resume r1 = new Resume();
                r1.setUuid(sc.nextLine().toLowerCase());
                System.out.println("The resume " + r + "  has been updated to " + r1 + ".");
                storage[i] = r1;
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("The resume " + r + " not found. \n" +
                    "Unable to update a resume that does not exist");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("No resume found.");
        return null;
    }

    public void delete(String uuid) {
        int i, index = -1;
        for (i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
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
