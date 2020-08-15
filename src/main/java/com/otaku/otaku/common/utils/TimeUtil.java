package com.otaku.otaku.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间Util
 * <p>
 * Created by tujietg on Mar 29, 2019
 */
public class TimeUtil {

    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    // 判断是否是今天
    public static boolean isToday(Date datetime) {
        String time = getStringTimeByDate(datetime);
        String data = time.split(" ")[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());// 当前时间
        if (data.equals(now)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否是今天
    public static boolean isToday(String datetime) {
        String data = datetime.split(" ")[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());// 当前时间
        if (data.equals(now)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是不是昨天
     *
     * @param datetime
     * @return
     */
    public static boolean isyesToday(Date datetime) {
        String time = getStringTimeByDate(datetime);
        // 订单的日期
        String data = time.split(" ")[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long dateLong = null;
        try {
            dateLong = sdf.parse(data).getTime();
        } catch (ParseException e) {
            logger.info(e.toString());
        }
        dateLong = dateLong + 1000 * 60 * 60 * 24;
        String lastDay = sdf.format(dateLong);
        String now = sdf.format(new Date());// 当前时间
        if (lastDay.equals(now)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否是昨天
    public static boolean isyesToday(String datetime) {
        String data = datetime.split(" ")[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long dateLong = null;
        try {
            dateLong = sdf.parse(data).getTime();
        } catch (ParseException e) {
            logger.info(e.toString());
        }
        dateLong = dateLong + 1000 * 60 * 60 * 24;
        String lastDay = sdf.format(dateLong);
        String now = sdf.format(new Date());// 当前时间
        if (lastDay.equals(now)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否是这个月的日期
    public static boolean isThisMonth(Date datetime) {
        String time = getStringTimeByDate(datetime);
        String data = time.split("-")[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());// 当前时间
        String newData = now.split("-")[1];
        if (data.equals(newData)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否是这个月的日期(传入String)
    public static boolean isThisMonth(String time) {
        String data = time.split("-")[0] + time.split("-")[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());// 当前时间
        String newData = now.split("-")[0] + now.split("-")[1];
        if (data.equals(newData)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否是上个月的日期(传入String)
    public static boolean isLastMonth(Date datetime) {
        // 上个月
        String beforeMonth = getLastMonth();
        String beforeMonthEnd = beforeMonth.split("-")[0] + beforeMonth.split("-")[1];

        // 传入时间
        String time = getStringTimeByDate(datetime);
        String datetimeEnd = time.split("-")[0] + time.split("-")[1];

        if (beforeMonthEnd.equals(datetimeEnd)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否是上个月的日期(传入String)
    public static boolean isNextMonth(String datetime) {
        // 上个月
        String beforeMonth = getNextMonth();
        String beforeMonthEnd = beforeMonth.split("-")[0] + beforeMonth.split("-")[1];

        // 传入时间
        String datetimeEnd = datetime.split("-")[0] + datetime.split("-")[1];

        if (beforeMonthEnd.equals(datetimeEnd)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否是上个月的日期(传入String)
    public static boolean isLastMonth(String datetime) {
        // 上个月
        String beforeMonth = getLastMonth();
        String beforeMonthEnd = beforeMonth.split("-")[0] + beforeMonth.split("-")[1];

        // 传入时间
        String datetimeEnd = datetime.split("-")[0] + datetime.split("-")[1];

        if (beforeMonthEnd.equals(datetimeEnd)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前日期是否大于当前月 24号
     *
     * @return
     */
    public static boolean isGreaterThanParam(int day) {
        Calendar now = Calendar.getInstance();
        Calendar twFive = Calendar.getInstance();
        // 设置当前月的25号
        twFive.set(Calendar.DAY_OF_MONTH, day);
        if (now.getTimeInMillis() > twFive.getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    // 根据String获取data
    public static Date getDateByString(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    // String解析成Long
    public static Long getLongByString(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long dateLong = null;
        try {
            dateLong = sdf.parse(datetime).getTime();
        } catch (ParseException e) {
            logger.info(e.toString());
        }
        return dateLong;
    }

    public static String getStringTimeByDate(Date datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(datetime);
    }

    public static String getStringByLong(Long currentLong) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(currentLong);
    }

    // date解析成String
    public static String getStringDateByDate(Date datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(datetime);
    }

    /**
     * 传入日期是否在现在时间减去多少天之前
     *
     * @param date 传入的日期
     * @param day  几天
     * @return
     */
    public static boolean isBeforeNowDelSomeDay(Date date, int day) {
        // 获取多少天之前的日期
        Date beforeDate = getBeforeDateAndTimeByParam(day);
        if (date.getTime() < beforeDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    // 获取多少天之前的日期
    public static String getBeforeDayByParam(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(cal.getTime());
        return dateStr;
    }

    /**
     * 获取多长分钟之后的时间
     *
     * @param time
     * @param minute
     * @return
     */
    public static String getAfterMinuteByParam(Date time, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE, minute);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(cal.getTime());
        return dateStr;
    }

    // 获取多少天之后的数据
    public static String getAfterDayByParam(Date time, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(cal.getTime());
        return dateStr;
    }

    // 获取多少天之前的日期
    public static Date getBeforeDateAndTimeByParam(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -day);
        return cal.getTime();
    }

    // 获取一月前的日期
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(cal.getTime());
        return dateStr;
    }

    // 获取一月之后的日期
    public static String getNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(cal.getTime());
        return dateStr;
    }

    // 增加12小时
    public static String getAdd12Hours(String currentTime) {
        long currentTimeLong = getLongByString(currentTime).longValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lastTimeLast = currentTimeLong + (1000 * 60 * 60 * 12);
        String lastTime = sdf.format(lastTimeLast);
        return lastTime;
    }

    // 增加60分钟
    public static String getAddSixtyMInute(String currentTime) {
        long currentTimeLong = getLongByString(currentTime).longValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lastTimeLast = currentTimeLong + (1000 * 60 * 60);
        String lastTime = sdf.format(lastTimeLast);
        return lastTime;
    }

    // 当前时间向前20分钟
    public static String getSubTwentyMInute() {
        long currentTimeLong = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lastTimeLast = currentTimeLong - (1000 * 60 * 20);
        String lastTime = sdf.format(lastTimeLast);
        return lastTime;
    }

    public static void main(String[] args) {
        System.out.println(getSubTwentyMInute());
    }

    // 当前时间向前20分钟
    public static String getJdTimeAndSubSomeMInute(int someMinute) {
        long currentTimeLong = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        long lastTimeLast = currentTimeLong - (1000 * 60 * someMinute);
        String jdData = sdf.format(new Date(lastTimeLast));
        return jdData;
    }

    // 获取时间 格式（yyyy-MM-dd HH:mm:ss）
    public static String getDateAndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String date = sdf.format(currentDate);
        return date;
    }

    // 获取时间 格式（yyyy-MM-dd HH:mm:ss）
    public static Date getDateAndTimeLastday() {
        long time = new Date().getTime();
        long lastDay = time - (1000 * 60 * 60 * 24);
        Date currentDate = new Date(lastDay);
        return currentDate;
    }

    // 获取前一天的日期
    public static String getTomDate() {
        long currentTimeLong = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lastTimeLast = currentTimeLong + (1000 * 60 * 60 * 24);
        String lastTime = sdf.format(lastTimeLast);
        return lastTime;
    }

    // 获取前一天的日期
    public static String getNowDate() {
        long currentTimeLong = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lastTimeLast = currentTimeLong;
        String lastTime = sdf.format(lastTimeLast);
        return lastTime;
    }

    public static String getDateSt(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }


    public static String getChinaTime() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }
}
