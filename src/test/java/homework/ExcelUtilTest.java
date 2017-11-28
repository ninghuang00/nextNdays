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
    ExcelUtil util = new ExcelUtil();

    @Test
    public void write() throws Exception {
        //File file = new File("/Users/huangning/Desktop/temp/testResult");
        String path = "/Users/huangning/Desktop/temp/testResult.xlsx";
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        ArrayList<Object> rows = new ArrayList<>();
        String[] titles1 = {"用例通过率","代码覆盖率"};
        String[] titles2 ={"input date","n","excepted date","output date"};
        for (String s : titles1) {
            rows.add(s);
        }
        result.add(rows);
        for (String s : titles2) {
            rows.add(s);

        }
        result.add(rows);

        util.writeExcel(result, path);

    }
}