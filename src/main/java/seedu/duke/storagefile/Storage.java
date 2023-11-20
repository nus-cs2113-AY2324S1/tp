package seedu.duke.storagefile;

import java.io.File;
import java.io.FileWriter;

class Storage {

    protected File dir;
    protected File textFile;
    protected FileWriter writeFile;

    public Storage(String dirName, String textFileName) {
        dir = new File(dirName);
        textFile = new File(textFileName);
    }

}
