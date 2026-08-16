package com.expense.time;

import com.expense.config.AppTimeZoneProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class AppTimeService {

    private static final Logger log = LoggerFactory.getLogger(AppTimeService.class);
    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final ZoneId DEFAULT_ZONE = ZoneId.of("UTC");

    private final AppTimeZoneProperties props;
    private volatile ZoneId cachedZoneId;

    public AppTimeService(AppTimeZoneProperties props) {
        this.props = props;
    }

    public ZoneId getZoneId() {
        String timezone = props.getTimezone();
        if (cachedZoneId == null || !cachedZoneId.getId().equals(timezone)) {
            try {
                cachedZoneId = ZoneId.of(timezone);
            } catch (DateTimeException e) {
                log.error("Invalid timezone '{}', falling back to UTC", timezone, e);
                cachedZoneId = DEFAULT_ZONE;
            }
        }
        return cachedZoneId;
    }

    public String getTimeZoneId() {
        return getZoneId().getId();
    }

    public LocalDate today() {
        return LocalDate.now(getZoneId());
    }

    public String todayIso() {
        return today().format(ISO_DATE);
    }
}
