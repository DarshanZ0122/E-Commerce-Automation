package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    // Load properties file
    public static void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    // Get property value
    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}
