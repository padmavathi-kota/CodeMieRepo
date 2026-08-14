package com.expensetracker.ui.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final String CONFIG_FILE = "/config.properties";
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = TestConfig.class.getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                throw new IllegalStateException("Missing " + CONFIG_FILE + " in test resources");
            }
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + CONFIG_FILE, e);
        }
    }

    public static String getBaseUrl() {
        return getEnvOrProp("BASE_URL", "baseUrl");
    }

    public static String getBrowser() {
        return getEnvOrProp("BROWSER", "browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getEnvOrProp("HEADLESS", "headless"));
    }

    public static int explicitWaitSeconds() {
        return Integer.parseInt(PROPS.getProperty("explicitWaitSeconds", "10"));
    }

    private static String getEnvOrProp(String envKey, String propKey) {
        String envVal = System.getenv(envKey);
        if (envVal != null && !envVal.isBlank()) return envVal.trim();
        return PROPS.getProperty(propKey);
    }
}
