package servlets;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {

        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages", "servlets");

        return properties;
    }
}