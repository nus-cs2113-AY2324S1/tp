package fittrack.command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddWorkoutCommandTest {

    @Test
    public void testHelp(){
        AddWorkoutCommand addWorkoutCommand = new AddWorkoutCommand();
        String expected = "'addworkout' adds a workout to the list." + "\n"
                + "Type 'addworkout' <workout> /cals <calories> to add the workout to your list.";
        assertEquals(expected, addWorkoutCommand.getHelp());
    }


}
