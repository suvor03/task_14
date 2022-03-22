package ru.vsu.cs.suvorov_d_a;

public enum Month {
    JANUARY("January", 31), FEBRUARY("February", 28), MARCH("March", 31), APRIL("April", 30),
    MAY("May", 31), JUNE("June", 30), JULY("July", 31), AUGUST("August", 31), SEPTEMBER("September", 30),
    OCTOBER("October", 31), NOVEMBER("November", 30), DECEMBER("December", 31);

    private int days;
    private String name;

    public int getDays() {
        return days;
    }

    Month(String name, int days) {
        this.days = days;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }
}
