package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyUtils {

    public static Properties getPropertiesFile() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

}
