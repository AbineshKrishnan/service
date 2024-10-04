package com.base.services.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author saikiranvalavoju
 * @author sabarees
 */
public final class DateUtils {

    private DateUtils() {
    }

    /**
     * Parses a String representation of a date into a `LocalDate` object using a specified date format pattern.
     * This method uses a `DateTimeFormatter` with the provided pattern to parse the String into a `LocalDate` object.
     * The `LocalDate` object represents the date only, without time information.
     *
     * @param date    The String representation of the date.
     * @param pattern The String format pattern used to parse the date String (e.g., "yyyy-MM-dd", "dd/MM/yyyy").
     * @return A `LocalDate` object representing the parsed date.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDate convertStringToLocalDate(String date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, dateTimeFormatter);
    }

    /**
     * Converts a String representation of a date into a `java.sql.Date` object using a specified date format pattern.
     * This method first parses the String into a `LocalDate` object using the provided pattern.
     * Then, it converts the `LocalDate` object to a `java.sql.Date` object.
     *
     * @param date        The String representation of the date.
     * @param datePattern The String format pattern used to parse the date String (e.g., "yyyy-MM-dd", "dd/MM/yyyy").
     * @return A `java.sql.Date` object representing the parsed date.
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static Date convertStringToSqlDate(String date, String datePattern) {
        LocalDate localDate = convertStringToLocalDate(date, datePattern);
        return toSqlDate(localDate);
    }

    /**
     * Converts a String representation of a date into a `java.util.Date` object using a specified date format pattern.
     * This method first parses the String into a `LocalDate` object using the provided pattern.
     * Then, it converts the `LocalDate` object to a `java.util.Date` object.
     *
     * @param date        The String representation of the date.
     * @param datePattern The String format pattern used to parse the date String (e.g., "yyyy-MM-dd", "dd/MM/yyyy").
     * @return A `java.util.Date` object representing the parsed date.
     * @author sabarees
     * @since V1.0.0
     */
    public static java.util.Date convertStringToUtilDate(String date, String datePattern) {
        LocalDate localDate = convertStringToLocalDate(date, datePattern);
        return toUtilDate(localDate);
    }

    /**
     * Formats a `LocalDateTime` object into a String representation using a specified date and time format pattern.
     * This method uses a `DateTimeFormatter` with the provided pattern to format the `LocalDateTime` object.
     * The `LocalDateTime` object includes both date and time information.
     *
     * @param localDateTime The `LocalDateTime` object to be formatted.
     * @param pattern       The String format pattern for the output date and time String (e.g., "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy hh:mm a").
     * @return A String representation of the `LocalDateTime` object in the specified format.
     * @author sabarees
     * @since V1.0.0
     */
    public static String convertLocalDateToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * Converts a `java.util.Date` object to a String representation using a specified date format pattern.
     * This method first converts the `java.util.Date` object to a `LocalDate` object, representing the date only without time information.
     * Then, it sets the time to the beginning of the day (00:00:00) using `atStartOfDay()` to ensure consistent behavior.
     * Finally, it uses a `DateTimeFormatter` with the provided pattern to format the `LocalDate` object into a String.
     *
     * @param date    The `java.util.Date` object representing a date.
     * @param pattern The String format pattern for the output date String (e.g., "yyyy-MM-dd", "dd/MM/yyyy").
     * @return A String representation of the date in the specified format.
     * @author sabarees
     * @since V1.0.0
     */
    public static String convertUtilDateToString(java.util.Date date, String pattern) {
        return convertLocalDateToString(toLocalDate(date).atStartOfDay(), pattern);
    }

    /**
     * Converts a `java.sql.Date` object to a String representation using a specified date format pattern.
     * This method first converts the `java.sql.Date` object to a `LocalDate` object, representing the date only without time information.
     * Then, it sets the time to the beginning of the day (00:00:00) using `atStartOfDay()` to ensure consistent behavior.
     * Finally, it uses a `DateTimeFormatter` with the provided pattern to format the `LocalDate` object into a String.
     *
     * @param date    The `java.sql.Date` object representing a date.
     * @param pattern The String format pattern for the output date String (e.g., "yyyy-MM-dd", "dd/MM/yyyy").
     * @return A String representation of the date in the specified format.
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static String convertSqlDateToString(Date date, String pattern) {
        return convertLocalDateToString(toLocalDate(date).atStartOfDay(), pattern);
    }

    /**
     * Calculates a future date by adding a specified number of days to the given date.
     *
     * @param localDate  The starting date from which to add days
     * @param noOfDays   The number of days to add to the starting date (positive integer)
     * @param noOfWeeks  The number of weeks to add to the starting week (positive integer)
     * @param noOfMonths The number of months to add to the starting month (positive integer)
     * @param noOfYears  The number of years to add to the starting year (positive integer)
     * @return A {@code LocalDate} object representing the calculated future date
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDate calculateFutureLocalDate(LocalDate localDate, int noOfDays, int noOfWeeks, int noOfMonths, int noOfYears) {
        return localDate.plusDays(noOfDays).plusWeeks(noOfWeeks)
                .plusMonths(noOfMonths).plusYears(noOfYears);
    }

    /**
     * Calculates a future date by adding a specified number of days to the given date.
     *
     * @param date       The starting date from which to add days
     * @param noOfDays   The number of days to add to the starting date (positive integer)
     * @param noOfWeeks  The number of weeks to add to the starting week (positive integer)
     * @param noOfMonths The number of months to add to the starting month (positive integer)
     * @param noOfYears  The number of years to add to the starting year (positive integer)
     * @return A {@code LocalDate} object representing the calculated future date
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static Date calculateFutureSqlDate(Date date, int noOfDays, int noOfWeeks, int noOfMonths, int noOfYears) {
        return toSqlDate(calculateFutureLocalDate(toLocalDate(date), noOfDays, noOfWeeks, noOfMonths, noOfYears));
    }

    /**
     * Calculates a future date by adding a specified number of days to the given date.
     *
     * @param date       The starting date from which to add days
     * @param noOfDays   The number of days to add to the starting date (positive integer)
     * @param noOfWeeks  The number of weeks to add to the starting week (positive integer)
     * @param noOfMonths The number of months to add to the starting month (positive integer)
     * @param noOfYears  The number of years to add to the starting year (positive integer)
     * @return A {@code java.util.Date} object representing the calculated future date
     * @author sabarees
     * @since V1.0.0
     */
    public static java.util.Date calculateFutureUtilDate(java.util.Date date, int noOfDays, int noOfWeeks, int noOfMonths, int noOfYears) {
        return toUtilDate(calculateFutureLocalDate(toLocalDate(date), noOfDays, noOfWeeks, noOfMonths, noOfYears));
    }

