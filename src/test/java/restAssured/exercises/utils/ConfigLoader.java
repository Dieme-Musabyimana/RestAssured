package restAssured.exercises.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        // Now returns an empty object if file is missing instead of crashing
//        Now this is the dummy test
        // This is a test comment for Jenkins triggerin
        // This is a test comment for Jenkins triggering
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    private String getPropertyValue(String key) {
        // 1. Check System Properties (Jenkins -D variables)
        String prop = System.getProperty(key);

        // 2. If null, check the physical config.properties file
        if (prop == null && properties != null) {
            prop = properties.getProperty(key);
        }

        if (prop != null) return prop;
        else throw new RuntimeException("Property " + key + " is not specified in Jenkins or config.properties");
    }

    public String getClientId(){
        return getPropertyValue("client_id");
    }

    public String getClientSecret(){
        return getPropertyValue("client_secret");
    }

    public String getClientGrantType(){
        return getPropertyValue("grant_type");
    }

    public String getRefreshToken(){
        return getPropertyValue("refresh_token");
    }

    public String getUser(){
        return getPropertyValue("user");
    }
}