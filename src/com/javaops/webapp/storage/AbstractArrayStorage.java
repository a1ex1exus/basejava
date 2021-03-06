package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("The array has been cleared");
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Error. The resume with id " + uuid + " already exists.");
        } else {
            if (size < STORAGE_LIMIT) {
                insertResume(r, index);
                size++;
            } else {
                System.out.println("Error. Not enough space to insert a new resume." +
                        "\n Maximum number of items is " + STORAGE_LIMIT + "Storage full." +
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
            System.out.println("Error. The resume with id " + r.getUuid() + " not found. \n" +
                    "Please repeat input.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Error. The resume with id " + uuid + " not exist.");
        return null;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            movingElements(index);
            storage[size - 1] = null;
            size--;
            System.out.println("The resume with id " + uuid + " has been found and deleted.");
        } else {
            System.out.println("Error. The resume with id" + uuid + " not found. \n" +
                    "Please repeat input.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void movingElements(int index);

    protected abstract int getIndex(String uuid);
}

