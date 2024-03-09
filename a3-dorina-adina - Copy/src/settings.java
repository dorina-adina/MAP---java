import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class settings {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("C:/Users/User/Documents/GitHub/a3-dorina-adina/src/settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRepositoryType() {
        return properties.getProperty("Repository");
    }
}

