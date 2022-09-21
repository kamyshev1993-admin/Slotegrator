package slotegrator.project.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;

public class ConfigQA {
    @Getter
    private String userName;
    @Getter
    private String baseurl;
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

    /**
     * read config file method
     */
    private void readConfig() {
        Config config =  ConfigFactory.parseResources(CONFIG_PATH);
        userName = config.getString("default_user_name");
        baseurl = config.getString("base_url");
    }
}
