package com.laze.springcorepractice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "app.config")
@Getter
@Setter
public class AppProperties {

    private String name;
    private String description;
    private Server server;
    private List<String> authorizedRoles;
    private Map<String, String> additionalParams;

    @Getter
    @Setter
    public static class Server {
        private String host;
        private int port;

        @Override
        public String toString() {
            return "Server{host='" +host + "', port=" + port + "}";
        }
    }

    @Override
    public String toString() {
        return "AppProperties{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", server=" + server +
                ", authorizedRoles=" + authorizedRoles +
                ", additionalParams=" + additionalParams +
                '}';
    }
}
