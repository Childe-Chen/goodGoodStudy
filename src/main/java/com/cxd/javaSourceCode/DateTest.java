package com.cxd.javaSourceCode;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * desc
 *
 * @author childe
 * @date 2017/10/27 15:18
 **/
public class DateTest {

    // 第1年1月1日 (格里高利历)
    public static final int     JAN_1_1_JULIAN_DAY               = 1721426;

    // 1970年1月1日 (格里高利历)
    public static final int     EPOCH_JULIAN_DAY                 = 2440588;

    public static final int     EPOCH_YEAR                       = 1970;

    // 一周、一天、一小时、一分钟、一秒，换算成毫秒的全局变量。
    public static final int     ONE_SECOND                       = 1000;

    public static final int     ONE_MINUTE                       = 60 * ONE_SECOND;

    public static final int     ONE_HOUR                         = 60 * ONE_MINUTE;

    public static final long    ONE_DAY                          = 24 * ONE_HOUR;

    public static final long    ONE_WEEK                         = 7 * ONE_DAY;

    public static void main(String[] args) {
        Date curr = new Date();
//        System.out.println(dateToString(add(curr,Calendar.MONTH,3)));

        String billingMonth = "2017-10";
        String billingDay = billingMonth + "-25";
        Date billingDate = stringToDate(billingDay,new SimpleDateFormat("yyyy-MM-dd"));
        int diffDays = daysBetween(billingDate,curr);
        System.out.println(billingDay + "~" + dateToString(curr) + " : " + diffDays);

        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(1512576000000L)));
    }

    public static Date stringToDate(String isoDateString, SimpleDateFormat simpleDateFormat) {
        if (StringUtils.isBlank(isoDateString)) {
            // throw new IllegalArgumentException("日期字符串不能为空！");
            return null;
        }
        try {
            return simpleDateFormat.parse(isoDateString);
        } catch (ParseException e) {
            // throw new RuntimeException(e);
        }
        return null;
    }

    public static Date add(Date date, int field, int amount) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getDefault());
        cal.setTime(date);
        cal.add(field, amount);

        return cal.getTime();
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static int daysBetween(Date early, Date late) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);
        return dateToJulianDay(c2.getTime()) - dateToJulianDay(c1.getTime());
    }

    public static int dateToJulianDay(Date date) {
        return millisToJulianDay(date.getTime());
    }

    /**
     * 将毫秒时间戳转换成格里高利历的日数。日数是只从格里高利历第1年1月1日算起的日数。
     *
     * @param millis 给定的毫秒时间戳
     * @return 格里高利历的日数
     */
    public static int millisToJulianDay(long millis) {
        return EPOCH_JULIAN_DAY - JAN_1_1_JULIAN_DAY + (int) (millis / ONE_DAY);
    }
}
