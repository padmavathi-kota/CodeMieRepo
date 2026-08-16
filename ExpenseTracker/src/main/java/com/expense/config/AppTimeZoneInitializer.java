package com.expense.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Ensures JVM default timezone aligns with app.timezone.
 * Helps keep legacy java.util.Date usage consistent.
 *
 * NOTE: This affects JVM-wide Date operations. Modern code should use
 * java.time APIs with explicit ZoneId instead of relying on default TZ.
 */
@Component
public class AppTimeZoneInitializer {

    private static final Logger log = LoggerFactory.getLogger(AppTimeZoneInitializer.class);

    private final AppTimeZoneProperties props;

    public AppTimeZoneInitializer(AppTimeZoneProperties props) {
        this.props = props;
    }

    @PostConstruct
    public void init() {
        TimeZone tz = TimeZone.getTimeZone(props.getTimezone());
        TimeZone.setDefault(tz);
        log.info("Set JVM default timezone to: {}", tz.getID());
    }
}
