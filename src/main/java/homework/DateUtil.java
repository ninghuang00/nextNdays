package homework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by huangning on 2017/11/2.
 */
public class DateUtil {
    Logger logger = Logger.getLogger("hn");

    //得到n天之后的日期
    public MyDate nextNdays(MyDate myDate, long n) {
        if (myDate != null) {
            if (n >= 0) {

                while (n-- > 0) {
                    myDate = nextDay(myDate);
                }
            } else {
                while (n++ < 0) {
                    myDate = lastDay(myDate);
                }
            }
        }
        return myDate;
    }

    //得到下一天
    public MyDate nextDay(MyDate date) {
        //判断输入日期是否合法
        if (!isDateLegal(date)) {
            System.out.println("the date you input is illegal");
            return null;
        }
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        //得到当前月份的天数
        int dayOfMonth = getDayOfMonth(year, month);
        //判断日期是否进位
        if (day + 1 <= dayOfMonth) {
            day += 1;
        } else {
            day = 1;
            //判断月份是否进位
            if (month + 1 <= 12) {
                month += 1;
            } else {
                month = 1;
                year += 1;
            }
        }
        return new MyDate(year, month, day);
    }

    //得到前一天
    public MyDate lastDay(MyDate date) {
        //判断输入日期是否合法
        if (!isDateLegal(date)) {
            logger.info("the format of date is illegal");
            return null;
        }
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        //计算日子
        if (day - 1 > 0) {
            --day;
        } else {
            //计算月份
            if (month - 1 > 0) {
                --month;
            } else {
                //计算年份
                if (year - 1 <= 0) {
                    System.out.println("去不了公元前");
                    return null;
                }
                --year;
                month = 12;
            }
            day = getDayOfMonth(year, month);
        }

        return new MyDate(year, month, day);
    }

    //判断日期是否合法
    public boolean isDateLegal(MyDate thisDay) {
        int year = thisDay.getYear();
        int month = thisDay.getMonth();
        int day = thisDay.getDay();
        //判断当前年份是否合法
        if (year <= 0) {
            logger.info("the format of year is illegal");
            logger.info("year is " + year);
            return false;
        }
        //判断当前月份是否合法
        if (month > 12 || month < 1) {
            logger.info("month is " + month);
            return false;
        }
        //得到当前月份对应的天数
        int dayOfMonth = getDayOfMonth(year, month);
        //判断当前日期是否合法
        if (day > dayOfMonth || day < 1) {
            logger.info("dayOfMonth is " + dayOfMonth);
            logger.info("day is " + day);
            return false;
        }


        return true;
    }

    //判断平年和闰年,并返回相应月份的天数
    private int getDayOfMonth(int year, int month) {
        int dayOfMonth = 30;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dayOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                dayOfMonth = 30;
                break;
            case 2:
                if (isLeapYear(year)) {
                    dayOfMonth = 29;
                } else {
                    dayOfMonth = 28;
                }
                break;
        }
        return dayOfMonth;
    }

    private boolean isLeapYear(int year) {
        //规定能被400整除的是闰年
        //能被4整除但是不能被100整除的是闰年
        return ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0) ? true : false;
    }

//    public MyDate dateToMyDate(Date date) {
//        return new MyDate(date);
//    }

    public MyDate strToMyDate(String str) {

        String pattern = ".*[0-9]{4}(.[0-9]{1,2}){2}.*";
        if (!Pattern.matches(pattern, str)) {
            logger.info("the string not matches yyyy/mm/dd");
            return null;
        }

        if (str.lastIndexOf(" ") != -1) {
            str = str.substring(0, str.lastIndexOf(" "));
        }
        //以/ | . - _ \为分隔符
        String[] strs = str.split("/|\\||\\.|-|_|\\\\");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        return new MyDate(year, month, day);
    }

    /*public Date strToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public Date nextNdaysByCalendar(MyDate date, int n) {
        if (!isDateLegal(date)) {
            logger.info("the date is not legal");
            return null;
        }
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        Date today = calendar.getTime();
        //System.out.println(sdf.format(today));
        logger.info("today is " +today);

        calendar.setTime(today);
        calendar.add(Calendar.DATE, n);
        Date nextNdays = calendar.getTime();
        //System.out.println("next n day is:" + sdf.format(nextNdays) + " by java.calendar");
        logger.info("next n day is: " + sdf.format(nextNdays) + " by java.calendar");

        return nextNdays;
    }

}

/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
Date date = new Date();
Calendar c = Calendar.getInstance();
c.setTime(date);
c.add(Calendar.DATE, 100);
System.out.println(sdf.format(c.getTime())); */
