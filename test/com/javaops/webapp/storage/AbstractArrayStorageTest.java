package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {
    private final Storage STORAGE;

    private static final String UUID_1 = "uuid1";
    private static final Resume RES_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RES_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RES_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume RES_4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.STORAGE = storage;
    }

    @Before
    public void setUp() throws Exception {
        STORAGE.clear();
        STORAGE.save(RES_1);
        STORAGE.save(RES_2);
        STORAGE.save(RES_3);
    }

    @Test
    public void clear() throws Exception {
        STORAGE.clear();
        verifySize(0);
    }

    @Test
    public void save() throws Exception {
        STORAGE.save(RES_4);
        verifySize(4);
        verifyGet(RES_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        STORAGE.save(RES_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                STORAGE.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        STORAGE.save(RES_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        STORAGE.update(RES_4);
    }

    @Test
    public void get() throws Exception {
        verifyGet(RES_1);
        verifyGet(RES_2);
        verifyGet(RES_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        STORAGE.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        STORAGE.delete(UUID_3);
        verifySize(2);
        STORAGE.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        STORAGE.delete("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] result = STORAGE.getAll();
        assertEquals(3, result.length);
        assertEquals(RES_1, result[0]);
        assertEquals(RES_2, result[1]);
        assertEquals(RES_3, result[2]);
    }

    @Test
    public void size() throws Exception {
        verifySize(3);
    }

    private void verifySize(int size) {
        assertEquals(size, STORAGE.size());
    }

    private void verifyGet(Resume resume) {
        assertEquals(resume, STORAGE.get(resume.getUuid()));

    }
}