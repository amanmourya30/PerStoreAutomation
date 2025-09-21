package api.utilities;

import java.util.ResourceBundle;

public class ConfigManager {
    private static ResourceBundle routes = ResourceBundle.getBundle("routes");

    public static String getUrl(String key) {
        return routes.getString(key);
    }
}
