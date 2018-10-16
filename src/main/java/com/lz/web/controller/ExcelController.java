package com.lz.web.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
			System.out.println(is);
			String fileName = myFile.getOriginalFilename();
			System.out.println(fileName);
			if(fileName.endsWith("xls")) {
				HSSFWorkbook hw = new HSSFWorkbook(is);
				parseExcel(hw);
			}else if (fileName.endsWith("xlsx")){
				XSSFWorkbook xw = new XSSFWorkbook(is);
				System.out.println(xw);
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
				System.out.println("LastCellNum"+lastCellNum);
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					System.out.println("cell"+cell);
					System.out.println(cell.getCellType());
					System.out.println(CellType.STRING);
					cell.getStringCellValue();
					System.out.println(cell.getStringCellValue());
				}
			}
		}
	}
	/*private static String getValue(Cell xssfCell, Workbook workbook) {
		String value = "";
		switch (xssfCell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = String.valueOf(xssfCell.getRichStringCellValue()
						.getString());
				System.out.print("|");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(xssfCell)) {
					value = String.valueOf(String.valueOf(xssfCell
							.getDateCellValue()));
				} else {
					value = String.valueOf(xssfCell.getNumericCellValue());
				}
				System.out.print("|");
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(xssfCell.getBooleanCellValue());
				System.out.print("|");
				break;
			// 公式,
			case Cell.CELL_TYPE_FORMULA:
				// 获取Excel中用公式获取到的值,//=SUM(P4-Q4-R4-S4)Excel用这种类似的公式计算出来的值用POI无法获取，要想获取的话，就必须一下操作
				FormulaEvaluator evaluator = workbook.getCreationHelper()
						.createFormulaEvaluator();
				evaluator.evaluateFormulaCell(xssfCell);
				CellValue cellValue = evaluator.evaluate(xssfCell);
				value = String.valueOf(cellValue.getNumberValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				value = String.valueOf(xssfCell.getErrorCellValue());
				break;
			default:
		}
		return value;
	}
*/

}
