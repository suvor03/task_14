package ru.vsu.cs.suvorov_d_a;

public enum Day {
    DAY(1), WEEK(7), MONTH(31), YEAR(365);

    private int days;

    public int getDays() {
        return days;
    }

    Day(int days) {
        this.days = days;
    }
}