    /**
     * Calculates a past date by subtracting a specified number of days from the given date.
     *
     * @param localDate  The starting date from which to subtract days
     * @param noOfDays   The number of days to add to the starting date (positive integer)
     * @param noOfWeeks  The number of weeks to add to the starting week (positive integer)
     * @param noOfMonths The number of months to add to the starting month (positive integer)
     * @param noOfYears  The number of years to add to the starting year (positive integer)
     * @return A {@code LocalDate} object representing the calculated past date
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDate calculatePastLocalDate(LocalDate localDate, int noOfDays, int noOfWeeks, int noOfMonths, int noOfYears) {
        return localDate.minusDays(noOfDays).minusWeeks(noOfWeeks)
                .minusMonths(noOfMonths).minusYears(noOfYears);
    }

    /**
     * Calculates a past date by subtracting a specified number of days from the given date.
     *
     * @param date       The starting date from which to subtract days
     * @param noOfDays   The number of days to add to the starting date (positive integer)
     * @param noOfWeeks  The number of weeks to add to the starting week (positive integer)
     * @param noOfMonths The number of months to add to the starting month (positive integer)
     * @param noOfYears  The number of years to add to the starting year (positive integer)
     * @return A {@code java.sql.Date} object representing the calculated past date
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static Date calculatePastSqlDate(Date date, int noOfDays, int noOfWeeks, int noOfMonths, int noOfYears) {
        return toSqlDate(calculatePastLocalDate(toLocalDate(date), noOfDays, noOfWeeks, noOfMonths, noOfYears));
    }

    /**
     * Calculates a past date by subtracting a specified number of days from the given date.
     *
     * @param date       The starting date from which to subtract days
     * @param noOfDays   The number of days to add to the starting date (positive integer)
     * @param noOfWeeks  The number of weeks to add to the starting week (positive integer)
     * @param noOfMonths The number of months to add to the starting month (positive integer)
     * @param noOfYears  The number of years to add to the starting year (positive integer)
     * @return A {@code java.util.Date} object representing the calculated past date
     * @author sabarees
     * @since V1.0.0
     */
    public static java.util.Date calculatePastUtilDate(java.util.Date date, int noOfDays, int noOfWeeks, int noOfMonths, int noOfYears) {
        return toUtilDate(calculatePastLocalDate(toLocalDate(date), noOfDays, noOfWeeks, noOfMonths, noOfYears));
    }

    /**
     * Extracts the year component from a `LocalDate` object.
     * This method uses the `getYear()` method of the `LocalDate` class to retrieve the year as an integer value.
     *
     * @param localDate The `LocalDate` object from which to extract the year.
     * @return A {@code integer} representing the year component of the `LocalDate` object.
     * @author sabarees
     * @since V1.0.0
     */
    public static int extractYearFromLocalDate(LocalDate localDate) {
        return localDate.getYear();
    }

    /**
     * Extracts the year component from a `java.sql.Date` object.
     * This method first converts the `java.sql.Date` object to a `LocalDate` object.
     * Then, it calls the `extractYearFromLocalDate` method to extract the year from the `LocalDate` object.
     *
     * @param date The `java.sql.Date` object from which to extract the year.
     * @return A {@code integer} representing the year component of the `java.util.Date` object.
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static int extractYearFromSqlDate(Date date) {
        return extractYearFromLocalDate(toLocalDate(date));
    }

    /**
     * Extracts the year component from a `java.util.Date` object.
     * This method first converts the `java.util.Date` object to a `LocalDate` object.
     * Then, it calls the `extractYearFromLocalDate` method to extract the year from the `LocalDate` object.
     *
     * @param date The `java.util.Date` object from which to extract the year.
     * @return A {@code integer} representing the year component of the `java.util.Date` object.
     * @author sabarees
     * @since V1.0.0
     */
    public static int extractYearFromUtilDate(java.util.Date date) {
        return extractYearFromLocalDate(toLocalDate(date));
    }

