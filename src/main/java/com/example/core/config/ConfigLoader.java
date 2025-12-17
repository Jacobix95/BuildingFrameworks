package com.example.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigLoader.class);

    private ConfigLoader() {
    }

    public static Config load() {
        String env = System.getProperty("env", "dev").toLowerCase();
        Properties fileProps = loadFileProps("/config-" + env + ".properties");

        return new Config(
                getString("base.url", fileProps, true),
                getString("browser", fileProps, false, "chrome"),
                getBool("headless", fileProps, false, false),
                getString("user.email", fileProps, false, ""),
                getString("user.password", fileProps, false, ""),
                getBool("highlight", fileProps, false, false)
        );
    }

    private static Properties loadFileProps(String path) {
        Properties p = new Properties();
        try (InputStream is = ConfigLoader.class.getResourceAsStream(path)) {
            if (is == null) throw new IllegalStateException("Missing properties file: " + path);
            p.load(is);
            LOG.info("Loaded config from {}", path);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load properties: " + path, e);
        }
        return p;
    }

    private static String getString(String key, Properties fileProps, boolean required) {
        return getString(key, fileProps, required, null);
    }

    private static String getString(String key, Properties fileProps, boolean required, String def) {
        String sys = System.getProperty(key);
        if (sys != null) return sys;

        String envKey = key.toUpperCase().replace('.', '_');
        String env = System.getenv(envKey);
        if (env != null) return env;

        if ("user.email".equals(key) && System.getenv("USER_EMAIL") != null)
            return System.getenv("USER_EMAIL");
        if ("user.password".equals(key) && System.getenv("USER_PASSWORD") != null)
            return System.getenv("USER_PASSWORD");

        String fromFile = fileProps.getProperty(key);
        if (fromFile != null) return fromFile;

        if (required && def == null) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return def;
    }

    private static boolean getBool(String key, Properties fileProps, boolean required, boolean def) {
        String v = getString(key, fileProps, required, String.valueOf(def));
        return "true".equalsIgnoreCase(v) || "1".equals(v);
    }

}

