package com.backend.quizzz.fileReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Component
public class ExcelReaderImpl implements ExcelReader {

    @Override
    public Map<Integer, List<String>> getFileContent(String path) throws IOException {

        Map<Integer, List<String>> dataFromExcel = new HashMap<>();
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int rowNumber = 0;

        while (rowIterator.hasNext()) {
            rowNumber++;
            dataFromExcel.put(rowNumber, new ArrayList<>());
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                dataFromExcel.get(rowNumber).add(cell.getStringCellValue());
            }
        }

        return dataFromExcel;
    }
}
