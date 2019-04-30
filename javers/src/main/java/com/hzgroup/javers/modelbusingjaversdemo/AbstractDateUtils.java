package com.hzgroup.javers.modelbusingjaversdemo;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用日期操作
 */
public abstract class AbstractDateUtils {

    /**
     * AbstractDateUtils.DIFF_IN_SECONDS.getTimeDiff(toDate('2015-11-10 01:30:00'), toDate('2015-11-10 01:30:30')) = 30
     * AbstractDateUtils.DIFF_IN_SECONDS.getTimeDiff(toDate('2015-11-10 01:30:30'), toDate('2015-11-10 01:30:00')) = -30
     */
    public static final AbstractTimeDiff DIFF_IN_SECONDS = new TimeDiffInSeconds();

    /**
     * AbstractDateUtils.DIFF_IN_HOURS.getTimeDiff(toDate('2015-11-10 00:30:00'), toDate('2015-11-10 00:31:30')) = 0
     * AbstractDateUtils.DIFF_IN_HOURS.getTimeDiff(toDate('2015-11-10 00:30:00'), toDate('2015-11-10 01:31:30')) = 1
     * AbstractDateUtils.DIFF_IN_HOURS.getTimeDiff(toDate('2015-11-09 23:30:30'), toDate('2015-11-10 00:30:00')) = 1
     */
    public static final AbstractTimeDiff DIFF_IN_HOURS = new TimeDiffInHours();

    /**
     * AbstractDateUtils.DIFF_IN_DAYS.getTimeDiff(toDate('2015-11-10 00:30:00'), toDate('2015-11-10 00:31:30')) = 0
     * AbstractDateUtils.DIFF_IN_DAYS.getTimeDiff(toDate('2015-11-09 23:30:30'), toDate('2015-11-10 00:30:00')) = 0
     * AbstractDateUtils.DIFF_IN_DAYS.getTimeDiff(toDate('2015-11-09 23:30:30'), toDate('2015-11-10 23:30:00')) = 1
     */
    public static final AbstractTimeDiff DIFF_IN_DAYS = new TimeDiffInDays();

    public static final TimeFormatter MONTH_DAY_TIME_FORMATTER = new DefaultTimeFormatter("MM-dd");

    public static final TimeFormatter HOUR_MINUTE_TIME_FORMATTER = new DefaultTimeFormatter("HH:mm");

    public static final TimeFormatter HOUR_MINUTE_SECOND_TIME_FORMATTER = new DefaultTimeFormatter("HH:mm:ss");

    public static final TimeFormatter YEAR_MONTH_DAY_TIME_FORMATTER = new DefaultTimeFormatter("yyyy-MM-dd");

    public static final TimeFormatter YEAR_MONTH_DAY_HOUR_MINUTE_TIME_FORMATTER = new DefaultTimeFormatter("yyyy-MM-dd HH:mm");

    public static final TimeFormatter FULL_TIME_FORMATTER = new DefaultTimeFormatter("yyyy-MM-dd HH:mm:ss");

    public static final TimeParser MONTH_DAY_TIME_PARSER = new DefaultTimeParser("MM-dd");

    public static final TimeParser HOUR_MINUTE_TIME_PARSER = new DefaultTimeParser("HH:mm");

    public static final TimeParser HOUR_MINUTE_SECOND_TIME_PARSER = new DefaultTimeParser("HH:mm:ss");

    public static final TimeParser YEAR_MONTH_DAY_TIME_PARSER = new DefaultTimeParser("yyyy-MM-dd");

    public static final TimeParser YEAR_MONTH_DAY_HOUR_MINUTE_TIME_PARSER = new DefaultTimeParser("yyyy-MM-dd HH:mm");

    public static final TimeParser FULL_TIME_PARSER = new DefaultTimeParser("yyyy-MM-dd HH:mm:ss");

    private static final String[] ZH_WEEK_LIST = {"日", "一", "二", "三", "四", "五", "六"};

    /**
     * 获取时间是星期几
     *
     * @return
     */
    public static String getXingQiJiOfWeek(Date date){
        return "星期"+getZhDayOfWeek(date);
    }

    /**
     * 获取时间是周几
     */
    public static String getZhouJiOfWeek(Date date){
        return "周"+getZhDayOfWeek(date);
    }

    private static String getZhDayOfWeek(Date date){
        Assert.notNull(date,"date cant be null");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return ZH_WEEK_LIST[dayOfWeek];
    }

    /**
     * 获取给定时间的0点时刻
     * 例如:给定的时间是2017-05-04 12:00:00, 那当前方法返回的是2017-05-04 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartPointOfDate(Date date) {
        String format = getTimeFormatter("yyyyMMdd").format(date);
        return getTimeParser("yyyyMMdd").parse(format);
    }


    /**
     * 检查时间是否晚于某个时间
     * <p/>
     * AbstractDateUtils.isAfter(toDate('2015-11-30'), toDate('2015-11-29')) = true
     * <p/>
     * Same As src.after(dest)
     *
     * @param src  比较时间
     * @param dest 待比较时间
     * @return 如果src时间晚于dest时间, 则返回true
     */
    public static boolean isAfter(Date src, Date dest) {
        Assert.notNull(src, "src cant be null");
        Assert.notNull(dest, "src cant be null");

        return src.after(dest);
    }

    /**
     * 获取当前时间下一天
     *
     * @return
     */
    public static Date nextDay() {
        return nextDay(new Date());
    }

    /**
     * 获取给定时间的下一天时间
     *
     * @param date
     * @return
     */
    public static Date nextDay(Date date) {
        return getDeltaDateByDays(date, 1);
    }

