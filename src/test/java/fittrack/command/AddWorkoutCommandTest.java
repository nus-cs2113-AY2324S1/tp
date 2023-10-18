package fittrack.command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddWorkoutCommandTest {
    public static final String COMMAND_WORD = "addworkout";
    private static final String DESCRIPTION =
            String.format("'%s' adds a workout to the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type '%s' <workout> /cals <calories> to add the workout to your list.", COMMAND_WORD);
    private static final String HELP = DESCRIPTION + "\n" + USAGE;

    @Test
    public void testHelp(){
        AddWorkoutCommand addWorkoutCommand = new AddWorkoutCommand();
        assertEquals(HELP, addWorkoutCommand.getHelp());
    }


}
