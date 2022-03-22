package ru.vsu.cs.suvorov_d_a;

public class Time {
    private int hour, minute, second;

    public Time(int hour, int minute, int second) {
        setHours(hour);
        setMinutes(minute);
        setSeconds(second);
    }

    public Time(int hour, int minute) {
        this(hour, minute, 0);
    }

    public Time() {
        this(0, 0, 0);
    }

    public void setHours(int hour) {
        if (hour < 0 || hour > 23)
            hour = 0;
        else
            hour = hour;
    }

    public void setMinutes(int minute) {
        if (minute < 0 || minute > 59)
            minute = 0;
        else
            minute = minute;
    }

    public void setSeconds(int second) {
        if (second < 0 || second > 59)
            second = 0;
        else
            second = second;
    }

    public int getHours() {
        return second / (60 * 60);
    }

    public int getMinutes() {
        return (second - (getHours()) * 60 * 60) / 60;
    }

    public int getSeconds() {
        return second - (getHours() * 60 * 60) - (getMinutes() * 60);
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public void addSeconds(int second) {
        second += second;
        minute += second / 60;
        second = second % 60;
        hour += minute / 60;
        minute = minute % 60;
        hour = hour % 24;
    }
    public void addMinutes(int minute) {
        addSeconds(minute * 60);
    }
    public void addHours(int hour) {
        addSeconds(hour * 60 * 60);
    }
}