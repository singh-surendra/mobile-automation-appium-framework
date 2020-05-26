package dataProviders;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src/main/java/configs/Configuration.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getUserName(){
        String username = properties.getProperty("userName");
        if(username!= null) return username;
        else throw new RuntimeException("userName not specified in the Configuration.properties file.");
    }

    public String getPassword(){
        String password = properties.getProperty("password");
        if(password!= null) return password;
        else throw new RuntimeException("password not specified in the Configuration.properties file.");
    }

}
