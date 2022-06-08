package util;

import framework.LogLevels;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties prop;

    /**
     * Provides the capability read a property file key and return the value of it.
     * @param propertyName expects the property file key
     * @return provides the value of the key provided
     */
    public static String getProperty(String propertyName) {
        prop = new Properties();
        try {
            InputStream propertyFileStream = PropertyReader.class.getClassLoader().getResourceAsStream("environment.properties");
            if (propertyFileStream != null) {
                prop.load(propertyFileStream);
                LoggerUtil.log("The property file was read successfully",LogLevels.INFO);
            } else {
                LoggerUtil.log("Cannot find environment.properties  file", LogLevels.ERROR);
            }
            return prop.getProperty(propertyName);

        } catch (IOException e) {
            LoggerUtil.log("IOException caught" + e.getMessage(), LogLevels.ERROR);

        }
        return null;
    }
}