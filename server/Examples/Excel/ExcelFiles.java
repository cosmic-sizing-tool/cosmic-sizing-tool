package models;

import com.avaje.ebean.Model;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Fran6 on 2015-11-28.
 */
public class ExcelFiles extends Model {
    protected Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook = null;

        if (filePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(new FileInputStream(filePath));
        } else if (filePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(filePath));
        } else {
            throw new IllegalArgumentException();
        }
        return workbook;
    }
}
