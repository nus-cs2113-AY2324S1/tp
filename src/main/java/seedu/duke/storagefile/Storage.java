package seedu.duke.storagefile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract class Storage {

    protected File dir;
    protected File textFile;
    protected FileWriter writeFile;

    public Storage(String dirName, String textFileName) {
        dir = new File(dirName);
        textFile = new File(textFileName);
    }
    public abstract void writeToStorage(int firstInt, int secondInt, String[] stringArray, int thirdInt)
            throws IOException;

    public abstract void deleteFromStorage(int firstInt, int secondInt, String[] stringArray, int thirdInt)
            throws IOException;

    public abstract void updateInStorage(int firstInt, int secondInt, String[] firstStringArray, int thirdInt,
            String[] secondStringArray, int fourthInt) throws IOException;
}
