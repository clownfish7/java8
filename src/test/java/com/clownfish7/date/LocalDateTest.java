package com.clownfish7.date;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * @author You
 * @create 2020-04-18 12:40
 */
public class LocalDateTest {

    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
    }

    @Test
    public void testLocalTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.getNano());
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    }

    @Test
    public void testInstant() {
        Instant instant = Instant.now();
        System.out.println(instant);
    }

    @Test
    public void testDuration() {
        LocalTime now = LocalTime.now();
        LocalTime after = now.plusHours(1);
        System.out.println(Duration.between(now, after).toMillis());
        System.out.println(Duration.between(after, now).getSeconds());
        System.out.println(Duration.ofSeconds(5).getSeconds());
    }

    @Test
    public void testPeriod() {
        Period period = Period.between(LocalDate.of(2010, 1, 1), LocalDate.now());
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(period.getYears());
    }

    @Test
    public void testDateFormat() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void testDateParse() {
        System.out.println(LocalDate.parse("20200418", DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(LocalTime.parse("13:19:42.416", DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(LocalDateTime.parse("2020-04-18 13:19:42", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
