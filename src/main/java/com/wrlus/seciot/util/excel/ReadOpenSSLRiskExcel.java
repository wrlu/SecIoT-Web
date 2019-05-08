package com.wrlus.seciot.util.excel;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class ReadOpenSSLRiskExcel {
	public static void main(String[] args) throws Exception {
		File sourceFile = new File("assets/OpenSSL漏洞索引（严重程度）.xlsx");
		File sqlFile = new File("OpenSSLRisk.sql");
		FileWriter writer = new FileWriter(sqlFile);
		if (sourceFile.exists()) {
			ExcelFileSolver solver = new ExcelFileSolver(sourceFile);
//			读取Excel中所有数据
			Vector<Vector<String>> data = solver.readData();
			Vector<String> firstRow = new Vector<>();
			firstRow = data.get(0);
			Map<String, String> levelMap = new HashMap<>();
			levelMap.put("高", "High");
			levelMap.put("中等", "Medium");
			levelMap.put("低", "Low");
//			数据是行优先排列的，首先遍历行
			for (int i = 1; i < data.size(); ++i) {
				Vector<String> rowVector = data.get(i);
				String cveName = rowVector.get(0);
//				然后遍历列
				for (int j = 1; j < rowVector.size(); ++j) {
					String item = rowVector.get(j);
					if (j == 1) {
						String level = levelMap.get(item);
						writer.write("insert into `seciot`.`cve` values ('"+cveName+"', '"+level+"', '', 'Linux', '');");
						writer.write("\r\n");
						writer.write("insert into `seciot`.`cve_category` values ('"+cveName+"', 'Firmware');");
						writer.write("\r\n");
					}
					if (item != null && item.equals("Y")) {
						String id = UUID.randomUUID().toString();
						String version = firstRow.get(j);
						writer.write("insert into `seciot`.`library_risk` values ('"+id+"', 'OpenSSL', '"+version+"', '"+cveName+"');");
						writer.write("\r\n");
					}
					writer.flush();
				}
			}
		}
		writer.close();
	}
}
