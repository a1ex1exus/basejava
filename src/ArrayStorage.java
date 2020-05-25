import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("The array has been cleared");
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Not enough space to insert a new resume." +
                    "\n First delete an unnecessary resume and try again.");
        }
    }

    Resume get(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        if (i == size) {
            System.out.println("Resume not found");
            return null;
        } else {
            return storage[i];
        }
    }

    void delete(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            }
        }
        if (i == size) {
            System.out.println("The resume " + uuid + " not found. \n" +
                    "Unable to delete a resume that does not exist");
        } else {
            for (int k = i; k < size - 1; k++) {
                storage[k] = storage[k + 1];
            }
            storage[size - 1] = null;
            size--;
            System.out.println("The resume " + uuid + "  has been found and deleted.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}