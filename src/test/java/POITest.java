import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by baojun on 2016/1/4.
 */
public class POITest {
    @Test
    public void testExport() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("d:/tmp/test.xlsx");
        SXSSFWorkbook wb = new SXSSFWorkbook(xssfWorkbook,100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        //Workbook wb = new HSSFWorkbook();
        Sheet sh = wb.getSheetAt(0);
        for (int rownum = 1; rownum < 1000; rownum++) {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }
        FileOutputStream out = new FileOutputStream("d:\\tmp\\test11.xlsx");
        wb.write(out);
        out.flush();
        out.close();
        wb.dispose();
    }

    @Test
    public void testExport1() throws IOException {
        String path = "d:\\tmp\\tt.xlsx";
        SXSSFWorkbook wb = new SXSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        Row row = sheet1.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("1");
        FileOutputStream fo = new FileOutputStream(path);
        wb.write(fo);
        fo.flush();
        fo.close();
        wb.dispose();
    }
}
