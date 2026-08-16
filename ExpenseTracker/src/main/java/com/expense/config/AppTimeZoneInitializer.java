package com.expense.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Ensures JVM default timezone aligns with app.timezone.
 * Helps keep legacy java.util.Date usage consistent.
 */
@Component
public class AppTimeZoneInitializer {

    private final AppTimeZoneProperties props;

    public AppTimeZoneInitializer(AppTimeZoneProperties props) {
        this.props = props;
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(props.getTimezone()));
    }
}
