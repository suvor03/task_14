package ru.vsu.cs.suvorov_d_a;

public class DateTime {
    private Date date = new Date();
    private Time time = new Time();

    public DateTime(int year, int month, int day, int hour, int minute, int second) {

    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonth();
    }

    public int getDays() {
        return date.getDay();
    }

    public int getHours() {
        return time.getHours();
    }

    public int getMinutes() {
        return time.getMinutes();
    }

    public int getSeconds() {
        return time.getSeconds();
    }


    public DateTime addDate(Day type, int count) {
        date.addDate(type, count);

        return this;
    }

    public DateTime addHours(int hour) {
        time.addHours(hour);
        int justHours = time.getHours();

        while (justHours > 24) {
            date.addDate(Day.DAY, +1);
            time.addHours(-24);
            justHours -= 24;
        }

        return this;
    }

    public DateTime addMinutes(int minute) {
        time.addMinutes(minute);

        return this;
    }

    public DateTime addSeconds(int second) {
        time.addSeconds(second);

        return this;
    }

    public String toString(DateFormat format) {
        return date.toString() + "   " + time.toString();
    }

    public String toString() {
        return date.toString() + "   " + time.toString();
    }
}
