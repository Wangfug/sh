package com.lte.admin.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 关于Xml处理的工具类.
 * 
 * @author yanzai
 * 
 */
public class XmlUtils {

	/**
	 * 凭证发送、返回结果信息保存xml字符串内容文件夹：xdptXml
	 * 
	 */
	private static final String XML_FOLDER_NAME = "xdptXml";

	/**
	 * 保存xml字符串内容到自定义的文件
	 * 
	 * @param xmlStr
	 *            字符串内容
	 * @param fileName
	 *            文件名
	 * @param folderName
	 *            文件夹目录
	 * @return
	 */
	public static boolean saveXMLStrToFile(String xmlStr, String fileName, String folderName) {
		if (StringUtils.isBlank(xmlStr)) {
			return false;
		}
		if (StringUtils.isBlank(fileName)) {
			fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		}
		if (fileName.indexOf(".xml") > -1) {
			fileName = fileName.replaceAll(".xml", "");
		}
		if (fileName.indexOf(".") > -1) {
			fileName = fileName.replaceAll("\\.", "");
		}
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		int end = path.length() - "WEB-INF/classes/".length();
		path = path.substring(1, end);
		path = Global.getConfig("filesavepath");
		String fileDir = path + File.separator + XML_FOLDER_NAME + File.separator
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + File.separator;
		if (StringUtils.notBlank(folderName)) {
			fileDir = path + File.separator + folderName + File.separator;
		}
		File fdir = new File(fileDir);
		fdir.mkdirs();

		XMLWriter xmlWriter = null;
		try {
			Document document = new SAXReader().read(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
			// 设置XML文档格式
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();
			// 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
			outputFormat.setEncoding("UTF-8");
			// xmlWriter是用来把XML文档写入字符串的(工具)
			xmlWriter = new XMLWriter(new FileOutputStream(fileDir + fileName + ".xml"), outputFormat);

			// 把创建好的XML文档写入字符串
			xmlWriter.write(document);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (null != xmlWriter) {
				try {
					xmlWriter.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
	}

	/**
	 * 保存xml字符串内容到自定义的文件
	 * 
	 * @param str
	 *            字符串内容
	 * @param fileName
	 *            文件名
	 * @param folderName
	 *            文件夹目录
	 * @return
	 */
	public static boolean saveStrToFile(String str, String fileName, String folderName) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		if (StringUtils.isBlank(fileName)) {
			fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		}
		if (fileName.indexOf(".xml") > -1) {
			fileName = fileName.replaceAll(".xml", "");
		}
		if (fileName.indexOf(".") > -1) {
			fileName = fileName.replaceAll("\\.", "");
		}
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		int end = path.length() - "WEB-INF/classes/".length();
		path = path.substring(1, end);
		path = Global.getConfig("filesavepath");
		String fileDir = path + File.separator + XML_FOLDER_NAME + File.separator
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + File.separator;
		if (StringUtils.notBlank(folderName)) {
			fileDir = path + File.separator + folderName + File.separator;
		}
		File fdir = new File(fileDir);
		fdir.mkdirs();

		FileWriter fileWriter = null;
		try {
			// xmlWriter是用来把XML文档写入字符串的(工具)
			fileWriter = new FileWriter(fileDir + fileName + ".txt", false);
			// 把创建好的XML文档写入字符串
			fileWriter.write(str);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (null != fileWriter) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
	}
}
