package cn.finance.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;

import cn.finance.model.bean.ServiceAdjustBean;

public abstract class ExcelUtils {

	public static Workbook transformXLS(String templateFileName, Map<String, Object> map, short[] columnsToHide)
			throws FileNotFoundException {
		XLSTransformer transformer = new XLSTransformer();
		if (columnsToHide != null)
			transformer.setColumnsToHide(columnsToHide);
		InputStream in = new BufferedInputStream(new FileInputStream(templateFileName));
		try {
			Workbook workbook = transformer.transformXLS(in, map);
			return workbook;
		} catch (ParsePropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static HSSFWorkbook readExl(String xlsPath) throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(xlsPath));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		return wb;
	}

	public static String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		if (cell != null)
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue().trim();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，取得该Cell的Date值
					Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					Format formatter = new SimpleDateFormat("yyyy-MM-dd");
					strCell = formatter.format(date);
				} else {
					java.text.NumberFormat nf = NumberFormat.getCurrencyInstance();
					DecimalFormat df = (DecimalFormat) nf;
					df.setDecimalSeparatorAlwaysShown(true);
					df.applyPattern("#0.00");
					String value = df.format(new Double(cell.getNumericCellValue()));
					strCell = String.valueOf(value);
				}
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				strCell = "";
				break;
			}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 得到搜搜绩效
	 * 
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public static List<ServiceAdjustBean> getSoSoReport(String upload) throws Exception {
		// POIFSFileSystem fs = new POIFSFileSystem(new
		// FileInputStream(upload));
		// HSSFWorkbook wb = new HSSFWorkbook(fs);
		// HSSFSheet sheet = wb.getSheetAt(0);
		// List <ServiceAdjustBean>list = new ArrayList<ServiceAdjustBean>();
		// for (int i=1; i<sheet.getDefaultRowHeight();i++){
		// HSSFRow row = sheet.getRow(i);
		// if (row == null)
		// break;
		// Date date = new Date();
		// Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		// BigDecimal value = new BigDecimal("0");
		// for (int j=0; j<row.getLastCellNum();j++){
		// try {
		// HSSFCell cell = row.getCell(j);
		// if (cell == null)
		// break;
		// ParsePosition pos = new ParsePosition(0);
		// String strValue = getStringCellValue(cell);
		// if (j==0){
		// date = (Date) formatter.parseObject(strValue,pos);
		// }
		// if (date == null)
		// break;
		// if (j==5)
		// value = new BigDecimal(strValue);
		// } catch (Exception e){
		// StringBuffer sb = new StringBuffer();
		// sb.append("第").append(i+1).append("行的第").append(j+1).append("列不对，请修改正确后上传");
		// throw new Exception(sb.toString());
		// }
		// }
		// if (date!=null){
		// ServiceAdjustBean record = new ServiceAdjustBean(value,date);
		// list.add(record);
		// }
		// }
		// return list;
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(upload), Charset.forName("GBK")));
		List<ServiceAdjustBean> list = new ArrayList<ServiceAdjustBean>();
		String stemp;
		int i = 1;
		while ((stemp = bufferedreader.readLine()) != null) {
			try {
				Format formatter = new SimpleDateFormat("yyyyMMdd");
				CSVUtils parser = new CSVUtils();
				List<String> cells = parser.parse(stemp);
				if (cells.size() == 11) {
					ParsePosition pos = new ParsePosition(0);
					Date date = (Date) formatter.parseObject(cells.get(0), pos);
					BigDecimal value = new BigDecimal(0);
					if (date != null) {
						String strvalue = cells.get(6);
						strvalue = strvalue.replaceAll(",", "");
						value = new BigDecimal(strvalue);
						ServiceAdjustBean record = new ServiceAdjustBean(value, date);
						list.add(record);
					}
				}
				i++;
			} catch (Exception e) {
				StringBuffer sb = new StringBuffer();
				sb.append("第").append(i + 1).append("行不对，请修改正确后上传");
				e.printStackTrace();
				throw new Exception(sb.toString());
			}
		}
		return list;
	}

	/**
	 * 得到网盟绩效
	 * 
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public static List<ServiceAdjustBean> getNetAffReport(String upload) throws Exception {
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(upload), Charset.forName("GBK")));
		List<ServiceAdjustBean> list = new ArrayList<ServiceAdjustBean>();
		String stemp;
		int i = 1;
		while ((stemp = bufferedreader.readLine()) != null) {
			try {
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				CSVUtils parser = new CSVUtils();
				List<String> cells = parser.parse(stemp);
				if (cells.size() == 7) {
					ParsePosition pos = new ParsePosition(0);
					Date date = (Date) formatter.parseObject(cells.get(0), pos);
					BigDecimal value = new BigDecimal(0);
					if (date != null) {
						String strvalue = cells.get(6);
						strvalue = strvalue.replaceAll("￥", "");
						strvalue = strvalue.replaceAll(",", "");
						value = new BigDecimal(strvalue);
						ServiceAdjustBean record = new ServiceAdjustBean(value, date);
						list.add(record);
					}
				}
				i++;
			} catch (Exception e) {
				StringBuffer sb = new StringBuffer();
				sb.append("第").append(i + 1).append("行不对，请修改正确后上传");
				e.printStackTrace();
				throw new Exception(sb.toString());
			}
		}
		return list;
	}

	/**
	 * 得到雅虎绩效
	 * 
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public static List<ServiceAdjustBean> getYahooReport(String upload) throws Exception {
		BufferedReader bufferedreader = new BufferedReader(new FileReader(upload));
		List<ServiceAdjustBean> list = new ArrayList<ServiceAdjustBean>();
		String stemp = bufferedreader.readLine();
		if (stemp != null) {
			int i = 1;
			while ((stemp = bufferedreader.readLine()) != null) {
				try {
					Format formatter = new SimpleDateFormat("yyyy-MM-dd");
					String str[] = stemp.split(",");
					ParsePosition pos = new ParsePosition(0);
					Date date = (Date) formatter.parseObject(str[0], pos);
					BigDecimal value = new BigDecimal(str[7]);
					if (date != null) {
						ServiceAdjustBean record = new ServiceAdjustBean(value, date);
						list.add(record);
					}
					i++;
				} catch (Exception e) {
					StringBuffer sb = new StringBuffer();
					sb.append("第").append(i + 1).append("行不对，请修改正确后上传");
					throw new Exception(sb.toString());
				}
			}
		}
		return list;
	}

	/**
	 * 得到搜狗绩效
	 * 
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public static List<ServiceAdjustBean> getSougoReport(String upload) throws Exception {
		// POIFSFileSystem fs = new POIFSFileSystem(new
		// FileInputStream(upload));
		// HSSFWorkbook wb = new HSSFWorkbook(fs);
		// HSSFSheet sheet = wb.getSheetAt(0);
		// List <ServiceAdjustBean>list = new ArrayList<ServiceAdjustBean>();
		// for (int i=1; i<sheet.getDefaultRowHeight();i++){
		// HSSFRow row = sheet.getRow(i);
		// if (row == null)
		// break;
		// Date date = new Date();
		// Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		// BigDecimal value = new BigDecimal("0");
		// for (int j=0; j<row.getLastCellNum();j++){
		// try {
		// HSSFCell cell = row.getCell(j);
		// if (cell == null)
		// break;
		// ParsePosition pos = new ParsePosition(0);
		// String strValue = getStringCellValue(cell);
		// if (j==0){
		// date = (Date) formatter.parseObject(strValue,pos);
		// }
		// if (date == null)
		// break;
		// if (j==5)
		// value = new BigDecimal(strValue);
		// } catch (Exception e){
		// StringBuffer sb = new StringBuffer();
		// sb.append("第").append(i+1).append("行的第").append(j+1).append("列不对，请修改正确后上传");
		// throw new Exception(sb.toString());
		// }
		// }
		// if (date!=null){
		// ServiceAdjustBean record = new ServiceAdjustBean(value,date);
		// list.add(record);
		// }
		// }
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(upload), Charset.forName("GBK")));
		List<ServiceAdjustBean> list = new ArrayList<ServiceAdjustBean>();
		String stemp;
		int i = 1;
		while ((stemp = bufferedreader.readLine()) != null) {
			try {
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				CSVUtils parser = new CSVUtils();
				List<String> cells = parser.parse(stemp);
				if (cells.size() == 9) {
					ParsePosition pos = new ParsePosition(0);
					Date date = (Date) formatter.parseObject(cells.get(1), pos);
					BigDecimal value = new BigDecimal(0);
					if (date != null) {
						String strvalue = cells.get(3);
						strvalue = strvalue.replaceAll(",", "");
						value = new BigDecimal(strvalue);
						ServiceAdjustBean record = new ServiceAdjustBean(value, date);
						list.add(record);
					}
				}
				i++;
			} catch (Exception e) {
				StringBuffer sb = new StringBuffer();
				sb.append("第").append(i + 1).append("行不对，请修改正确后上传");
				e.printStackTrace();
				throw new Exception(sb.toString());
			}
		}
		return list;
	}

}
