package ru.vsu.cs.suvorov_d_a;

import java.time.LocalDate;

public class Date {
    private DateFormat format = new DateFormat("dd.MM.yyyy");

    private int day;
    private int month;
    private int year;

    Date(int year, int month, int day) {
        parseDate(year, month, day);
    }

    public Date() {
        this(1, 1, 1);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean equals(Date otherDate) {
        return (day == otherDate.day
                && getMonth() == otherDate.getMonth()
                && year == otherDate.year);
    }

    public boolean precedes(Date otherDate) {
        return ((year < otherDate.year)
                || (year == otherDate.year && getMonth() < otherDate.getMonth())
                || (year == otherDate.year && getMonth() == otherDate.getMonth() && day < otherDate.day));
    }

    public static Date now() {
        LocalDate date = LocalDate.now();
        return new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    public void setFormat(String pattern) {
        this.format = new DateFormat(pattern);
    }

    private void checkArguments(int year, int month, int day) {
        if (year <= 0) {
            throw new IllegalArgumentException("incorrect argument [year]...");
        } else if (month <= 0 || month > Month.values().length) {
            throw new IllegalArgumentException("incorrect argument [month]...");
        } else {
            int daysToMonth = Month.values()[month - 1].getDays();
            if (isLeapYear(year) && month == 2) {
                daysToMonth++;
            }
            if (day <= 0 || day > daysToMonth) {
                throw new IllegalArgumentException("incorrect argument [day]...");
            }
        }
    }

    private String parseDaysToString() {
        int year = this.day / Day.YEAR.getDays() + 1;
        year -= countLeapYears(year) / Day.YEAR.getDays();
        int daysDiv = this.day - ((year - 1) * Day.YEAR.getDays() + countLeapYears(year - 1));
        int searchDay = 0;
        int[] daysMonths = new int[Month.values().length];
        for (int index = 0; index < daysMonths.length; index++) {
            daysMonths[index] = Month.values()[index].getDays();
            if (index == 1 && isLeapYear(year)) {
                daysMonths[index]++;
            }
        }
        int monthCount = 1;
        for (int days : daysMonths) {
            if (searchDay + days <= daysDiv) {
                searchDay += days;
                monthCount++;
            } else {
                break;
            }
        }
        int day = daysDiv - searchDay + 1;
        return this.format.parse(year, monthCount, day);
    }

    private static boolean isLeapYear(int year) {
        return year > 0 && year % 4 == 0 && (year % 100 != 0 || (year % 100 == 0 && year % 400 == 0));
    }

    private int countLeapYears(int end) {
        int count = 0;
        for (int year = 1; year <= end; year++) {
            if (isLeapYear(year)) {
                count++;
            }
        }
        return count;
    }

    private void parseDate(int year, int month, int day) {
        checkArguments(year, month, day);
        this.day += (year - 1) * Day.YEAR.getDays() + countLeapYears(year - 1);
        for (int count = 0; count < month - 1; count++) {
            this.day += Month.values()[count].getDays();
        }
        if (isLeapYear(year) && month - 1 >= 2) {
            this.day++;
        }
        this.day += --day * Day.DAY.getDays();
    }

    @Override
    public String toString() {
        return parseDaysToString();
    }

    public void addDate(Day type, int count) {
        this.day += type.getDays() * count;
    }
}