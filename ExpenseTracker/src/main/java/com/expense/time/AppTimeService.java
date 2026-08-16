package com.expense.time;

import com.expense.config.AppTimeZoneProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class AppTimeService {

    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    private final AppTimeZoneProperties props;

    public AppTimeService(AppTimeZoneProperties props) {
        this.props = props;
    }

    public ZoneId getZoneId() {
        return ZoneId.of(props.getTimezone());
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
