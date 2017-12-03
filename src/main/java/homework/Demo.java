package homework;

import org.junit.Test;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by huangning on 2017/11/28.
 */
public class Demo {

    Logger logger = Logger.getLogger("hn");
    DateUtil util = new DateUtil();

    @Test
    public void strToDate() throws Exception {
        String in = "1/1/1";
        String n = "-367";
        String out = "-1/1/30";
        MyDate inner = util.strToMyDate(in);

        MyDate outter = util.strToMyDate(out);

        System.out.println();


    }

    @Test
    public void nextDays() throws Exception {

//        String n = "1";
//        String pattern = "^-?\\d+$";
//        if (Pattern.compile(pattern).matcher(n).find()) {
//            System.out.println("pass");
//        }

//        String path = Demo.class.getClassLoader().getResource("");
        int year = 2016;
        int month = 2;
        int day = 30;
        int n = 3;
        MyDate thisDay = new MyDate(year, month, day);

        MyDate thatDay = util.nextNdays(thisDay, n);

        System.out.println();
    }

    @Test
    public void dateTest() throws Exception {

        logger.setLevel(Level.OFF);
        //year=118是2018年,year=0是1900年
        //int year = 118;
        int year = 100;
//        int year = -148;
        int month = 1;
        int day = 1;
        Date date = new Date(year, month - 1, day);
//        date = new Date();
//        int n = -734;
//        int n = -158925*2;
        long n = 365;
        // long n = (2 << 30) - 1;
//        int n = 181502;
//        int n = 365 * 1000;
//        int n = 365*3 +366;
        int base = 365;


        for (int i = 1; i < 10; i++) {
            n = i * base;
            System.out.println("/********************** start ***********************/");
            System.out.println("result of system api ==========>");
            System.out.println(util.nextNdaysByCalendar(new MyDate(date), (int) n));
            System.out.println("result of my program =========>");
            System.out.println(util.nextNdays(new MyDate(date), n));
            System.out.println("/********************** end ***********************/");
            System.out.println();
        }
    }

    @Test
    public void testOne() throws Exception {
//        logger.setLevel(Level.OFF);
        //year=118是2018年,year=0是1900年,1548
        //int year = 118;
        int year = -354;
//        int year = -148;
        int month = 1;
        int day = 1;
        Date date = new Date(year, month - 1, day);
//        date = new Date();
//        int n = -734;
//        int n = -158925*2;
        long n = 100000;


        System.out.println("/********************** start ***********************/");
        System.out.println("result of system api ==========>");
        System.out.println(util.nextNdaysByCalendar(new MyDate(date), (int) n));
        System.out.println("result of my program =========>");
        System.out.println(util.nextNdays(new MyDate(date), n));
        System.out.println("/********************** end ***********************/");
        System.out.println();

    }
}
