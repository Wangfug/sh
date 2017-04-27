package com.lte.admin.system.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lte.admin.common.utils.CSVUtils;
import com.lte.admin.common.utils.DateUtils;
import com.lte.admin.common.utils.StringUtils;
import com.lte.admin.common.web.BaseController;

/**
 * ExportController
 * 
 * @author yanzai
 */
@Controller
@RequestMapping("system/export")
public class ExportController extends BaseController {
	/**
	 * 数组分割字符 ;
	 */
	private static final String EXPORT_SPLIT = ";";
	/**
	 * 数组里数字内容分割字符 &
	 */
	private static final String NUMBER_SPLIT = "_";

	private static final String EXPORT_FILE_TYPE = ".csv";

	/**
	 * 导出文件
	 * 
	 * @param oldfilename
	 *            业务保存的文件名，可以只有文件名，也可以是上传后得到的完整路径
	 * @param newfilename
	 *            新的文件名，最好只有文件名
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "exportCSV")
	public void exportCSV(@RequestParam("fileName") String fileName, @RequestParam("fileType") String fileType,
			@RequestParam("exportData") String exportData, @RequestParam("head") String head,
			HttpServletRequest request, HttpServletResponse response) {

		String[] headAry = head.split(EXPORT_SPLIT);
		List<Object> headList = new ArrayList<Object>();
		for (String headStr : headAry) {
			headList.add(headStr.trim());
		}
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		String[] dataAry = exportData.split(EXPORT_SPLIT);
		List<String> dataListAry = Arrays.asList(dataAry);
		if (StringUtils.notNull(dataListAry) && dataListAry.size() > 0) {
			for (int i = 0; i < dataListAry.size(); i++) {
				String[] rowAry = dataListAry.get(i).split(NUMBER_SPLIT);
				List<Object> rowDataList = new ArrayList<Object>();
				for (String rowdata : rowAry) {
					rowDataList.add(rowdata);
				}
				dataList.add(rowDataList);
			}
		}
		fileName = fileName + DateUtils.getDateRandom() + EXPORT_FILE_TYPE;
		System.out.println(fileName);
		File saveFile = CSVUtils.createCSVFile(headList, dataList, fileName);
		String filePath = saveFile.getPath();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 获取文件的长度
			long fileLength = new File(filePath).length();
			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(filePath));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