    /**
     * Gets the ending date (start day of the month) of the current month in the provided time zone.
     *
     * @param sourceTimeZone The time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code LocalDateTime} object representing the starting date (last day of the month) of the current month in the specified time zone
     * @author sabarees
     * @apiNote Supported time zone IDs follow the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static LocalDateTime currentMonthStartLocalDate(String sourceTimeZone) {
        LocalDate now = LocalDate.now(ZoneId.of(sourceTimeZone));
        LocalDate startDayOfMonth = now.withDayOfMonth(1);
        return startDayOfMonth.atTime(LocalTime.MIN);
    }

    /**
     * Gets the ending date (last day of the month) of the current month in the provided time zone.
     *
     * @param sourceTimeZone The time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code LocalDateTime} object representing the ending date (last day of the month) of the current month in the specified time zone
     * @author sabarees
     * @apiNote Supported time zone IDs follow the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static LocalDateTime currentMonthEndLocalDate(String sourceTimeZone) {
        LocalDate now = LocalDate.now(ZoneId.of(sourceTimeZone));
        LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());
        return lastDayOfMonth.atTime(LocalTime.MAX);
    }

    /**
     * Gets the ending date (start day of the month) of the current month in the provided time zone.
     *
     * @param sourceTimeZone The time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code java.sql.Date} object representing the starting date (last day of the month) of the current month in the specified time zone
     * @author saikiranvalavoju
     * @apiNote Supported time zone IDs follow the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static Date currentMonthStartSqlDate(String sourceTimeZone) {
        return toSqlDate(currentMonthStartLocalDate(sourceTimeZone).toLocalDate());
    }

    /**
     * Gets the ending date (last day of the month) of the current month in the provided time zone.
     *
     * @param sourceTimeZone The time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code java.sql.Date} object representing the ending date (last day of the month) of the current month in the specified time zone
     * @author saikiranvalavoju
     * @apiNote Supported time zone IDs follow the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static Date currentMonthEndSqlDate(String sourceTimeZone) {
        return toSqlDate(currentMonthEndLocalDate(sourceTimeZone).toLocalDate());
    }

    /**
     * Gets the ending date (start day of the month) of the current month in the provided time zone.
     *
     * @param sourceTimeZone The time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code java.util.Date} object representing the starting date (last day of the month) of the current month in the specified time zone
     * @author sabarees
     * @apiNote Supported time zone IDs follow the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static java.util.Date currentMonthStartUtilDate(String sourceTimeZone) {
        return toUtilDate(currentMonthStartLocalDate(sourceTimeZone).toLocalDate());
    }

    /**
     * Gets the ending date (last day of the month) of the current month in the provided time zone.
     *
     * @param sourceTimeZone The time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code java.util.Date} object representing the ending date (last day of the month) of the current month in the specified time zone
     * @author sabarees
     * @apiNote Supported time zone IDs follow the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static java.util.Date currentMonthEndUtilDate(String sourceTimeZone) {
        return toUtilDate(currentMonthEndLocalDate(sourceTimeZone).toLocalDate());
    }

    /**
     * Gets the starting date (January 1st) of the current year in the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code LocalDateTime} object representing the starting date (January 1st) of the current year in the specified time zone
     * @author sabarees
     * @apiNote Time zone format follows the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static LocalDateTime currentYearStartLocalDateTime(String sourceTimeZone) {
        Year yearNow = Year.now(ZoneId.of(sourceTimeZone));
        LocalDate firstDayOfYear = yearNow.atDay(1);
        return firstDayOfYear.atStartOfDay();
    }

    /**
     * Gets the starting date (January 1st) of the current year in the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code Instant} object representing the starting date (January 1st) of the current year in the specified time zone
     * @author sabarees
     * @apiNote Time zone format follows the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static Instant currentYearStartInstant(String sourceTimeZone) {
        return toInstant(currentYearStartLocalDateTime(sourceTimeZone));
    }

    /**
     * Gets the starting date (January 1st) of the current year in the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code Timestamp} object representing the starting date (January 1st) of the current year in the specified time zone
     * @author saikiranvalavoju
     * @apiNote Time zone format follows the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static Timestamp currentYearStartTimeStamp(String sourceTimeZone) {
        return toTimeStamp(currentYearStartLocalDateTime(sourceTimeZone));
    }

    /**
     * Gets the starting date (December 31st) of the current year in the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code Timestamp} object representing the ending date (December 31st) of the current year in the specified time zone
     * @author sabarees
     * @apiNote Time zone format follows the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static LocalDateTime currentYearEndLocalDate(String sourceTimeZone) {
        Year yearNow = Year.now(ZoneId.of(sourceTimeZone));
        YearMonth yearMonthNow = yearNow.atMonth(12);
        LocalDate lastOfMonth = yearMonthNow.atEndOfMonth();
        return lastOfMonth.atTime(LocalTime.MAX);
    }

    /**
     * Gets the starting date (December 31st) of the current year in the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code Instant} object representing the ending date (December 31st) of the current year in the specified time zone
     * @author sabarees
     * @apiNote Time zone format follows the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static Instant currentYearEndDate(String sourceTimeZone) {
        return toInstant(currentYearEndLocalDate(sourceTimeZone));
    }

    /**
     * Gets the starting date (December 31st) of the current year in the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code Timestamp} object representing the ending date (December 31st) of the current year in the specified time zone
     * @author sabarees
     * @apiNote Time zone format follows the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static Timestamp currentYearEndTimestamp(String sourceTimeZone) {
        return toTimeStamp(currentYearEndLocalDate(sourceTimeZone));
    }

    /**
     * Gets the current date and time as a ZonedDateTime based on the system default time zone.
     *
     * @return A {@code ZonedDateTime} representation of the current date and time in the system default time zone
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime getCurrentDateTime() {
        LocalDateTime today = LocalDateTime.now();
        return today.atZone(ZoneId.systemDefault());
    }

    /**
     * Gets the current date and time as a LocalDateTime object without time zone information.
     * This method first calls `getCurrentDateTime` to get the current date and time in the system default time zone as a `ZonedDateTime` object.
     * Then, it extracts the `LocalDateTime` component from the `ZonedDateTime` object, effectively discarding the time zone information.
     *
     * @return A {@code LocalDateTime} representation of the current date and time without time zone information.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return getCurrentDateTime().toLocalDateTime();
    }

    /**
     * Gets the current instant as an Instant object representing a specific moment in time without time zone information.
     * This method first calls `getCurrentDateTime` to get the current date and time in the system default time zone as a `ZonedDateTime` object.
     * Then, it converts the `ZonedDateTime` object to an `Instant` object, effectively discarding the time zone information.
     *
     * @return An {@code Instant} object representing the current instant in time without time zone information.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant getCurrentInstant() {
        return toInstant(getCurrentDateTime().toLocalDateTime());
    }

    /**
     * Gets the current date as a java.sql.Date object representing the date only without time information.
     * This method first calls `getCurrentDateTime` to get the current date and time in the system default time zone as a `ZonedDateTime` object.
     * Then, it extracts the `LocalDate` component from the `ZonedDateTime` object, effectively discarding the time information.
     * Finally, it converts the `LocalDate` object to a `java.sql.Date` object.
     *
     * @return A {@code java.sql.Date} object representing the current date.
     * @author sabarees
     * @since V1.0.0
     */
    public static Date getCurrentSqlDate() {
        return toSqlDate(getCurrentDateTime().toLocalDate());
    }

