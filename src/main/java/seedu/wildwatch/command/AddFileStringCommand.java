//@@lctxct
package seedu.wildwatch.command;

import java.util.regex.Pattern;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;

public class AddFileStringCommand extends Command {
    public static final Pattern FILE_STRING_FORMAT =
            Pattern.compile("\\s*(?<date>[^/]+)\\s*/" +
                    "\\s*(?<species>[^/]+)\\s*/" +
                    "\\s*(?<name>[^/]+)\\s*/" +
                    "\\s*(?<remark>[^/]+)");

    private final Entry newEntry;

    public AddFileStringCommand(Entry entry) {
        newEntry = entry;
    }
    /**
     * Add new a Entry
     */
    @Override
    public void execute() {
        EntryList.addEntry(newEntry);
    }
}
