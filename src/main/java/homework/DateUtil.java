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

    /********1582年10月05日到10月14日这十天不存在***********/
    //使用的时候调用这个函数就好了
    public MyDate nextNdays(MyDate myDate, long n) {
        if (myDate == null) {
            return null;
        }

        boolean isGoThrough1582 = false;

        //当n大于一年的天数的时候,先用年份计算
        while (Math.abs(n) > 366) {

//            logger.info("now n is " + n);
//            logger.info("date is " + myDate);

            //logger.info("now n is " + n);
            int thisYear = myDate.getYear();
            int lastYear = thisYear - 1;
            int nextYear = thisYear + 1;
            if (lastYear == 0) {
                lastYear = -1;
            }
            if (nextYear == 0) {
                nextYear = 1;
            }

            if (n >= 0) {
                /*if (myDate.getYear() == 0) {
                    myDate.setYear(1);
                }*/
                //判断这一年是不是闰年
                if (isLeapYear(thisYear)) {
                    //n > 366
                    //当时间早于该闰年的3月1日时
                    while (n > 0 && myDate.compareTo(new MyDate(thisYear, 3, 1)) == -1) {
                        n--;
                        myDate = nextDay(myDate);
                    }
                    if (n >= 365) {
                        n -= 365;
                        myDate.setYear(nextYear);
                    }
                } else if ((thisYear == 1582 || thisYear == 1581) && isGoThrough1582 == false) {
                    //1582年少了10天,所以直接计算比较麻烦,改用原始方法,当时间早于1582.10.15
                    while (n > 0 && myDate.compareTo(new MyDate(1582, 10, 15)) == -1) {
                        n--;
                        myDate = nextDay(myDate);
                    }
                    isGoThrough1582 = true;

//                return nextNdays(myDate, n - 355);
                } else {
                    //n > 366
                    //这一年不是闰年但是下一年是,并且日期超过2月28日
                    if (isLeapYear(nextYear) && myDate.compareTo(new MyDate(thisYear, 3, 1)) == 1) {
                        n -= 366;
                    } else {
                        //如果没有超过2月28日,要到明年的同一天只要365天
                        n -= 365;
                    }
                    myDate.setYear(nextYear);

                }

            } else {
//                if (myDate.getYear() == 0) {
//                    myDate.setYear(-1);
//                }

                //判断这一年是不是闰年
                if (isLeapYear(thisYear)) {
                    // n < -366
                    //当时间晚于该闰年的2月28日时
                    while (n < 0 && myDate.compareTo(new MyDate(thisYear, 2, 28)) == 1) {
                        n++;
                        myDate = lastDay(myDate);
                    }
                    if (n <= -365) {
                        n += 365;
                        myDate.setYear(lastYear);

                    }

                } else if ((thisYear == 1582 || thisYear == 1583) && isGoThrough1582 == false) {
                    //1582年少了10天,所以直接计算比较麻烦,改用原始方法
                    //当时间晚于1582年10月4日
                    while (n < 0 && myDate.compareTo(new MyDate(1582, 10, 4)) == 1) {
                        n++;
                        myDate = lastDay(myDate);
//                        logger.info("now n is " + n);
//                        logger.info("date is " + myDate);
                    }
                    isGoThrough1582 = true;

//                return nextNdays(myDate, n + 355);
                } else {
                    //这一年不是闰年但是上一年是,并且日期没到2月28日
                    if (isLeapYear(lastYear) && myDate.compareTo(new MyDate(thisYear, 3, 1)) == -1) {
                        n += 366;
                    } else {
                        //如果超过2月28日,要到去年的同一天只要365天
                        n += 365;
                    }
                    myDate.setYear(lastYear);

                }
            }
        }

        return nextDays(myDate, n);
        //return nextNdays(myDate, n);

    }

    //以n为条件进行while循环,计算日期
    public MyDate nextDays(MyDate myDate, long n) {
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
            System.out.println("the date you input is illegal: " + date);
            return null;
        }
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        //特例:1582年10月4日的下一天是10月15日
        if (year == 1582 && month == 10 && day == 4) {
            return new MyDate(year, month, 15);
        }
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
                if (year == 0) {
                    year = 1;
                }
            }
        }
        return new MyDate(year, month, day);
    }

    //得到前一天
    public MyDate lastDay(MyDate date) {
        //判断输入日期是否合法
        if (!isDateLegal(date)) {
            System.out.println("the format of date is illegal:" + date.toString());
            return null;
        }
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        //特例: 1582年10月15日的前一天是10月4日
        if (year == 1582 && month == 10 && day == 15) {
            return new MyDate(year, month, 4);
        }
        //计算日子
        if (day - 1 > 0) {
            --day;
        } else {
            //计算月份
            if (month - 1 > 0) {
                --month;
            } else {
                //计算年份
                --year;
                month = 12;
                if (year == 0) {
                    //System.out.println("去不了公元前");
                    //0年就是公元前1年
                    year = -1;
                }
            }
            day = getDayOfMonth(year, month);
        }

        return new MyDate(year, month, day);
    }

    //判断日期是否合法
    public boolean isDateLegal(MyDate thisDay) {
        boolean result = true;
        if (thisDay == null) {
            logger.info("the date input is null" + thisDay);
            return false;

        }
        int year = thisDay.getYear();
        int month = thisDay.getMonth();
        int day = thisDay.getDay();
        //判断当前年份是否合法
        if (year == 0) {
            logger.info("the format of year is illegal" + thisDay);
            result = false;

        }
        //判断当前月份是否合法
        if (month > 12 || month < 1) {
            logger.info("illegal month :" + thisDay);
            result = false;

        }
        //得到当前月份对应的天数
        int dayOfMonth = getDayOfMonth(year, month);
        //判断当前日期是否合法
        if (day > dayOfMonth || day < 1) {
            logger.info("illegal day :" + thisDay);
            logger.info("dayOfMonth is " + dayOfMonth);
            logger.info("day is " + day);
            result = false;

        }
        //1582年10月05日到10月14日这十天不存在
        if (year == 1582 && month == 10) {
            if (day >= 5 && day <= 14) {
                logger.info("the date " + year + "." + month + "." + day + "does not exist in the history");
                result = false;
            }
        }

        if (!result) {
            System.out.println("this date is illegal: " + thisDay);
        }

        return result;
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

    //判断是不是闰年
    private boolean isLeapYear(int year) {
        //规定能被400整除的是闰年
        //能被4整除但是不能被100整除的是闰年
        return ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0) ? true : false;
    }

