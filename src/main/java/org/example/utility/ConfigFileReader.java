package org.example.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private final static String configFilePath = "src\\main\\resources\\config.properties";
    private final static Properties properties = new Properties();

    private ConfigFileReader() {}

    static {
        File configFile = new File(configFilePath);

        try {
            FileInputStream configFileReader = new FileInputStream(configFile);
            properties.load(configFileReader);
            configFileReader.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double getRate() {
        double rate = 0f;
        try {
            rate = Double.parseDouble(properties.getProperty("RATE"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rate;
    }
}
