package models;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by XL on 2015-11-28.
 */
public class ImportExcel extends ExcelFiles {

    public void importExcel(String filePath) throws IOException {

        Workbook workbook = getWorkbook(filePath);

        int numberOfSheets = workbook.getNumberOfSheets();
        Sheet sheet;

        sheet = workbook.getSheetAt(0);

        importSheetProjectData(sheet);

        sheet = workbook.getSheetAt(1);

        importSheetMeasure(sheet);
    }


    private void importSheetProjectData(Sheet sheet) {
        //String sheetName = sheet.getSheetName();

        int nbRow = sheet.getPhysicalNumberOfRows();
        int fRow = sheet.getFirstRowNum();
        int nbCol = 0;

        if (0 < nbRow) {
            nbCol = sheet.getRow(fRow).getPhysicalNumberOfCells();
        }

        Iterator iterRows = sheet.rowIterator();
        while (iterRows.hasNext()) {
            Row row = (Row) iterRows.next();

            Iterator cells = row.cellIterator();

            while (cells.hasNext()) {
                Cell cell = (Cell) cells.next();

                String cellValue;

                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    cellValue = cell.getStringCellValue();


                    if (cellValue.equalsIgnoreCase("Nom du Projet")) {
                        if (cells.hasNext()) {
                            cell = (Cell) cells.next();
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                // System.out.println(cell.getStringCellValue());
                                //?? = cell.getStringCellValue();
                            }
                        }
                    } else if (cellValue.equalsIgnoreCase("Stratégie de mesure")) {
                        if (cells.hasNext()) {
                            cell = (Cell) cells.next();
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                // System.out.println(cell.getStringCellValue());
                                //?? = cell.getStringCellValue();
                            }
                        }
                    } else if (cellValue.equalsIgnoreCase("Utilisateurs fonctionnels")) {
                        if (cells.hasNext()) {
                            cell = (Cell) cells.next();
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                //System.out.println(cell.getStringCellValue());
                                //?? = cell.getStringCellValue();
                            }
                        }
                    } else if (cellValue.equalsIgnoreCase("Niveau de granularité")) {
                        if (cells.hasNext()) {
                            cell = (Cell) cells.next();
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                //System.out.println(cell.getStringCellValue());
                                //?? = cell.getStringCellValue();
                            }
                        }
                    } else if (cellValue.equalsIgnoreCase("Niveau de décomposition")) {
                        if (cells.hasNext()) {
                            cell = (Cell) cells.next();
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                // System.out.println(cell.getStringCellValue());
                                //?? = cell.getStringCellValue();
                            }
                        }
                    }


                }
            }
        }
    }

    private void importSheetMeasure(Sheet sheet) {
        int nbRow = sheet.getPhysicalNumberOfRows();
        int fRow = sheet.getFirstRowNum();
        int nbCol = 0;
        int col = 0;

        ArrayList<String> typeOfChange = new ArrayList<String>();
        ArrayList<String> system = new ArrayList<String>();
        ArrayList<String> couche = new ArrayList<String>();
        ArrayList<String> reference = new ArrayList<String>();
        ArrayList<String> process = new ArrayList<String>();
        ArrayList<String> groupOfData = new ArrayList<String>();
        ArrayList<String> mouvement = new ArrayList<String>();
        ArrayList<Integer> entrance = new ArrayList<Integer>();
        ArrayList<Integer> exit = new ArrayList<Integer>();
        ArrayList<Integer> read = new ArrayList<Integer>();
        ArrayList<Integer> write = new ArrayList<Integer>();
        ArrayList<Integer> total = new ArrayList<Integer>();
        ArrayList<String> commentary = new ArrayList<String>();


        if (0 < nbRow) {
            nbCol = sheet.getRow(fRow).getPhysicalNumberOfCells();
        }

        for (int c = 0; c < nbCol; ++c) {


            Row row = sheet.getRow(fRow);

            Iterator iterRows = sheet.rowIterator();
            while (iterRows.hasNext()) {

                row = (Row) iterRows.next();

                Cell cell = row.getCell(col);

                String cellValue = "";


                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    cellValue = cell.getStringCellValue();
                }

                switch (cellValue) {
                    case "Type de changement":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);
                            cell.setCellType(Cell.CELL_TYPE_STRING);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                typeOfChange.add(cell.getStringCellValue());
                                //System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;
                    case "Système":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                system.add(cell.getStringCellValue());
                               // System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;
                    case "Couche":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                couche.add(cell.getStringCellValue());
                                //System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;
                    case "Référence":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                reference.add(cell.getStringCellValue());
                                //System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;
                    case "Processus fonctionnel":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                process.add(cell.getStringCellValue());
                                //System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;
                    case "Groupe de données":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                groupOfData.add(cell.getStringCellValue());
                                //System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;
                    case "Mouvements":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                mouvement.add(cell.getStringCellValue());
                                //System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;
                    case "Entrée":
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)) {
                                String s1 = cell.getStringCellValue();
                                entrance.add((int) Math.round(cell.getNumericCellValue()));
                                //System.out.println(cell.getNumericCellValue());
                            }
                        }
                        break;
                    case "Sortie":
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                                exit.add((int) Math.round(cell.getNumericCellValue()));
                                //System.out.println(cell.getNumericCellValue());


                        }
                        break;
                    case "Lecture":
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)) {
                                read.add((int) Math.round(cell.getNumericCellValue()));
                                //System.out.println((int) Math.round(cell.getNumericCellValue()));
                            }
                        }
                        break;
                    case "Écriture":
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)) {
                                write.add((int) Math.round(cell.getNumericCellValue()));
                                //System.out.println(cell.getNumericCellValue());
                            }
                        }
                        break;
                    case "Total PFC":
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)) {
                                total.add((int) Math.round(cell.getNumericCellValue()));
                                //System.out.println(cell.getNumericCellValue());
                            }
                        }
                        break;
                    case "Commentaires":
                        while (iterRows.hasNext()) {
                            row = (Row) iterRows.next();
                            cell = row.getCell(c);

                            if ((cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                commentary.add(cell.getStringCellValue());
                                //System.out.println(cell.getStringCellValue());
                            }
                        }
                        break;

                }

            }
        }


    }

    /*  // Test avec fichier
    public static void main(String[] argv) throws IOException {
        ImportExcel import1 = new ImportExcel();
        import1.importExcel("C:\\Users\\Fran6\\Documents\\ImportTry.xlsx");
    }
    */
}
