package com.expense.time;

import com.expense.config.AppTimeZoneProperties;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AppTimeServiceTest {

    @Test
    void testTodayReturnsCorrectDateInUTC() {
        AppTimeZoneProperties props = new AppTimeZoneProperties();
        props.setTimezone("UTC");
        AppTimeService service = new AppTimeService(props);

        LocalDate today = service.today();
        LocalDate expected = LocalDate.now(ZoneId.of("UTC"));

        assertEquals(expected, today);
    }

    @Test
    void testTodayIsoReturnsCorrectFormat() {
        AppTimeZoneProperties props = new AppTimeZoneProperties();
        props.setTimezone("UTC");
        AppTimeService service = new AppTimeService(props);

        String todayIso = service.todayIso();
        LocalDate expected = LocalDate.now(ZoneId.of("UTC"));

        assertEquals(expected.format(DateTimeFormatter.ISO_LOCAL_DATE), todayIso);
        assertTrue(todayIso.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    void testGetZoneIdReturnsCorrectZone() {
        AppTimeZoneProperties props = new AppTimeZoneProperties();
        props.setTimezone("America/New_York");
        AppTimeService service = new AppTimeService(props);

        ZoneId zoneId = service.getZoneId();

        assertEquals(ZoneId.of("America/New_York"), zoneId);
    }

    @Test
    void testGetTimeZoneIdReturnsCorrectString() {
        AppTimeZoneProperties props = new AppTimeZoneProperties();
        props.setTimezone("Europe/London");
        AppTimeService service = new AppTimeService(props);

        String timezoneId = service.getTimeZoneId();

        assertEquals("Europe/London", timezoneId);
    }

    @Test
    void testInvalidTimezoneDefaultsToUTC() {
        AppTimeZoneProperties props = new AppTimeZoneProperties();
        props.setTimezone("Invalid/Timezone");
        AppTimeService service = new AppTimeService(props);

        ZoneId zoneId = service.getZoneId();

        // Should fall back to UTC on invalid timezone
        assertEquals(ZoneId.of("UTC"), zoneId);
    }

    @Test
    void testZoneIdCaching() {
        AppTimeZoneProperties props = new AppTimeZoneProperties();
        props.setTimezone("UTC");
        AppTimeService service = new AppTimeService(props);

        ZoneId first = service.getZoneId();
        ZoneId second = service.getZoneId();

        // Should return same instance (cached)
        assertSame(first, second);
    }
}
