package com.lz.web.controller;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class ExcelController {
	@RequestMapping("/upload")
	public String upload(MultipartFile myFile){
		System.out.println("-------");
		try {
			InputStream is = myFile.getInputStream();
			String fileName = myFile.getOriginalFilename();
			System.out.println(fileName);
			if(fileName.endsWith("xls")) {
				HSSFWorkbook hw = new HSSFWorkbook(is);
				parseExcel(hw);
			}else if (fileName.endsWith("xlsx")){
				XSSFWorkbook xw = new XSSFWorkbook(is);
				parseExcel(xw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void parseExcel(Workbook wb) {
		int sheetNum = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			Sheet sheet =  wb.getSheetAt(i);
			int count = 0;
			for (Row row : sheet
					) {
				System.out.println(row.getRowNum());
				if (count == 0) {
					count++;
					continue;
				}
				short lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					System.out.println(cell.getStringCellValue());
				}
			}
		}
	}
}
