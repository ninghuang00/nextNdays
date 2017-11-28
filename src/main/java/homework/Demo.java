package homework;

import java.util.Date;

/**
 * Created by huangning on 2017/11/28.
 */
public class Demo {

    public static void main(String[] args) {
        DateUtil util = new DateUtil();

        //int year = 118;
        int year = -316;
        int month = 3;
        int day = 10;
        Date date = new Date(year, month - 1, day);
//        date = new Date();
//        int n = -734;
//        int n = -158925*2;
        long n = 2<<30;
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
