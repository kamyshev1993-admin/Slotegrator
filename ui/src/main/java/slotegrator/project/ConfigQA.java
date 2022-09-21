package slotegrator.project;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;

public class ConfigQA {

    @Getter
    private String loginUrl;
    @Getter
    private String userName;
    @Getter
    private String password;

    private static final String CONFIG_PATH = "config.conf";
    private ConfigQA() {
        readConfig();
    }

    private static volatile ConfigQA instance;

    public static ConfigQA getInstance() {
        ConfigQA result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ConfigQA.class) {
            if (instance == null) {
                instance = new ConfigQA();
            }
            return instance;
        }
    }

    private void readConfig() {
        Config config =  ConfigFactory.parseResources(CONFIG_PATH);
        userName = config.getString("user_name");
        password = config.getString("password");
        loginUrl = config.getString("login_url");
    }
}