//    public MyDate dateToMyDate(Date date) {
//        return new MyDate(date);
//    }

    public MyDate strToMyDate(String str) {


        String pattern = ".*[0-9]+(.[0-9]{1,2}){2}.*";
        if (!Pattern.matches(pattern, str)) {
            logger.info("the string not matches yyyy/mm/dd");
            return null;
        }

        if (str.lastIndexOf(" ") != -1) {
            str = str.substring(0, str.lastIndexOf(" "));
        }
        //以/ | . - _ \为分隔符
        String[] strs = str.split("/|\\||\\.|_|\\\\");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        return new MyDate(year, month, day);
    }

    /**使用系统函数验证next n day **/
    /*public Date nextNdaysByCalendar(MyDate date, int n) {
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
        logger.info("today is " + today);

        calendar.setTime(today);
        calendar.add(Calendar.DATE, n);
        Date nextNdays = calendar.getTime();
        //System.out.println("next n day is:" + sdf.format(nextDays) + " by java.calendar");
        logger.info("next n day is: " + sdf.format(nextNdays) + " by java.calendar");

        return nextNdays;
    }*/

}

/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
Date date = new Date();
Calendar c = Calendar.getInstance();
c.setTime(date);
c.add(Calendar.DATE, 100);
System.out.println(sdf.format(c.getTime())); */
