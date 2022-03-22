package ru.vsu.cs.suvorov_d_a;

public class Main {

    public static void main(String[] args) {
        Date dateNow = Date.now();
        System.out.println(dateNow);
        dateNow.addDate(Day.WEEK, +5);
        dateNow.addDate(Day.DAY, +5);
        dateNow.addDate(Day.MONTH, -1);
        System.out.println(dateNow);
        dateNow.setFormat("yy/MMMM/dd");
        System.out.println(dateNow);
        dateNow.addDate(Day.YEAR, -2);
        dateNow.setFormat("d-MMM-y");
        System.out.println(dateNow);

        Date dateStart = new Date();
        System.out.println(dateStart);

        Date myBirthday = new Date(2003, 5, 27);
        Date today = new Date(2022, 3, 15);

        System.out.println("My Birthday: " + myBirthday);
        System.out.println("Today: " + today);

        if (myBirthday.precedes(today)) {
            System.out.println(myBirthday + " precedes " + today);
        }
        else {
            System.out.println(today + " precedes " + myBirthday);
        }

        testingExample();
    }

    private static void testingExample() {
        System.out.println("Example: ");
        DateTime dateTime = new DateTime(2022, 3, 5, 12, 5, 39);

        System.out.println(dateTime.addDate(Day.YEAR, 4).addDate(Day.DAY, -5).addHours(5).toString());

    }
}