    /**
     * Gets the current date as a java.util.Date object representing the date only without time information.
     * This method first calls `getCurrentDateTime` to get the current date and time in the system default time zone as a `ZonedDateTime` object.
     * Then, it extracts the `LocalDate` component from the `ZonedDateTime` object, effectively discarding the time information.
     * Finally, it converts the `LocalDate` object to a `java.util.Date` object.
     *
     * @return A {@code java.util.Date} object representing the current date.
     * @author sabarees
     * @since V1.0.0
     */
    public static java.util.Date getCurrentUtilDate() {
        return toUtilDate(getCurrentDateTime().toLocalDate());
    }

    /**
     * Gets the current date and time as a ZonedDateTime based on the provided time zone.
     *
     * @param sourceTimeZone The time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code ZonedDateTime} representation of the current date and time in the specified time zone
     * @author sabarees
     * @apiNote Supported time zone IDs follow the standard Time Zone Database format (e.g., "Asia/Kolkata").
     * @since V1.0.0
     */
    public static ZonedDateTime getCurrentDateByTimeZone(String sourceTimeZone) {
        ZoneId fromTimeZone = ZoneId.of(sourceTimeZone);
        LocalDateTime today = LocalDateTime.now();
        return today.atZone(fromTimeZone);
    }

    /**
     * Gets the current date as a java.sql.Date object, considering the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code LocalDateTime} object representing the current date in the specified time zone
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static LocalDateTime getCurrentLocalDateTime(String sourceTimeZone) {
        return getCurrentDateByTimeZone(sourceTimeZone).toLocalDateTime();
    }

    /**
     * Gets the current date as a java.sql.Date object, considering the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code java.sql.Date} object representing the current date in the specified time zone
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static Date getCurrentSqlDate(String sourceTimeZone) {
        return toSqlDate(getCurrentDateByTimeZone(sourceTimeZone).toLocalDate());
    }

    /**
     * Gets the current date as a java.sql.Date object, considering the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code java.util.Date} object representing the current date in the specified time zone
     * @author sabarees
     * @since V1.0.0
     */
    public static java.util.Date getCurrentUtilDate(String sourceTimeZone) {
        return toUtilDate(getCurrentDateByTimeZone(sourceTimeZone).toLocalDate());
    }

