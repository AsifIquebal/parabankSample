package pageObjects.base;

public class Constants {

    // drivers are bundled with project, please change as per need
    public static final String CHROME_DRIVER_PATH_LINUX = System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver";
    public static final String CHROME_DRIVER_PATH_WINDOWS = System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe";

    public static final String GECKO_DRIVER_PATH_LINUX = System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver";
    static final String GECKO_DRIVER_PATH_WINDOWS = System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe";

    public static final String OPERA_DRIVER_PATH_LINUX = System.getProperty("user.dir") + "/src/main/resources/drivers/operadriver";

    // Application URL
    static final String APP_URL = "https://parabank.parasoft.com";

    // config values
    public static final int WAIT_TIME = 15;

}
