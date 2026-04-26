package restAssured.exercises.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filePath) {

        Properties properties = new Properties();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            properties.load(reader);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + filePath);
        }

        return properties;
    }
}