package com.lte.admin.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关于导出csv的工具类.
 * 
 * @author yanzai
 * 
 */
public class CSVUtils {

	/**
	 * CSV存放文件夹：xdptCSV
	 * 
	 */
	private static final String CSV_FOLDER_NAME = "xdptCSV";

	private static Logger logger = LoggerFactory.getLogger(CSVUtils.class);

	/**
	 * 写一行数据方法
	 * 
	 * @param row
	 * @param csvWriter
	 * @throws IOException
	 */
	private static void writeRow(List<Object> row, BufferedWriter csvWriter, boolean isHeader) throws IOException {
		String str = "";
		// 写入文件头部
		for (int i = 0; i < row.size(); i++) {
			Object data = row.get(i);
			StringBuffer sb = new StringBuffer();

			String rowStr = "";
			if (isHeader) {
				rowStr = sb.append("\"").append(data).append("\",").toString();
			} else {
				str = String.valueOf(data);
				// 临时解决方法
				// 数字的长度大于等于11位，在Excel中打开，会显示为科学记数法
				// 而一条记录中的金额，几乎不对达到11位（百亿级别）

				// 另外还要考虑在Excel中打开，需要能够对单元格进行公示计算，如SUM
				if (NumberUtils.isNumber(str)) {
					// 一般只会有电话号码以0开头，做特殊处理,避免不显示开头的0
					if (str.startsWith("0")) {
						rowStr = sb.append("\"\t").append(data).append("\",").toString();
					} else if (str.length() >= 11) {
						if (str.indexOf('.') != -1) {
							rowStr = sb.append("=\"").append(String.format("%.2f", new BigDecimal(str))).append("\",")
									.toString();
						} else {
							rowStr = sb.append("=\"").append(str).append("\",").toString();
						}
					} else {
						if (str.indexOf('.') != -1) {
							rowStr = sb.append("\"=").append(String.format("%.2f", new BigDecimal(str))).append("\",")
									.toString();
						} else {
							rowStr = sb.append("\"=").append(str).append("\",").toString();
						}
					}
				} else if (str.matches("[\\d-]+")) {
					rowStr = sb.append("\"\t").append(data).append("\",").toString();
				} else {
					rowStr = sb.append("\"").append(data).append("\",").toString();
				}
			}
			// 删除最后一个不必要的逗号
			if (i == (row.size() - 1)) {
				rowStr = rowStr.substring(0, rowStr.length() - 1);
			}
			csvWriter.write(rowStr);
		}
		csvWriter.newLine();
	}

