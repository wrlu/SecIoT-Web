package com.wrlus.seciot.util.excel;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class ReadRiskExcel {
	
	public static void main(String[] args) throws Exception {
		File sourceFile = new File("/home/wrlu/文档/CAUC-项目科研/中国民航大学本科毕业设计/第三方库漏洞/Zlib漏洞.xlsx");
		File sqlFile = new File("ZlibRisk.sql");
		String libName = "Zlib";
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
			String libid = UUID.randomUUID().toString();
			writer.write("insert into `seciot`.`third_library` values ('"+libid+"', '"+libName+"', '', '');");
			writer.write("\r\n");
//			数据是行优先排列的，首先遍历行
			for (int i = 1; i < data.size(); ++i) {
				Vector<String> rowVector = data.get(i);
				String cveName = rowVector.get(0).replaceAll(" ", "").trim();
//				然后遍历列
				for (int j = 2; j < rowVector.size(); ++j) {
					String item = rowVector.get(j);
					if (j == 2 && item != null) {
						String des = item.trim();
						writer.write("insert into `seciot`.`cve` values ('"+cveName+"', '', '"+des+"', 'Linux', '');");
						writer.write("\r\n");
						continue;
					}
					if (j == 3 && item != null) {
						String level = "Unknown";
						double mark = Double.valueOf(item);
						if (mark >= 7) {
							level = "High";
						} else if (mark >= 4 && mark < 7) {
							level = "Medium";
						} else if (mark < 4 && mark > 0) {
							level = "Low";
						}
						writer.write("update `seciot`.`cve` set level = '"+level+"' where cve_num = '"+cveName+"';");
						writer.write("\r\n");
						continue;
					}
					if (item != null && item.equals("Y")) {
						String id = UUID.randomUUID().toString();
						String version = firstRow.get(j);
						writer.write("insert into `seciot`.`library_risk` values ('"+id+"', '"+libName+"', '"+version+"', '"+cveName+"');");
						writer.write("\r\n");
					}
					writer.flush();
				}
			}
		}
		writer.close();
	}
}
