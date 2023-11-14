package seedu.duke.storagefile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Storage {

    protected File dir;
    protected File textFile;
    protected FileWriter writeFile;

    public Storage(String dirName, String textFileName) {
        dir = new File(dirName);
        textFile = new File(textFileName);
    }

}
