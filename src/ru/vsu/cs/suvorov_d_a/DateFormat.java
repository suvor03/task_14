package ru.vsu.cs.suvorov_d_a;

public class DateFormat {
    private String[] patterns;
    private char prefix;

    DateFormat(String pattern) {
        getFormat(pattern);
    }

    private void getFormat(String pattern) {
        String[] formats;
        char prefix;
        if (pattern != null) {
            pattern = pattern.trim();
            prefix = getPrefix(pattern);
            if (prefix != 0) {
                StringBuilder sb = new StringBuilder();
                for (int index = 0; index < pattern.length(); index++) {
                    if (pattern.charAt(index) != prefix) {
                        sb.append(pattern.charAt(index));
                    } else {
                        sb.append("\n");
                    }
                }
                formats = sb.toString().split("\n");
                for (String format : formats) {
                    if (!checkSymbols(format)) {
                        throw new IllegalArgumentException("incorrect pattern format...");
                    }
                }
            } else {
                if (checkSymbols(pattern)) {
                    formats = new String[]{pattern};
                } else {
                    throw new IllegalArgumentException("incorrect pattern format...");
                }
            }
        } else {
            throw new IllegalArgumentException("incorrect pattern format...");
        }
        this.prefix = prefix;
        this.patterns = formats;
    }

    private boolean checkSymbols(String symbols) {
        return symbols.chars().distinct().count() == 1;
    }

    private char getPrefix(String pattern) {
        char prefix = 0;
        pattern = pattern.trim().replace("y", "").replaceAll("M", "").
                replaceAll("d", "");
        if (pattern.length() > 0 && checkSymbols(pattern)) {
            prefix = pattern.charAt(0);
        }
        return prefix;
    }


    private String getDay(String pattern, int day) {
        String result;
        if (pattern.equals("d")) {
            result = String.format("%d", day);
        } else {
            result = String.format("%s%d", day < 10 ? "0" : "", day);
        }
        return result;
    }

    private String getMonth(String pattern, int month) {
        String result;
        switch (pattern.length()) {
            case 1:
                result = String.format("%d", month);
                break;
            case 2:
                result = String.format("%s%d", month < 10 ? "0" : "", month);
                break;
            case 3:
                result = String.format("%s", Month.values()[month - 1].toString().substring(0, 3).toLowerCase());
                break;
            default:
                result = String.format("%s", Month.values()[month - 1].toString());
        }
        return result;
    }

    private String correctYear(int year) {
        StringBuilder sb = new StringBuilder();
        for (int count = 0; count < 4; count++) {
            sb.append(year % 10);
            year /= 10;
        }
        return sb.reverse().toString();
    }

    private String getYear(String pattern, int year) {
        String result;
        if (pattern.length() == 2) {
            result = String.format("%s", correctYear(year).substring(2, 4));
        } else {
            result = String.format("%s", correctYear(year));
        }
        return result;
    }

    private void checkArguments(String arg) {
        if (arg.charAt(0) == 'd' && arg.length() > 2) {
            throw new IllegalArgumentException("incorrect format day...");
        } else if (arg.charAt(0) == 'M' && arg.length() > 4) {
            throw new IllegalArgumentException("incorrect format month...");
        } else if (arg.charAt(0) == 'y' && (arg.length() == 3 || arg.length() > 4)) {
            throw new IllegalArgumentException("incorrect format year...");
        }

    }

    public String parse(int year, int month, int day) {
        StringBuilder sb = new StringBuilder();
        for (String arg : this.patterns) {
            checkArguments(arg);
            if (arg.charAt(0) == 'd') {
                sb.append(getDay(arg, day)).append(this.prefix);
            } else if (arg.charAt(0) == 'y') {
                sb.append(getYear(arg, year)).append(this.prefix);
            } else {
                sb.append(getMonth(arg, month)).append(this.prefix);
            }
        }
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }
}
