package cashleh;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CurrentDateUpdater {
    private Path filePath;
    private String placeholder;

    public CurrentDateUpdater(String fileName, String placeholder) {
        this.filePath = Paths.get(System.getProperty("user.dir")).resolve(fileName);
        this.placeholder = placeholder;
    }

    public void updateCurrentDate() throws IOException {
        // Read the content of the file while handling line endings
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        // Get the current date in the desired format
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.toString(); // Customize the date format as needed

        // Replace all instances of the placeholder with the current date
        List<String> updatedLines = lines.stream()
                .map(line -> line.replace(placeholder, formattedDate))
                .collect(Collectors.toList());

        // Write the updated content back to the file while preserving line endings
        Files.write(filePath, updatedLines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}