	/**
	 * CSV生成文件方法
	 * 
	 * @param head
	 * @param dataList
	 * @param fileName
	 * @return
	 */
	public static File createCSVFile(List<Object> headList, List<List<Object>> dataList, String fileName) {

		File csvFile = null;
		BufferedWriter csvWtriter = null;
		try {
			if (StringUtils.isBlank(fileName)) {
				fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			}
			if (fileName.indexOf(".csv") > -1) {
				fileName = fileName.replaceAll(".csv", "");
			}
			if (fileName.indexOf(".") > -1) {
				fileName = fileName.replaceAll("\\.", "");
			}
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			int end = path.length() - "WEB-INF/classes/".length();
			path = path.substring(1, end);
			path = Global.getConfig("filesavepath");
			String fileDir = path + File.separator + CSV_FOLDER_NAME + File.separator + fileName + ".csv";
			csvFile = new File(fileDir);
			File parent = csvFile.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			csvFile.createNewFile();

			// GB2312使正确读取分隔符","
			csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
			// 写入文件头部
			writeRow(headList, csvWtriter, true);

			// 写入文件内容
			for (List<Object> row : dataList) {
				writeRow(row, csvWtriter, false);
			}
			csvWtriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (csvWtriter != null) {
					csvWtriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvFile;
	}

	// /**
	// * 导入票据批量录入的CSV文件
	// *
	// * @param filePath
	// * @return
	// */
	// public static List<Plpjxx> importCSV(String filePath) {
	// List<Plpjxx> list = new ArrayList<Plpjxx>();
	// int lineNum = 1;
	// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	// String line = "";
	// String[] arr;
	// Plpjxx plpjxx = null;
	// int oldLength = 0;
	//
	// // skip the header line
	// br.readLine();
	//
	// while ((line = br.readLine()) != null) {
	// plpjxx = new Plpjxx();
	// arr = line.split(",");
	//
	// if (arr != null && arr.length > 26) {
	// throw new XdptException("导入的CSV文件列的个数过多，请确认!");
	// }
	//
	// oldLength = arr.length;
	// // 导出CSV共通有bug，临时规避
	// if (oldLength < 26) {
	// arr = Arrays.copyOf(arr, 26);
	// for (int i = oldLength; i < 26; i++) {
	// arr[i] = "";
	// }
	// }
	//
	// // 替换特殊字符按
	// for (int i = 0; i < arr.length; i++) {
	// arr[i] = arr[i].replaceAll("\"", "").replaceFirst("=", "").replace("\t",
	// "");
	// }
	//
	// // TODO check文件数据的合法性
	// if (StringUtils.isBlank(arr[0]) || arr[0].indexOf("-") == -1) {
	// throw new XdptException("导入文件第" + lineNum + "行出错！信贷单据号不对！" +
	// StringUtils.toNotNullString(arr[0]));
	// }
	// plpjxx.setClid(Long.valueOf((arr[0].split("-"))[0]));
	// plpjxx.setPjid(Short.valueOf((arr[0].split("-"))[1]));
	// plpjxx.setSqsj(DateUtils.parseDate(arr[1]));
	//
	// // TODO 增加平台业财确认时间
	// plpjxx.setPtycqrsj(DateUtils.parseDate(arr[2]));
	// plpjxx.setSkrgsmc(arr[3]);
	// plpjxx.setSkryhzhmc(arr[4]);
	// plpjxx.setSkryhzh(arr[5]);
	// plpjxx.setSkryhlhh(arr[6]);
	//
	// if ("3个月".equals(arr[7])) {
	// plpjxx.setPjqx("3");
	// } else if ("6个月".equals(arr[7])) {
	// plpjxx.setPjqx("6");
	// } else {
	// throw new XdptException(
	// "导入文件第" + lineNum + "行出错！票据期限不对！" + ":" +
	// StringUtils.toNotNullString(arr[7]));
	// }
	// plpjxx.setPjqxName(arr[7]);
	//
	// if ("纸质票据".equals(arr[8])) {
	// plpjxx.setPjlx("1");
	// } else if ("电子票据".equals(arr[8])) {
	// plpjxx.setPjlx("2");
	// } else {
	// throw new XdptException(
	// "导入文件第" + lineNum + "行出错！票据类型不对！" + ":" +
	// StringUtils.toNotNullString(arr[8]));
	// }
	// plpjxx.setPjlxName(arr[8]);
	//
	// plpjxx.setDzpmjexx(StringUtils.toBigDecimal(arr[9]));
	// plpjxx.setDzpmjedx(arr[10]);
	// plpjxx.setBzxx(arr[11]);
	// plpjxx.setPh(arr[12]);
	//
	// if (!StringUtils.isBlank(arr[13]) && !DateUtils.isDateFormat(arr[13])) {
	// throw new XdptException(
	// "导入文件第" + lineNum + "行出错！票面出票日格式不对！" + ":" +
	// StringUtils.toNotNullString(arr[13]));
	// }
	// plpjxx.setPmcpr(DateUtils.parseDate(arr[13]));
	//
	// if (!StringUtils.isBlank(arr[14]) && !DateUtils.isDateFormat(arr[14])) {
	// throw new XdptException(
	// "导入文件第" + lineNum + "行出错！票面到期日格式不对！" + ":" +
	// StringUtils.toNotNullString(arr[14]));
	// }
	// plpjxx.setPmdqr(DateUtils.parseDate(arr[14]));
	//
	// plpjxx.setPtgsmc(arr[15]);
	// plpjxx.setSsfbmc(arr[16]);
	//
	// if ("总部新办".equals(arr[17])) {
	// plpjxx.setJylx("21");
	// } else if ("票据下拨".equals(arr[17])) {
	// plpjxx.setJylx("33");
	// } else {
	// throw new XdptException(
	// "导入文件第" + lineNum + "行出错！交易类型不对！" + ":" +
	// StringUtils.toNotNullString(arr[17]));
	// }
	// plpjxx.setJylxName(arr[17]);
	// plpjxx.setCprgsmc(arr[18]);
	// plpjxx.setCpryhzhmc(arr[19]);
	// plpjxx.setCpryhzh(arr[20]);
	//
	// if ("总部面签".equals(arr[21])) {
	// plpjxx.setLqfs("1");
	// } else if ("邮寄".equals(arr[21])) {
	// plpjxx.setLqfs("2");
	// } else {
	// throw new XdptException(
	// "导入文件第" + lineNum + "行出错！领取方式不对！" + ":" +
	// StringUtils.toNotNullString(arr[21]));
	// }
	// plpjxx.setLqfsName(arr[21]);
	// plpjxx.setLxr(arr[22]);
	// plpjxx.setLxdh(arr[23]);
	// plpjxx.setSjrgsmc(arr[24]);
	// plpjxx.setSjdz(arr[25]);
	//
	// list.add(plpjxx);
	// lineNum++;
	// }
	// } catch (XdptException ex) {
	// logger.error("导入文件第" + lineNum + "行出错！" +
	// Exceptions.getStackTraceAsString(ex));
	// throw new XdptException("导入文件第" + lineNum + "行出错！" + ex.getErrorMsg());
	// } catch (Exception ex) {
	// logger.error("导入文件第" + lineNum + "行出错！" +
	// Exceptions.getStackTraceAsString(ex));
	// }
	//
	// return list;
	// }

}
