package reddit.db_connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Utils {

    private static String PATH;

    public static Properties getProperties() {

        PATH = String.format("system.properties", System.getProperty("user.dir"));
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream(new File(PATH))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return properties;
    }
}
