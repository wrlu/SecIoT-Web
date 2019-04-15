package com.wrlus.seciot.util;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 功能：
 * 转换Excel文件格式
 * 
 * 修订版本：
 * 2019-04-12 更新Apache-POI版本到4.1.0，修改getCellType调用
 * 2018-01-04 更新Apache-POI版本到3.17
 * 2018-01-01 首次编写，提供单元格类型转文本型的方法
 * 
 * @author 路伟饶
 *
 */
public class ConventExcelCellType {
	private File sourceFile,targetFile;
	/**
	 * 构造方法
	 * @param from 源文件
	 * @param to 目的文件
	 */
	public ConventExcelCellType(File from, File to) {
		this.sourceFile = from;
		this.targetFile = to;
	}
	/**
	 * 将Excel中的数值型单元格都转为文本型
	 * @throws Exception
	 */
	public void conventToStringValue() throws Exception {
//		目标文件类型为新版Microsoft Excel文件，使用XSSF系列类型
		if (sourceFile.getName().endsWith(".xlsx")) {
//			打开源工作簿
			XSSFWorkbook xlsx = new XSSFWorkbook(sourceFile);
			XSSFWorkbook target = new XSSFWorkbook();
//			获得第一个工作表
			XSSFSheet sheet = xlsx.getSheetAt(0);
			XSSFSheet targetSheet = target.createSheet();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); ++i) {
//				获得行对象，迭代一行数据
				XSSFRow row = sheet.getRow(i);
				XSSFRow targetRow = targetSheet.createRow(i);
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); ++j) {
//					获得单元格对象
					XSSFCell cell = row.getCell(j);
					if (cell==null) {
						continue;
					}
//					判断单元格数据类型
//					只支持文本类型和数字类型
//					如果为文本类型单元格
					XSSFCell targetCell = targetRow.createCell(j);
					if (cell.getCellType().equals(CellType.STRING)) {
						targetCell.setCellValue(cell.getStringCellValue());
					}
//					数字类型单元格，也转化为文本类型存储
					else if (cell.getCellType().equals(CellType.NUMERIC)) {
						Double d = cell.getNumericCellValue();
						targetCell.setCellValue(Long.toString(d.longValue()));
						System.out.println("Changing Numeric Cell Value: "+d.longValue());
					}
					else if (cell.getCellType().equals(CellType.BLANK)) {
						continue;
					}
					else {
						System.out.println("Odd Cell Type: "+cell.getCellType().name()+", skipped.");
						continue;
					}
				}
			}
			xlsx.close();
			FileOutputStream fos = new FileOutputStream(targetFile);
			target.write(fos);
			target.close();
			fos.close();
		}
//		目标文件类型为旧版Microsoft Excel 1997-2003 文件，使用HSSF系列类型
		else if (sourceFile.getName().endsWith(".xls")) {
//			HSSF系列类型中文件打开操作略有不同
//			需要使用POIFSFileSystem类打开文件再传入HSSFWorkbook
//			XSSF系列类型进行了简化，毕竟真的有点反人类......
//			再次证明一个道理：不要用97-03的格式，程序员写程序都更麻烦
			POIFSFileSystem fileSystem = new POIFSFileSystem(sourceFile, true);
			HSSFWorkbook xls = new HSSFWorkbook(fileSystem);
			HSSFWorkbook target = new HSSFWorkbook();
			HSSFSheet sheet = xls.getSheetAt(0);
			HSSFSheet targetSheet = target.createSheet();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); ++i) {
				HSSFRow row = sheet.getRow(i);
				HSSFRow targetRow = targetSheet.createRow(i);
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); ++j) {
					HSSFCell cell = row.getCell(j);
					if (cell == null) {
						continue;
					}
					HSSFCell targetCell = targetRow.createCell(j);
					if (cell.getCellType().equals(CellType.STRING)) {
						targetCell.setCellValue(cell.getStringCellValue());
					}
					else if (cell.getCellType().equals(CellType.NUMERIC)) {
						Double d = cell.getNumericCellValue();
						targetCell.setCellValue(Long.toString(d.longValue()));
						System.out.println("Changing Numeric Cell Value: "+d.longValue());
					}
					else if (cell.getCellType().equals(CellType.BLANK)) {
						continue;
					}
					else {
						System.out.println("Odd Cell Type: "+cell.getCellType().name()+", skipped.");
						continue;
					}
				}
			}
			xls.close();
			FileOutputStream fos = new FileOutputStream(targetFile);
			target.write(fos);
			target.close();
			fos.close();
		}
		else {
			throw new IllegalArgumentException("不是Excel文件后缀");
		}
	}
}