package com.expensetracker.ui.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String today(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }
}
