package com.expense.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppTimeZoneProperties {

    /**
     * Application configured timezone, e.g. "UTC", "America/New_York".
     */
    private String timezone = "UTC";

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
