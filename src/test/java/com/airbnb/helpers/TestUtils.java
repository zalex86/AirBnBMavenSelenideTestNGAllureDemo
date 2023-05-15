package com.airbnb.helpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.airbnb.tests.BaseSetup.LOGGER;

public class TestUtils {

    public static String getRandomString(int upperRange) {
        return RandomStringUtils.randomAlphabetic(upperRange);
    }

    public static String getRandomStringWithNumbers(int upperRange) {
        return RandomStringUtils.randomAlphanumeric(upperRange);
    }

    public static DateTime formatDateTime(String date, String pattern){
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return format.parseDateTime(date);
    }

    public static Date formatDate(String date, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }

    public static String formatDate(DateTime date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return dateTimeFormatter.print(date);
    }

    public static String formatDateWithTime(DateTime date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return dateTimeFormatter.print(date);
    }

    public static String formatDate(LocalDateTime date, String pattern) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static String getYesterdayDay(LocalDateTime date, String pattern) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static String formatFirstAndLastDaysWeekDate(LocalDateTime localDateTime, Integer firstDayOfWeek, Integer lastDayOfWeek) {
        LocalDateTime firstOfWeek = localDateTime.with(ChronoField.DAY_OF_WEEK, firstDayOfWeek).toLocalDate().atStartOfDay();
        LocalDateTime lastOfWeek = localDateTime.with(ChronoField.DAY_OF_WEEK, lastDayOfWeek).toLocalDate().atStartOfDay();
        String firstDayOfWeekDate = TestUtils.formatDate(firstOfWeek, "dd.MM.yyyy");
        String lastDayOfWeekDate = TestUtils.formatDate(lastOfWeek, "dd.MM.yyyy");
        return firstDayOfWeekDate + "-" + lastDayOfWeekDate;
    }

    public static String formatFirstAndLastMonthDate(LocalDateTime localDateTime, Integer lastDay) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(".MM.yyyy");
        return "01" +  localDateTime.format(formatter) + "-" + lastDay + localDateTime.format(formatter);
    }

    public static List<String> getDateOfWeekArray(LocalDateTime localDateTime, String patternDate) {
        List<String>  listOfDate = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            LocalDateTime dayOfWeek = localDateTime.with(ChronoField.DAY_OF_WEEK, i).toLocalDate().atStartOfDay();
            String formatedDayOfWeek = TestUtils.formatDate(dayOfWeek, patternDate);
            listOfDate.add(formatedDayOfWeek);
        }
        return listOfDate;
    }

    public static String formatDate(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatDateRu(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("ru"));
        return simpleDateFormat.format(date);
    }

    public static List<String> toLowerCase(List<String> list){
        return list.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public static void execScript(String pathToScript){
        LOGGER.log(Level.INFO, "Run command: bash " + pathToScript);
        ProcessBuilder processBuilder = new ProcessBuilder("bash", pathToScript);
        processBuilder.inheritIO();
        try {
            Process process = processBuilder.start();
            process.waitFor();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader readerErrors =
                    new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String output;
            while ((output = reader.readLine()) != null) {
                LOGGER.log(Level.INFO,"Output " + output);
            }
            String error;
            while ((error = readerErrors.readLine()) != null) {
                LOGGER.log(Level.INFO,"Error " + error);
            }
            LOGGER.log(Level.INFO,"Exited with error code : " + process.waitFor());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
