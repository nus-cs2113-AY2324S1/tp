package seedu.stocker.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    @Test
    public void getIndexedListForViewing_success() {
        String expectedOutput = "\t1. Paracetamol" + System.lineSeparator()
            +  "\t2. Ibuprofene" + System.lineSeparator()
            +  "\t3. Doliprane" + System.lineSeparator();

        List<String> input = Arrays.asList("Paracetamol", "Ibuprofene", "Doliprane");
        assertEquals(expectedOutput, new Ui().getIndexedListForViewing(input));
    }
}
