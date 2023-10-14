package command;

import data.Resource;
import seedu.duke.Parser;

public class AddCommand extends Command{
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        String[] temp = statement.split(" ");
        parser.taskList.add(new Resource(temp[0], temp[1]));
        System.out.println("This task is added: ");
        System.out.println(parser.taskList.get(parser.taskList.size()-1).getTitle());
    }
}
