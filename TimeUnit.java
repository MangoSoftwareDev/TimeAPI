package com.matthiaantje.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Matthias on 4/02/2017.
 */
public enum  TimeUnit {

    SEC("second", 1L, 's'),  MIN("minute", 60L, 'm'),  HOUR("hour", 3600L, 'h'),  DAY("day", 86400L, 'd'),  WEEK("week", 604800L, 'w');

    private final String name;
    private final long ms;
    private final char unit;
    private static final Map<Character, Long> convertion;
    private static final TimeUnit[] order;
    private static final Pattern isLong;

    static
    {
        convertion = new HashMap();
        order = new TimeUnit[] { WEEK, DAY, HOUR, MIN, SEC };
        isLong = Pattern.compile("[0-9]+");
        TimeUnit[] arrayOfTimeUnit;
        int j = (arrayOfTimeUnit = values()).length;
        for (int i = 0; i < j; i++)
        {
            TimeUnit unit = arrayOfTimeUnit[i];
            convertion.put(Character.valueOf(unit.unit), Long.valueOf(unit.ms));
        }
    }

    private TimeUnit(String name, long ms, char unit)
    {
        this.name = name;
        this.ms = ms;
        this.unit = unit;
    }

    public String addUnit(int x)
    {
        String r = this.name;
        if (x > 1) {
            r = r + "s";
        }
        return x + " " + r;
    }

    private static long convert(char c)
    {
        if (convertion.containsKey(Character.valueOf(c))) {
            return ((Long)convertion.get(Character.valueOf(c))).longValue();
        }
        return 0L;
    }

    public static String toString(long time)
    {
        StringBuilder sb = new StringBuilder();
        TimeUnit[] arrayOfTimeUnit;
        int j = (arrayOfTimeUnit = order).length;
        for (int i = 0; i < j; i++)
        {
            TimeUnit unit = arrayOfTimeUnit[i];
            if (time >= unit.ms)
            {
                int t = (int)Math.floor(time / unit.ms);
                sb.append(unit.addUnit(t)).append(" ");
                time -= t * unit.ms;
            }
        }
        return sb.toString().trim();
    }

    public static long toLong(String s)
    {
        long fromString = 0L;
        String[] arrayOfString;
        int j = (arrayOfString = s.split(" ")).length;
        for (int i = 0; i < j; i++)
        {
            String t = arrayOfString[i];
            char c = t.charAt(t.length() - 1);
            t = t.substring(0, t.length() - 1);
            if (isLong.matcher(t).matches()) {
                fromString += Long.parseLong(t) * convert(c);
            }
        }
        return fromString;
    }

}
