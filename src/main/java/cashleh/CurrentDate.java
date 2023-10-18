package cashleh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class used in runtest.sh() to dynamically update <code>CURRENT_DATE</code>to current date in EXPECTED.TXT
 */

public class CurrentDate {
    public static void main(String[] args) throws IOException {
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));

        // Define the path to EXPECTED-TXT file
        String fileName = "EXPECTED.TXT";
        Path expectedFilePath = currentDirectory.resolve(fileName);

        // Read the content of the EXPECTED-TXT file
        List<String> lines = Files.readAllLines(expectedFilePath);

        // Define placeholder
        String placeholder = "<<CURRENT_DATE>>";

        // Get the current date in the desired format
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.toString(); // Customize the date format as needed

        // Replace all instances of the placeholder with the current date
        List<String> updatedLines = lines.stream()
                .map(line -> line.replace(placeholder, formattedDate))
                .collect(Collectors.toList());

        // Write the updated content back to the file
        Files.write(expectedFilePath, updatedLines);

        // Print the updated lines or use them as needed
        for (String line : updatedLines) {
            System.out.println(line);
        }
    }
}
