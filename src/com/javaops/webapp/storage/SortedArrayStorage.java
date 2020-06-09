package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("A resume " + r + " already exists. You can update it.");
        } else {
            if (size + 1 < STORAGE_LIMIT) {
                index = abs(index + 1);
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = r;
                size++;
            } else {
                System.out.println("Not enough space to insert a new resume." +
                        "\n First delete an unnecessary resume and try again.");
            }
        }
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("The resume " + r + " updated.");
        } else {
            System.out.println("The resume " + r + " not found. \n" +
                    "Unable to update a resume that does not exist. \n" +
                    "Please repeat input.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not exist.");
        return null;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
            size--;
            System.out.println("The resume " + uuid + " has been found and deleted.");
        } else {
            System.out.println("The resume " + uuid + " not found. \n" +
                    "Unable to delete a resume that does not exist. \n" +
                    "Please repeat input.");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
