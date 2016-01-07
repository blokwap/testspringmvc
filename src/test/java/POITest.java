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
    public void testExport1(){
//        Workbook wb = new HSSFWorkbook();
//        Workbook wb = new XSSFWorkbook(100);
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short)0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // Write the output to a file
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("d:/tmp/workbook.xlsx");
            try {
                wb.write(fileOut);
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
