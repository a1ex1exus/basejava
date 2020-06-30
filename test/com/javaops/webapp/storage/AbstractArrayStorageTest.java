package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractArrayStorageTest {
    private final Storage STORAGE;

    public AbstractArrayStorageTest(Storage storage) {
        this.STORAGE = storage;
    }

    private static final String UUID_1 = "uuid1";
    public static final Resume RES1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    public static final Resume RES2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    public static final Resume RES3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    public static final Resume RES4 = new Resume(UUID_4);

    @Before
    public void setUp() throws Exception {
        STORAGE.clear();
        STORAGE.save(RES1);
        STORAGE.save(RES2);
        STORAGE.save(RES3);
    }

    @Test
    public void clear() throws Exception {
        STORAGE.clear();
        assertEquals(0, STORAGE.size());
    }

    @Test
    public void save() throws Exception {
        STORAGE.save(RES4);
        assertEquals(RES4, STORAGE.get(UUID_4));
        assertEquals(4, STORAGE.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        STORAGE.save(RES1);
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
        STORAGE.save(RES4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        STORAGE.update(RES4);
        assertSame(RES4, STORAGE.get(UUID_4));
    }

    @Test
    public void get() throws Exception {
        assertEquals(RES1, STORAGE.get(UUID_1));
        assertEquals(RES2, STORAGE.get(UUID_2));
        assertEquals(RES3, STORAGE.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        STORAGE.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        STORAGE.delete(UUID_3);
        STORAGE.get(UUID_3);
        assertEquals(2, STORAGE.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        STORAGE.delete("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume [] result = STORAGE.getAll();
        assertEquals(3, result.length);
        assertEquals(RES1, result[0]);
        assertEquals(RES2, result[1]);
        assertEquals(RES3, result[2]);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, STORAGE.size());
    }
}