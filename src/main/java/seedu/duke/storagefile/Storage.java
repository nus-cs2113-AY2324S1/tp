package seedu.duke.storagefile;

import java.io.IOException;

abstract class Storage {
    public abstract void writeToStorage(int firstInt, int secondInt, String[] stringArray, int thirdInt)
            throws IOException;

    public abstract void deleteFromStorage(int firstInt, int secondInt, String[] stringArray, int thirdInt)
            throws IOException;

    public abstract void updateInStorage(int firstInt, int secondInt, String[] firstStringArray, int thirdInt,
            String[] secondStringArray, int fourthInt) throws IOException;
}