    /**
     * Gets the current date as a java.sql.Date object, considering the provided time zone.
     *
     * @param sourceTimeZone The desired time zone ID (e.g., "Asia/Kolkata", "Europe/Dublin")
     * @return A {@code Instant} object representing the current date in the specified time zone
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant getCurrentInstant(String sourceTimeZone) {
        return toInstant(getCurrentDateByTimeZone(sourceTimeZone));
    }

    /**
     * Gets the names of the last 12 months, starting from the provided month and year string (inclusive).
     * This method uses LocalDate and DateTimeFormatter for date manipulation, ensuring order is descending
     * (most recent month first).
     *
     * @param monthFormat A String representation of the month and year in the format "MMM-yyyy" (e.g., "Jan-2024")
     * @return A List containing the names of the last 12 months in descending order (most recent first)
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static List<String> getLast12Months(String monthFormat) {
        List<String> monthNames = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 1; i <= 12; i++) {
            monthNames.add(date.minusMonths(i).format(DateTimeFormatter.ofPattern(monthFormat)));
        }
        return monthNames;
    }

    /**
     * Gets the names of the next 12 months, starting from the provided month and year string (inclusive).
     * This method uses LocalDate and DateTimeFormatter for date manipulation, ensuring order is descending
     * (most recent month first).
     *
     * @param monthFormat A String representation of the month and year in the format "MMM-yyyy" (e.g., "Jan-2024")
     * @return A List containing the names of the next 12 months in ascending order (most recent first)
     * @author sabarees
     * @since V1.0.0
     */
    public static List<String> getNext12Months(String monthFormat) {
        List<String> monthNames = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 1; i <= 12; i++) {
            monthNames.add(date.plusMonths(i).format(DateTimeFormatter.ofPattern(monthFormat)));
        }
        return monthNames;
    }

    /**
     * Calculates the date and time by adding the specified number of minutes to the current date and time.
     *
     * @param expiryTimeInMinutes The number of minutes to add (must be non-negative)
     * @return A {@link LocalDateTime} object representing the calculated date and time
     * @throws IllegalArgumentException if the provided expiryTimeInMinutes is negative
     * @author saikiranvalavoju
     * @since V1.0.0
     */
    public static LocalDateTime calculateDateAndTimeFromGivenMinutes(int expiryTimeInMinutes) throws IllegalArgumentException {
        if (expiryTimeInMinutes < 0) throw new IllegalArgumentException("Expiry time in minutes cannot be negative");
        LocalDateTime now = LocalDateTime.now();
        return now.plusMinutes(expiryTimeInMinutes);
    }

    /**
     * Calculates the difference between two dates provided as strings and returns a human-readable format.
     *
     * @param startDateStr String representation of the start date in a specified format (e.g., "dd-MM-yyyy", "dd/MM/yyyy HH:mm:ss")
     * @param endDateStr   String representation of the end date in the same format as startDateStr
     * @param dateFormat   The exact date format pattern used in startDateStr and endDateStr (e.g., "yyyy-MM-dd", "dd/MM/yyyy HH:mm")
     * @return A String representing the difference between the two dates in a human-readable format (e.g., "2y 3 days 1h 2m 3s")
     * @throws ParseException If the provided date strings cannot be parsed using the specified format
     * @author saikiranvalavoju
     * @apiNote This method supports various date and time format patterns for both start and end dates.
     * - Patterns without time: dd-MM-yyyy, dd/MM/yyyy, MM/dd/yyyy, MM-dd-yyyy, etc.
     * - Patterns with time: dd-MM-yyyy HH:mm:ss, dd/MM/yyyy HH:mm:ss, dd-MM-yyyy HH:mm aa, etc.
     * @since V1.0.0
     */
    public static String findDifferenceBwTwoDates(String startDateStr, String endDateStr, String dateFormat)
            throws ParseException {

        SimpleDateFormat startDateSDF = new SimpleDateFormat(dateFormat);
        SimpleDateFormat endDateSDF = new SimpleDateFormat(dateFormat);
        java.util.Date startDate = startDateSDF.parse(startDateStr);
        java.util.Date endDate = endDateSDF.parse(endDateStr);
        long differenceInTime = endDate.getTime() - startDate.getTime();
        long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInTime) % 60;
        long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceInTime) % 60;
        long differenceInHours = TimeUnit.MILLISECONDS.toHours(differenceInTime) % 24;
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInTime) % 365;
        long differenceInYears = TimeUnit.MILLISECONDS.toDays(differenceInTime) / 365L;

        StringBuilder builder = new StringBuilder();
        if (differenceInYears > 0) {
            builder.append(differenceInYears).append("y ");
        }
        if (Math.abs(differenceInDays) > 0) {
            builder.append(Math.abs(differenceInDays)).append(" days");
        }
        if (differenceInHours > 0) {
            builder.append(differenceInHours).append("h ");
        }
        if (differenceInMinutes > 0) {
            builder.append(differenceInMinutes).append("m ");
        }
        if (differenceInSeconds > 0) {
            builder.append(differenceInSeconds).append("s ");
        }
        return builder.toString();
    }


    /**
     * Converts an `Instant` object to a `LocalDateTime` object.
     *
     * @param instant The `Instant` object representing a specific moment in time.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `Instant`.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        return toLocalDateTime(toZonedDateTime(instant));
    }

    /**
     * Converts an `Instant` object with a source time zone to a `LocalDateTime` object.
     *
     * @param instant        The `Instant` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `Instant` object.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `Instant` in the source zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(Instant instant, String sourceTimeZone) {
        return toLocalDateTime(toZonedDateTime(instant, sourceTimeZone));
    }

    /**
     * Converts a `Timestamp` object to a `LocalDateTime` object.
     *
     * @param timestamp The `Timestamp` object representing a specific moment in time.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `Timestamp`.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    /**
     * Converts a `Timestamp` object with a source time zone to a `LocalDateTime` object.
     *
     * @param timestamp      The `Timestamp` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `Timestamp` object.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `Timestamp` in the source zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(Timestamp timestamp, String sourceTimeZone) {
        return toLocalDateTime(toInstant(timestamp, sourceTimeZone));
    }

    /**
     * Converts a `ZonedDateTime` object to a `LocalDateTime` object.
     *
     * @param zonedDateTime The `ZonedDateTime` object representing a specific moment in time.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `ZonedDateTime`.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * Converts a `ZonedDateTime` object with a source time zone to a `LocalDateTime` object.
     *
     * @param zonedDateTime  The `ZonedDateTime` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `ZonedDateTime` object.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `ZonedDateTime` in the source zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime, String sourceTimeZone) {
        return toLocalDateTime(zonedDateTime.withZoneSameInstant(ZoneId.of(sourceTimeZone)));
    }

    /**
     * Converts an `OffsetDateTime` object to a `LocalDateTime` object.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `OffsetDateTime`.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDateTime();
    }

    /**
     * Converts an `OffsetDateTime` object with a source time zone to a `LocalDateTime` object.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `OffsetDateTime` object.
     * @return A {@code LocalDateTime} object representing the same moment in time as the `OffsetDateTime` in the source zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime, String sourceTimeZone) {
        return toLocalDateTime(toZonedDateTime(offsetDateTime, sourceTimeZone));
    }

    /**
     * Converts a `LocalDateTime` object to an `Instant` object.
     * This method first converts the `LocalDateTime` to a `ZonedDateTime` using the system default time zone.
     * Then, it extracts the `Instant` from the `ZonedDateTime`.
     *
     * @param localDateTime The `LocalDateTime` object representing a specific moment in time.
     * @return An {@code Instant} object representing the same moment in time as the `LocalDateTime` in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(LocalDateTime localDateTime) {
        return toInstant(toZonedDateTime(localDateTime));
    }

    /**
     * Converts a `LocalDateTime` object with a specified source time zone to an `Instant` object.
     * This method first converts the `LocalDateTime` to a `ZonedDateTime` using the provided source time zone.
     * Then, it extracts the `Instant` from the `ZonedDateTime`.
     *
     * @param localDateTime  The `LocalDateTime` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `LocalDateTime` object.
     * @return An {@code Instant} object representing the same moment in time as the `LocalDateTime` in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(LocalDateTime localDateTime, String sourceTimeZone) {
        return toInstant(toZonedDateTime(localDateTime, sourceTimeZone));
    }

    /**
     * Converts a `Timestamp` object to an `Instant` object.
     * This method leverages the built-in `toInstant` method of the `Timestamp` class.
     *
     * @param timestamp The `Timestamp` object representing a specific moment in time.
     * @return An {@code Instant} object representing the same moment in time as the `Timestamp`.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(Timestamp timestamp) {
        return timestamp.toInstant();
    }

    /**
     * Converts a `Timestamp` object with a specified source time zone to an `Instant` object.
     * This method first converts the `Timestamp` to a `ZonedDateTime` using the provided source time zone.
     * Then, it extracts the `Instant` from the `ZonedDateTime`.
     *
     * @param timestamp      The `Timestamp` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `Timestamp` object.
     * @return An {@code Instant} object representing the same moment in time as the `Timestamp` in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(Timestamp timestamp, String sourceTimeZone) {
        return toInstant(toZonedDateTime(timestamp, sourceTimeZone));
    }

    /**
     * Converts a `ZonedDateTime` object to an `Instant` object.
     * This method leverages the built-in `toInstant` method of the `ZonedDateTime` class.
     *
     * @param zonedDateTime The `ZonedDateTime` object representing a specific moment in time.
     * @return An {@code Instant} object representing the same moment in time as the `ZonedDateTime`.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant();
    }

    /**
     * Converts a `ZonedDateTime` object with a target time zone to an `Instant` object.
     * This method first converts the `ZonedDateTime` to an `Instant`.
     * Then, it creates a new `ZonedDateTime` object in the target time zone using the extracted `Instant`.
     * Finally, it extracts the `Instant` from the new `ZonedDateTime`.
     *
     * @param zonedDateTime  The `ZonedDateTime` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the source time zone associated with the `ZonedDateTime` object.
     * @return An {@code Instant} object representing the same moment in time as the `ZonedDateTime` in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(ZonedDateTime zonedDateTime, String sourceTimeZone) {
        return toInstant(zonedDateTime.withZoneSameInstant(ZoneId.of(sourceTimeZone)));
    }

    /**
     * Converts an `OffsetDateTime` object to an `Instant` object.
     * This method leverages the built-in `toInstant` method of the `OffsetDateTime` class.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time.
     * @return An {@code Instant} object representing the same moment in time as the `OffsetDateTime`.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toInstant();
    }

    /**
     * Converts an `OffsetDateTime` object with a specified source time zone to an `Instant` object.
     * This method first converts the `OffsetDateTime` to a `ZonedDateTime` using the provided source time zone.
     * Then, it extracts the `Instant` from the `ZonedDateTime`.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `OffsetDateTime` object.
     * @return An {@code Instant} object representing the same moment in time as the `OffsetDateTime` in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Instant toInstant(OffsetDateTime offsetDateTime, String sourceTimeZone) {
        return toInstant(toZonedDateTime(offsetDateTime, sourceTimeZone));
    }

    /**
     * Converts a `LocalDateTime` object to a `ZonedDateTime` object using the system default time zone.
     * This method assumes the `LocalDateTime` represents a date and time without time zone information.
     * It assigns the system default time zone to the `LocalDateTime` to create a `ZonedDateTime`.
     *
     * @param localDateTime The `LocalDateTime` object representing a date and time.
     * @return A {@code ZonedDateTime} object representing the same date and time in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault());
    }

    /**
     * Converts a `LocalDateTime` object to a `ZonedDateTime` object using a specified source time zone.
     * This method assumes the `LocalDateTime` represents a date and time without time zone information.
     * It assigns the provided source time zone to the `LocalDateTime` to create a `ZonedDateTime`.
     *
     * @param localDateTime  The `LocalDateTime` object representing a date and time.
     * @param sourceTimeZone The String ID of the time zone associated with the `LocalDateTime` object.
     * @return A {@code ZonedDateTime} object representing the same date and time in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, String sourceTimeZone) {
        return localDateTime.atZone(ZoneId.of(sourceTimeZone));
    }

    /**
     * Converts an `Instant` object to a `ZonedDateTime` object using the system default time zone.
     * An `Instant` represents a specific moment in time without time zone information.
     * This method assigns the system default time zone to the `Instant` to create a `ZonedDateTime`.
     *
     * @param instant The `Instant` object representing a specific moment in time.
     * @return A {@code ZonedDateTime} object representing the same moment in time in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(Instant instant) {
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * Converts an `Instant` object to a `ZonedDateTime` object using a specified source time zone.
     * An `Instant` represents a specific moment in time without time zone information.
     * This method assigns the provided source time zone to the `Instant` to create a `ZonedDateTime`.
     *
     * @param instant        The `Instant` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `Instant` object.
     * @return A {@code ZonedDateTime} object representing the same moment in time in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(Instant instant, String sourceTimeZone) {
        return ZonedDateTime.ofInstant(instant, ZoneId.of(sourceTimeZone));
    }

    /**
     * Converts an `OffsetDateTime` object to a `ZonedDateTime` object using the system default time zone.
     * An `OffsetDateTime` includes both date, time, and offset information.
     * This method keeps the same date, time, and offset information and assigns the system default time zone to create a `ZonedDateTime`.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time with an offset from UTC.
     * @return A {@code ZonedDateTime} object representing the same moment in time with the same offset from UTC in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime.atZoneSameInstant(ZoneId.systemDefault());
    }

    /**
     * Converts an `OffsetDateTime` object to a `ZonedDateTime` object using a specified source time zone.
     * An `OffsetDateTime` includes both date, time, and offset information.
     * This method keeps the same date, time, and offset information and assigns the provided source time zone to create a `ZonedDateTime`.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time with an offset from UTC.
     * @param sourceTimeZone The String ID of the time zone associated with the `OffsetDateTime` object (likely for reference).
     * @return A {@code ZonedDateTime} object representing the same moment in time with the same offset from UTC in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(OffsetDateTime offsetDateTime, String sourceTimeZone) {
        return offsetDateTime.atZoneSameInstant(ZoneId.of(sourceTimeZone));
    }


    /**
     * Converts a `Timestamp` object to a `ZonedDateTime` object using the system default time zone.
     * A `Timestamp` represents a date and time with millisecond precision, typically in UTC.
     * This method first converts the `Timestamp` to an `Instant` and then assigns the system default time zone to create a `ZonedDateTime`.
     *
     * @param timestamp The `Timestamp` object representing a date and time.
     * @return A {@code ZonedDateTime} object representing the same date and time in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(Timestamp timestamp) {
        return toInstant(timestamp).atZone(ZoneId.systemDefault());
    }

    /**
     * Converts a `Timestamp` object to a `ZonedDateTime` object using a specified source time zone.
     * A `Timestamp` represents a date and time with millisecond precision, typically in UTC.
     * This method first converts the `Timestamp` to an `Instant` and then assigns the provided source time zone to create a `ZonedDateTime`.
     *
     * @param timestamp      The `Timestamp` object representing a date and time.
     * @param sourceTimeZone The String ID of the time zone associated with the `Timestamp` object (likely for reference).
     * @return A {@code ZonedDateTime} object representing the same date and time in the specified source time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static ZonedDateTime toZonedDateTime(Timestamp timestamp, String sourceTimeZone) {
        return toInstant(timestamp).atZone(ZoneId.of(sourceTimeZone));
    }

    /**
     * Converts a `LocalDateTime` object to a `Timestamp` object.
     * This method assumes the `LocalDateTime` represents a date and time without time zone information.
     * It creates a `Timestamp` object with the same date and time in the system default time zone.
     *
     * @param localDateTime The `LocalDateTime` object representing a date and time.
     * @return A {@code Timestamp} object representing the same date and time in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Converts a `LocalDateTime` object with a specified source time zone to a `Timestamp` object.
     * This method first converts the `LocalDateTime` to a `ZonedDateTime` using the provided source time zone.
     * Then, it converts the `ZonedDateTime` to a `LocalDateTime` (effectively dropping the time zone information).
     * Finally, it creates a `Timestamp` object with the resulting `LocalDateTime` in the system default time zone.
     *
     * @param localDateTime  The `LocalDateTime` object representing a date and time.
     * @param sourceTimeZone The String ID of the time zone associated with the `LocalDateTime` object.
     * @return A {@code Timestamp} object representing the same date and time (without time zone information) in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(LocalDateTime localDateTime, String sourceTimeZone) {
        return Timestamp.valueOf(toLocalDateTime(toZonedDateTime(localDateTime, sourceTimeZone)));
    }

    /**
     * Converts an `Instant` object to a `Timestamp` object.
     * An `Instant` represents a specific moment in time without time zone information.
     * This method first converts the `Instant` to a `LocalDateTime` in the system default time zone.
     * Then, it creates a `Timestamp` object with the resulting `LocalDateTime`.
     *
     * @param instant The `Instant` object representing a specific moment in time.
     * @return A {@code Timestamp} object representing the same moment in time in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(Instant instant) {
        return toTimeStamp(toLocalDateTime(instant));
    }

    /**
     * Converts an `Instant` object with a specified source time zone to a `Timestamp` object.
     * This method first converts the `Instant` to a `ZonedDateTime` using the provided source time zone.
     * Then, it converts the `ZonedDateTime` to a `LocalDateTime` (effectively dropping the time zone information).
     * Finally, it creates a `Timestamp` object with the resulting `LocalDateTime` in the system default time zone.
     *
     * @param instant        The `Instant` object representing a specific moment in time.
     * @param sourceTimeZone The String ID of the time zone associated with the `Instant` object (likely for reference).
     * @return A {@code Timestamp} object representing the same moment in time (without time zone information) in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(Instant instant, String sourceTimeZone) {
        return Timestamp.valueOf(toLocalDateTime(instant, sourceTimeZone));
    }

    /**
     * Converts a `ZonedDateTime` object to a `Timestamp` object.
     * This method first converts the `ZonedDateTime` to a `LocalDateTime` (effectively dropping the time zone information).
     * Then, it creates a `Timestamp` object with the resulting `LocalDateTime` in the system default time zone.
     *
     * @param zonedDateTime The `ZonedDateTime` object representing a date and time with a specific time zone.
     * @return A {@code Timestamp} object representing the same date and time (without time zone information) in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(ZonedDateTime zonedDateTime) {
        return toTimeStamp(toLocalDateTime(zonedDateTime));
    }

    /**
     * Converts a `ZonedDateTime` object with a specified source time zone to a `Timestamp` object.
     * This method first converts the `ZonedDateTime` to a `LocalDateTime` using the provided source time zone.
     * Then, it creates a `Timestamp` object with the resulting `LocalDateTime` in the system default time zone.
     *
     * @param zonedDateTime  The `ZonedDateTime` object representing a date and time with a specific time zone.
     * @param sourceTimeZone The String ID of the time zone associated with the `ZonedDateTime` object (likely for reference).
     * @return A {@code Timestamp} object representing the same date and time (without time zone information) in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(ZonedDateTime zonedDateTime, String sourceTimeZone) {
        return toTimeStamp(toLocalDateTime(zonedDateTime, sourceTimeZone));
    }

    /**
     * Converts an `OffsetDateTime` object to a `Timestamp` object.
     * This method first converts the `OffsetDateTime` to a `LocalDateTime` (effectively dropping the offset information).
     * Then, it creates a `Timestamp` object with the resulting `LocalDateTime` in the system default time zone.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time with an offset from UTC.
     * @return A {@code Timestamp} object representing the same date and time (without time zone information) in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(OffsetDateTime offsetDateTime) {
        return toTimeStamp(toLocalDateTime(offsetDateTime));
    }

    /**
     * Converts an `OffsetDateTime` object with a specified source time zone to a `Timestamp` object.
     * This method behaves the same as the single-argument `toTimeStamp(OffsetDateTime)` method.
     * The provided sourceTimeZone parameter is likely for reference and not used in the conversion.
     *
     * @param offsetDateTime The `OffsetDateTime` object representing a specific moment in time with an offset from UTC.
     * @param sourceTimeZone The String ID of the time zone associated with the `OffsetDateTime` object (likely for reference).
     * @return A {@code Timestamp} object representing the same date and time (without time zone information) in the system default time zone.
     * @author sabarees
     * @since V1.0.0
     */
    public static Timestamp toTimeStamp(OffsetDateTime offsetDateTime, String sourceTimeZone) {
        return Timestamp.valueOf(toLocalDateTime(offsetDateTime, sourceTimeZone));
    }

    /**
     * Converts a `java.sql.Date` or `java.util.Date` object to a `LocalDate` object.
     * This method first converts the `Date` object to an `Instant` object, effectively discarding any time zone information.
     * Then, it creates a `ZonedDateTime` object in the system default time zone using the `Instant`.
     * Finally, it extracts the `LocalDate` component from the `ZonedDateTime` object to represent the date only.
     *
     * @param date The `java.sql.Date` or `java.util.Date` object to be converted.
     * @return A {@code LocalDate} object representing the date part of the input `Date` object.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts a `java.util.Date` object to a `LocalDate` object.
     * This method first converts the `Date` object to an `Instant` object, effectively discarding any time zone information.
     * Then, it creates a `ZonedDateTime` object in the system default time zone using the `Instant`.
     * Finally, it extracts the `LocalDate` component from the `ZonedDateTime` object to represent the date only.
     *
     * @param date The `java.util.Date` object to be converted.
     * @return A {@code LocalDate} object representing the date part of the input `Date` object.
     * @author sabarees
     * @since V1.0.0
     */
    public static LocalDate toLocalDate(java.util.Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts a `LocalDate` object to a `java.sql.Date` object.
     * This method simply creates a `java.sql.Date` object using the `valueOf` method with the provided `LocalDate` object.
     * The resulting `java.sql.Date` object represents the date only without time information.
     *
     * @param localDate The `LocalDate` object to be converted.
     * @return A {@code java.sql.Date} object representing the same date as the input `LocalDate` object.
     * @author sabarees
     * @since V1.0.0
     */
    public static Date toSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    /**
     * Converts a `java.utilDate` object to a `java.sql.Date` object.
     * This method simply creates a `java.sql.Date` object using the `valueOf` method with the provided `LocalDate` object.
     * The resulting `java.sql.Date` object represents the date only without time information.
     *
     * @param date The `java.util.Date` object to be converted.
     * @return A {@code java.sql.Date} object representing the same date as the input `LocalDate` object.
     * @author sabarees
     * @since V1.0.0
     */
    public static Date toSqlDate(java.util.Date date) {
        return Date.valueOf(toLocalDate(date));
    }

    /**
     * Converts a `LocalDate` object to a `java.util.Date` object.
     * This method first sets the time of the `LocalDate` object to the beginning of the day (00:00:00) using `atStartOfDay()`.
     * Then, it converts the resulting `LocalDateTime` object to an `Instant` object.
     * Finally, it creates a `java.util.Date` object using the `from` method of the `java.util.Date` class with the `Instant` object.
     *
     * @param localDate The `LocalDate` object to be converted.
     * @return A {@code java.util.Date} object representing the same date as the input `LocalDate` object with time set to the beginning of the day.
     * @author sabarees
     * @since V1.0.0
     */
    public static java.util.Date toUtilDate(LocalDate localDate) {
        return java.util.Date.from(toInstant(localDate.atStartOfDay()));
    }

    /**
     * Converts a `java.sql.Date` object to a `java.util.Date` object.
     * This method first sets the time of the `LocalDate` object to the beginning of the day (00:00:00) using `atStartOfDay()`.
     * Then, it converts the resulting `LocalDateTime` object to an `Instant` object.
     * Finally, it creates a `java.util.Date` object using the `from` method of the `java.util.Date` class with the `Instant` object.
     *
     * @param date The `java.sql.Date` object to be converted.
     * @return A {@code java.util.Date} object representing the same date as the input `LocalDate` object with time set to the beginning of the day.
     * @author sabarees
     * @since V1.0.0
     */
    public static java.util.Date toUtilDate(Date date) {
        return java.util.Date.from(toInstant(toLocalDate(date).atStartOfDay()));
    }

}