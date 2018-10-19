package com.lz.web.controller;

import com.lz.bean.User;
import com.lz.common.util.DateFormatUtil;
import com.lz.common.util.StringUtil;
import com.lz.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


@Log4j
@Controller
public class ExcelController {
	@Autowired
	private UserService userService;
	@RequestMapping("/import")
	public String importExcel(MultipartFile myFile){
		try {
			InputStream is = myFile.getInputStream();
			String fileName = myFile.getOriginalFilename();
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

		return "success";
	}

	private void parseExcel(Workbook wb) {
		User user=new User();
		CopyOnWriteArrayList list=new CopyOnWriteArrayList();
		int sheetNum = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			Sheet sheet =  wb.getSheetAt(i);
			for (Row row : sheet
					) {
				if (row.getRowNum() == 0) {
					continue;
				}
				short lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					log.info(getValue(cell,wb));
					list.add(getValue(cell,wb));
				}
				String id=(String)list.get(0);
				String name=(String)list.get(1);
				String age=(String)list.get(2);

				user.setId(StringUtil.StringToInt(id));
				user.setName(name);
				user.setAge(StringUtil.StringToInt(age));
				list.clear();
				userService.saveUser(user);
			}
		}
	}
	private String getValue(Cell cell, Workbook workbook) {
		String value = "";
		switch (cell.getCellType()) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					value = DateFormatUtil.formatDate(cell.getDateCellValue());
				} else {
					value = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			// 公式,
			case FORMULA:
				// 获取Excel中用公式获取到的值,//=SUM(P4-Q4-R4-S4)Excel用这种类似的公式计算出来的值用POI无法获取，要想获取的话，就必须一下操作
				FormulaEvaluator evaluator = workbook.getCreationHelper()
						.createFormulaEvaluator();
				evaluator.evaluateFormulaCell(cell);
				CellValue cellValue = evaluator.evaluate(cell);
				value = String.valueOf(cellValue.getNumberValue());
				break;
			case ERROR:
				value = String.valueOf(cell.getErrorCellValue());
				break;
			default:
		}
		return value;
	}
	@RequestMapping("/export")
	public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("dsfsdf");
		List<User> users = userService.getUsers();
		System.out.println(users);
		XSSFWorkbook xf=new XSSFWorkbook();
		XSSFSheet sheet = xf.createSheet();
		sheet.setHorizontallyCenter(true);
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("学号");
		cell.setCellValue("姓名");
		cell.setCellValue("年龄");
		for (int i = 0; i < users.size(); i++) {
			XSSFCell cell1 = row.createCell(i+1);
			User user = users.get(i);
			cell1.setCellValue(user.getId());
			cell1.setCellValue(user.getName());
			cell1.setCellValue(user.getAge());
		}
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		try {
			xf.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		UUID uuid = UUID.randomUUID();
		String fileName = new String((uuid.toString()+"_student.xls").getBytes("UTF-8"), "iso-8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		System.out.println("success");
		return new ResponseEntity<byte[]>(bos.toByteArray(),headers,HttpStatus.CREATED);
	}

}
