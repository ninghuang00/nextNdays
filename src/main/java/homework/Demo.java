package homework;

import org.junit.Test;

import java.util.Date;

/**
 * Created by huangning on 2017/11/28.
 */
public class Demo {

    DateUtil util = new DateUtil();

    @Test
    public void strToDate() throws Exception {
        String in = "1/1/1";
        String n = "-367";
        String out = "-1/1/30";
        MyDate inner = util.strToMyDate(in);

        MyDate outter  = util.strToMyDate(out);

        System.out.println();


    }

    @Test
    public void nextDays() throws Exception {

//        String path = Demo.class.getClassLoader().getResource("");
        /*int year = 1582;
        int month = 3;
        int day = 10;
        int n = -3650000;
        MyDate thisDay = new MyDate(year, month, day);

        MyDate thatDay = util.nextNdays(thisDay, n);

        System.out.println();*/
    }

    @Test
    public void dateTest() throws Exception {

        //int year = 118;
        int year = -316;
        int month = 3;
        int day = 10;
        Date date = new Date(year, month - 1, day);
//        date = new Date();
//        int n = -734;
//        int n = -158925*2;
        long n = -3650000;
        // long n = (2 << 30) - 1;
//        int n = 181502;
//        int n = 365 * 1000;
//        int n = 365*3 +366;
        System.out.println("result of system api ==========>");
        //System.out.println(util.nextNdaysByCalendar(new MyDate(date), (int) n));
        System.out.println("result of my program =========>");
        System.out.println(util.nextNdays(new MyDate(date), n));
        System.out.println();
    }


}
