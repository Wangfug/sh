package com.lte.admin.system.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lte.admin.common.consts.ErrorCodeConst;
import com.lte.admin.common.exception.AdminLteException;
import com.lte.admin.common.utils.Global;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.system.utils.MultipartFileValidator;

/**
 * controller
 * 
 * @author ty
 * @date 2015年1月14日
 */
@Controller
@RequestMapping("system/fileupload")
public class FileController extends BaseController {

	private MultipartFileValidator validator;

	@PostConstruct
	public void init() {
		validator = new MultipartFileValidator();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String fileUploadForm() {
		return "system/fileupload";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String processUpload(@RequestParam("upFile") MultipartFile file, Model model, HttpServletRequest request) {
		String filepath = "";
		validator.validate(file);
		filepath = saveFile(file, request);
		// model.addAttribute("filepath", filepath);
		return filepath;
	}

	@RequestMapping(value = "down", method = RequestMethod.GET)
	public void processDownloadGet(@RequestParam("fileName") String fileName, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		downFile(fileName, fileName, request, response);
	}

	@RequestMapping(value = "down", method = RequestMethod.POST)
	public void processDownload(@RequestParam("fileName") String fileName, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		downFile(fileName, fileName, request, response);
	}

	/**
	 * 下载文件
	 * 
	 * @param oldfilename
	 *            业务保存的文件名，可以只有文件名，也可以是上传后得到的完整路径
	 * @param newfilename
	 *            新的文件名，最好只有文件名
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downFile(String oldfilename, String newfilename, HttpServletRequest request,
			HttpServletResponse response) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 获取项目根目录
			String[] oldpath = null;

			String syspath = Global.getConfig("filesavepath");
			// 文件保存路径
			if (syspath == null) {
				syspath = "";
			} else {
				if (oldfilename.indexOf(syspath) >= 0) {
					oldpath = oldfilename.split(syspath);
				}
			}
			String filepath = "";
			if (oldpath != null) {
				if (oldpath.length == 1) {
					oldfilename = oldpath[0].substring(1, oldpath[0].length());
				} else if (oldpath.length == 2) {
					oldfilename = oldpath[1].substring(1, oldpath[1].length());
				} else if (oldpath.length == 3) {
					oldfilename = oldpath[2].substring(1, oldpath[2].length());
				}
			}

			if (syspath.startsWith("/")) {
				filepath = syspath + System.getProperty("file.separator") + oldfilename;
			} else if (syspath.indexOf(":") > 0) {
				filepath = syspath + System.getProperty("file.separator") + oldfilename;
			} else {
				filepath = request.getSession().getServletContext().getRealPath("")
						+ System.getProperty("file.separator") + syspath + System.getProperty("file.separator")
						+ oldfilename;
			}

			// 获取文件的长度
			long fileLength = new File(filepath).length();

			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(newfilename.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流

			bis = new BufferedInputStream(new FileInputStream(filepath));

			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			throw new AdminLteException(ErrorCodeConst.FILE_NOT_EXISTS, "文件不存在！");
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 保存文件
	/**
	 * 上传文件保存，保存到系统配置的filesavepath下， filesavepath可以是目录名，也可以是硬盘的完整路径，后面不加斜杠。
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	public static String saveFile(MultipartFile file, HttpServletRequest request) {
		String filepath = "";
		if (file.getOriginalFilename().equals("") && file.getSize() == 0l) {
			return filepath;
		}
		try {
			String filename = new Date().getTime() + "";
			String syspath = Global.getConfig("filesavepath");
			// 文件保存路径
			if (syspath == null) {
				syspath = "";
			}
			if (syspath.startsWith("/")) {
				filepath = syspath + System.getProperty("file.separator") + filename + "."
						+ getExtensionName(file.getOriginalFilename());
			} else if (syspath.indexOf(":") > 0) {
				filepath = syspath + System.getProperty("file.separator") + filename + "."
						+ getExtensionName(file.getOriginalFilename());
			} else {
				filepath = request.getSession().getServletContext().getRealPath("")
						+ System.getProperty("file.separator") + syspath + System.getProperty("file.separator")
						+ filename + "." + getExtensionName(file.getOriginalFilename());
			}
			File targetFile = new File(filepath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
		} catch (Exception e) {
			throw new AdminLteException(ErrorCodeConst.FILE_UPLOAD_FAIL, "上传文件失败");
		}
		return filepath;
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

}
