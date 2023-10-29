package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    static Properties properties = null;

    public static Properties readProp(String configFileName){

        properties = new Properties();
        FileInputStream fis;

        try{
            String propertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/" + configFileName;
            fis = new FileInputStream(propertiesFilePath);
            properties.load(fis);

        }catch (IOException e){
            e.printStackTrace();
        }

        return properties;
    }
}
