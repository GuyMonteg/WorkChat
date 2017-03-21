package config;

import severControls.ChatServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Monteg on 16.03.2017.
 */

public class DBProperties {
    public static final String URL;
    public static final String USER;
    public static final String PASW;

    static {
        Properties propert = new Properties();
        ClassLoader classLoad = ChatServer.class.getClassLoader();
        try (InputStream stream = classLoad.getResourceAsStream("db.properties")) {
            propert.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not get DB properties");
        }
        try {
            Class.forName(propert.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        URL = propert.getProperty("db.url");
        USER = propert.getProperty("db.user");
        PASW = propert.getProperty("db.password");
    }
}
