package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void insertResume(Resume r, int index) {
        index = abs(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    protected void movingElements(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
