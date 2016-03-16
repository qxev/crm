package cn.finance.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class FileUtils {

	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	public static void writeFile(String path, String content) throws IOException {
		writeFile(new File(path), content);
	}

	public static void writeFile(File file, String content) throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(file, content);
	}

	public static String readFile(String filePath) throws IOException {
		return org.apache.commons.io.FileUtils.readFileToString(new File(filePath));
	}

	public static void createDir(String path) throws Exception {
		File dirFile = new File(path);
		if (!dirFile.exists()) {
			if (!dirFile.mkdirs()) {
				throw new Exception("目录不存在");
			}
		}
	}
	
	public static String getProjectUrl() {
		String path = FileUtils.class.getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("/"));
		return path.substring(0, path.lastIndexOf("/") + 1);
	}
	public static void main(String[] args) throws IOException {
		String text = readFile("D:\\workspace\\finance\\src\\main\\webapps\\WEB-INF\\reports\\baidu\\account\\shb-SEPHORA\\20091116-20091116.txt");
		String line[] = text.split("\n");
		System.out.println(line.length);
		BigDecimal total = new BigDecimal(0);
		for (int i = 1 ; i <line.length;i++){
			String column[] = line[i].split(",");
			BigDecimal value = new BigDecimal(column[6]);
			System.out.println(value);
			total = total.add(value);
		}
		System.out.println(total);
	}
}
