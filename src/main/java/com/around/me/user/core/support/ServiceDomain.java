package com.around.me.user.core.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service")
public class ServiceDomain {

    private HashMap<String, String> domain;

    public String getDomain(String serviceName) {
        return domain.get(serviceName);
    }

    public void setDomain(HashMap<String, String> domain) {
        this.domain = domain;
    }
}
