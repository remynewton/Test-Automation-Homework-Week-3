import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDatabase {
    private static final Logger logger = LogManager.getLogger(FileDatabase.class);

    private String filePath;

    public FileDatabase(String filePath) {
        this.filePath = filePath;
    }

    public void writeData(List<String> data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String s : data) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            logger.error("Error writing data to file: " + e.getMessage());
        }
    }

    public List<String> readData() {
        List<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error reading data from file: " + e.getMessage());
        }
        return data;
    }

    public void appendData(String s) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(s + "\n");
        } catch (IOException e) {
            logger.error("Error appending data to file: " + e.getMessage());
        }
    }
}