    /**
     * 获取给定时间seconds以后的时间点,seconds可以是负数
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date getDeltaDateInSeconds(Date date, int seconds) {
        Assert.notNull(date, "given date cant be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);

        return calendar.getTime();
    }

    /**
     * 获取给定时间minute以后的时间点,minute可以是负数
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getDeltaDateInMinute(Date date, int minute) {
        Assert.notNull(date, "given date cant be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取给定时间hour以后的时间点,hour可以是负数
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date getDeltaDateInHour(Date date, int hour) {
        Assert.notNull(date, "given date cant be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 获取给定时间deltaDays以后的时间点,deltaDays可以是负数
     * <p/>
     * AbstractDateUtils.getDeltaDateByDays(toDate('2015-11-29 12:00:00'), 1) = toDate('2015-11-30 12:00:00')
     * AbstractDateUtils.getDeltaDateByDays(toDate('2015-11-29 12:00:00'), -1) = toDate('2015-11-28 12:00:00')
     *
     * @param date
     * @param deltaDays
     * @return
     */
    public static Date getDeltaDateByDays(Date date, int deltaDays) {
        Assert.notNull(date, "given date cant be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_YEAR, deltaDays);
        return calendar.getTime();
    }

    /**
     * 获取给定时间所处日期的起始时刻
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        String dateString = YEAR_MONTH_DAY_TIME_FORMATTER.format(date);
        return YEAR_MONTH_DAY_TIME_PARSER.parse(dateString);
    }

    /**
     * 判断两个时间是否属于同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return YEAR_MONTH_DAY_TIME_FORMATTER.format(date1).equals(YEAR_MONTH_DAY_TIME_FORMATTER.format(date2));
    }

    /**
     * 判断两个时间段是否有交集
     *
     * 分为三种情况:
     * 1. 起始时间在另一个时间段内
     * 2. 结束时间在另一个时间段内
     * 3. 一个时间段包含另一个时间段
     *
     * @param startTime1
     * @param endTime1
     * @param startTime2
     * @param endTime2
     * @return
     */
    public static boolean isTimeSpansOverlap(Date startTime1, Date endTime1, Date startTime2, Date endTime2) {
        return isStartTimeInTimeSpan(startTime1, startTime2, endTime2)
                || isEndTimeInTimeSpan(endTime1, startTime2, endTime2)
                || isTimeSpan1InTimeSpan2(startTime1, endTime1, startTime2, endTime2);

    }

    private static boolean isStartTimeInTimeSpan(Date startTime1, Date startTime2, Date endTime2) {
        return !startTime1.before(startTime2) && startTime1.before(endTime2);
    }

    private static boolean isEndTimeInTimeSpan(Date endTime1, Date startTime2, Date endTime2) {
        return endTime1.after(startTime2) && !endTime1.after(endTime2);
    }

    private static boolean isTimeSpan1InTimeSpan2(Date startTime1, Date endTime1, Date startTime2, Date endTime2) {
        return !startTime1.after(startTime2) && !endTime1.before(endTime2);
    }

    /**
     * 根据日期格式来获取TimeFormatter对象
     *
     * @param simpleFormat 例如:yyyy-MM-dd, yy-MM-dd
     * @return
     */
    public static TimeFormatter getTimeFormatter(String simpleFormat) {
        return new DefaultTimeFormatter(simpleFormat);
    }

    /**
     * 根据日期格式来获取TimeParser对象
     *
     * @param simpleFormat 例如:yyyy-MM-dd, yy-MM-dd
     * @return
     */
    public static TimeParser getTimeParser(String simpleFormat) {
        return new DefaultTimeParser(simpleFormat);
    }

    /////////////// TimeFormatter Interface & Implementation
    public interface TimeFormatter {

        String format(Date date);

    }

    static class DefaultTimeFormatter implements TimeFormatter {

        private String format;

        public DefaultTimeFormatter(String format) {
            this.format = format;
        }

        @Override
        public String format(Date date) {
            try {
                return getSimpleDateFormat(format).format(date);
            } catch (Exception e) {
                return StringUtils.EMPTY;
            }
        }
    }

    ////////////// TimeParser Interface & Implementation
    public interface TimeParser {

        Date parse(String str);

    }

    static class DefaultTimeParser implements TimeParser {

        private String format;

        public DefaultTimeParser(String format) {
            this.format = format;
        }

        @Override
        public Date parse(String str) {
            try {
                return getSimpleDateFormat(format).parse(str);
            } catch (Exception e) {
                return null;
            }
        }
    }

    private static SimpleDateFormat getSimpleDateFormat(String dateFormat) {
        return new SimpleDateFormat(dateFormat);
    }

    /////////// TimeDiff Interface & Implementation
    public interface AbstractTimeDiff {

        int getTimeDiff(Date start, Date end);

    }

    static class TimeDiffInSeconds implements AbstractTimeDiff {

        @Override
        public int getTimeDiff(Date start, Date end) {
            DateTime startTime = new DateTime(start);
            DateTime endTime = new DateTime(end);
            Period period = new Period(startTime, endTime, PeriodType.seconds());
            return period.getSeconds();
        }
    }

    static class TimeDiffInHours implements AbstractTimeDiff {

        @Override
        public int getTimeDiff(Date start, Date end) {
            DateTime startTime = new DateTime(start);
            DateTime endTime = new DateTime(end);
            Period period = new Period(startTime, endTime, PeriodType.hours());
            return period.getHours();
        }
    }

    static class TimeDiffInDays implements AbstractTimeDiff {

        @Override
        public int getTimeDiff(Date start, Date end) {
            DateTime startTime = new DateTime(start);
            DateTime endTime = new DateTime(end);
            Period period = new Period(startTime, endTime, PeriodType.days());
            return period.getDays();
        }
    }

}
