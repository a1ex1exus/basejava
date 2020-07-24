package com.javaops.webapp.storage;

import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("The array has been cleared");
    }

    @Override
    protected void doSave(Resume resume, Object index) {
            if (size < STORAGE_LIMIT) {
                insertResume(resume, (Integer) index);
                size++;
            } else {
                throw new StorageException("Storage overflow.", resume.getUuid());
            }
        }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    public void doDelete(Object index) {
        movingElements((Integer) index);
        storage[size - 1] = null;
        size--;
    }
    public Resume doGet(Object index) {
            return storage[(Integer)index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void movingElements(int index);

    protected abstract Integer getSearchKey(String uuid);
}