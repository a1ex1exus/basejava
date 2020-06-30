package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.exception.StorageException;
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
            throw new ExistStorageException(r.getUuid());
        } else {
            if (size < STORAGE_LIMIT) {
                insertResume(r, index);
                size++;
            } else {
                throw new StorageException("Storage overflow.", r.getUuid());
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
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
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
            throw new NotExistStorageException(uuid);
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

