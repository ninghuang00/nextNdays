package homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by huangning on 2017/11/27.
 */
public class DateUtilTest {
    Logger logger = Logger.getLogger("hn");
    DateUtil util = new DateUtil();
    //用来存放从Excel中读取到的每一行数据
    List<Row> rows = new ArrayList<>();
    //用来存放处理之后的结果数据,包括输入和预期输出和实际输出
    List<TestResult> testResults = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        File file = new File("/Users/huangning/Desktop/temp/testCases.xlsx");
        ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
        int height = result.size();

        /*for(int i = 0 ;i < result.size() ;i++){
            for(int j = 0;j<result.get(i).size(); j++){
                System.out.println(i+"行 "+j+"列  "+ result.get(i).get(j).toString());
            }
        }*/

        for (int i = 0; i < height; ++i) {
            String in = result.get(i).get(0).toString();
            String n = result.get(i).get(1).toString();
            String out = result.get(i).get(2).toString();

//            System.out.println(in + "," + n + "," + out);

            try {
                MyDate iner = util.strToMyDate(in);

                MyDate outer = null;
                if (!out.equals("null")) {
                    outer = util.strToMyDate(out);
                } else {
                    logger.info("the output date expected is null");
                }
                Row row = new Row(iner, (int) Double.parseDouble(n), outer);
                rows.add(row);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                logger.info("where wrong:============> " + " in is " + in + " n is " + n + " out is " + out);
            }

        }

    }

    @After
    public void tearDown() throws Exception {
        System.out.println(" *********** result info ****************");
        for (TestResult result : testResults) {
            System.out.println(result.toString());
//            Assert.assertEquals(result.getDateExpected(),result.getDateResult());
        }


    }
    @Test
    public void nextNdays() throws Exception {

        for (Row row : rows) {
            MyDate thisDay = row.getInputDate();
            int n = row.getN();
            MyDate thatDay = row.getOutputDate();
            TestResult result = new TestResult();
            result.setDateInput(thisDay);
            result.setDateResult(util.nextDays(thisDay, n));
            result.setDateExpected(thatDay);
            result.setN(n);
            testResults.add(result);

        }
        System.out.println();

    }
   /* @Test
    public void nextDay() throws Exception {
        String str = "2017/11/31";

        int year = 2017;
        int month = 11;
        int day = 31;
        thisDay.setYear(year);
        thisDay.setMonth(month);
        thisDay.setDay(day);
        dates.add(thisDay);
        System.out.println("the date input is:" + thisDay.toString());
        System.out.println("the next day is:" + util.nextDay(year, month, day));
    }*/

    /*@Test
    public void nextNdaysByCalendar() throws Exception {

        int year = 118;

        // int year = -316;
        int month = 3;
        int day = 10;
        Date date = new Date(year, month - 1, day);
//        date = new Date();

        *//*TestSuite suite = new TestSuite();
        junit.framework.TestResult testResult = new junit.framework.TestResult();
        suite.run(testResult);
        suite*//*
//        int n = -365*2000;
        *//*1582年10月05日到10月14日这十天不存在*//*

//        int n = -710;
//        int n = -158890 - 265;
        int n = -733;

//        int n = -158925*2;
        // long n = (2 << 30) - 1;
//        int n = 181502;
//        int n = 365 * 1000;
//        int n = 365*3 +366;
        System.out.println("result of system api ==========>");
        System.out.println(util.nextNdaysByCalendar(new MyDate(date), (int) n));
        System.out.println("result of my program =========>");
        System.out.println(util.nextNdays(new MyDate(date), n));
        System.out.println();

    }*/



    class TestResult {
        private MyDate dateInput;
        private MyDate dateResult;
        private MyDate dateExpected;
        private int n;

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public TestResult(MyDate dateInput, MyDate dateResult, MyDate dateExpected) {
            this.dateInput = dateInput;
            this.dateResult = dateResult;
            this.dateExpected = dateExpected;
        }


        public MyDate getDateInput() {
            return dateInput;
        }

        public void setDateInput(MyDate dateInput) {
            this.dateInput = dateInput;
        }

        public TestResult() {

        }

        public MyDate getDateResult() {
            return dateResult;
        }

        public void setDateResult(MyDate dateResult) {
            this.dateResult = dateResult;
        }

        public MyDate getDateExpected() {
            return dateExpected;
        }

        public void setDateExpected(MyDate dateExpected) {
            this.dateExpected = dateExpected;
        }

        @Override
        public String toString() {
            return "dateInput is " + dateInput +
                    "\n dateResult is " + dateResult +
                    "\n dateExpected is " + dateExpected +
                    "\n the arg n is " + n;

        }
    }

}