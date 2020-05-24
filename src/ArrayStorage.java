import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int nElems = 0;

    void clear() {
        Arrays.fill(Arrays.copyOf(storage, nElems), null);
        nElems = 0;
        System.out.println("The array has been cleared");
    }

    void save(Resume r) {
        if (nElems <= storage.length - 1) {
            storage[nElems] = r;
            nElems++;
        } else {
            System.out.println("Not enough space to insert a new resume." +
                    "\n First delete an unnecessary resume and try again.");
        }
    }

    Resume get(String uuid) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            }
        }
        if (i == nElems) {
            System.out.println("Resume not found");
            return null;
        } else {
            return storage[i];
        }
    }

    void delete(String uuid) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            }
        }
        if (i == nElems) {
            System.out.println("The resume " + uuid + " not found. \n" +
                    "Unable to delete a resume that does not exist");
        } else {
            for (int k = i; k < nElems-1; k++) {
                storage[k] = storage[k + 1];
            }
            storage[nElems-1] = null;
            nElems--;
            System.out.println("The resume " + uuid + "  has been found and deleted.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, nElems);
    }

    int size() {
        return nElems;
    }
}