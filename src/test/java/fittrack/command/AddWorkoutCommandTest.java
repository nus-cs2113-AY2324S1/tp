package fittrack.command;
import fittrack.parser.CommandParser;
import fittrack.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddWorkoutCommandTest {
    public static final String COMMAND_WORD = "addworkout";
    private static final String DESCRIPTION =
            String.format("'%s' adds a workout to the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type '%s' <workout> /cals <calories> to add the workout to your list.", COMMAND_WORD);
    private static final String HELP = DESCRIPTION + "\n" + USAGE;

    private AddWorkoutCommand addWorkoutCommand;
    private CommandParser parser;
    final String args = "Workout /cals 100";
    final double actual = 100;

    @BeforeEach
    public void setup(){
        addWorkoutCommand = new AddWorkoutCommand();

    }

    @Test
    public void setArgumentsTest(){
        addWorkoutCommand.setArguments(args, parser);
        double testCals = addWorkoutCommand.getWorkout().getCalories();

        assertEquals(testCals, actual);
    }

    @Test
    public void testHelp(){
        assertEquals(HELP, addWorkoutCommand.getHelp());
    }


}
