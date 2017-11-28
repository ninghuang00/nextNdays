package homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by huangning on 2017/11/27.
 */
public class ExcelUtilTest {

    List<Row> rows = new ArrayList<Row>();
    @Before

    public void setUp() throws Exception {


        File file = new File("/Users/huangning/Desktop/temp/testCases.xlsx");
        ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
        int height = result.size();

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.println(i + "行 " + j + "列  " + result.get(i).get(j).toString());
            }
        }

       /* for(int i = 0; i < height; ++ i) {
            String in = result.get(i).get(0).toString();
            String n = result.get(i).get(1).toString();
            String out = result.get(i).get(2).toString();
            Date outer = null;
            if (!out.equals("null")) {
                outer = new Date(out);
            }

            Row row = new Row(new Date(in), (int)Double.parseDouble(n), outer);
            rows.add(row);
        }*/
    }


    @Test
    public void main() throws Exception {

        System.out.println();
    }

    @Test
    public void test() throws Exception {
        String str = "2014/9/9";
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        calendar.setTime(sdf.parse(str));

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println();

    }

    @Test
    public void reg() throws Exception {
        String str = "2013/2/12 00:00:00";
        String pattern = ".*[0-9]{4}(.[0-9]{1,2}){2}.*";
        if (Pattern.matches(pattern, str)) {
            System.out.println("ok");
        }
    }


    @After
    public void tearDown() throws Exception {
    }

}