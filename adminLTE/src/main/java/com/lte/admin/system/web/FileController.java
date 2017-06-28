package com.lte.admin.system.web;

import com.lte.admin.common.consts.ErrorCodeConst;
import com.lte.admin.common.exception.AdminLteException;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.utils.Global;
import com.lte.admin.common.utils.MathUtils;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.system.utils.MultipartFileValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	@RequestMapping(value = "upload", method = {RequestMethod.POST})
	@ResponseBody
	public String processUpload(MultipartFile file, HttpServletRequest request) throws Exception {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			validator.validate(file);
			boolean isLunbo = false;
			String lunbo = request.getParameter("lunbo");
			if("lunbo".equals(lunbo)){
				isLunbo = true;
			}
			String j = saveFile(file, request,isLunbo);
			serviceResponse.setData(j);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("上传成功");
		}catch (Exception e){
			serviceResponse.setInfo("上传失败");
		}
		return serviceResponse.objectToJson();
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
//	public static Map<String,Object> saveFile(MultipartFile file, HttpServletRequest request) {
		public static String saveFile(MultipartFile file, HttpServletRequest request,boolean lunbo) {
		Map<String,Object> j = new HashMap<String,Object>();
		String state = "0";
		String filename1 = "";
		Long filsize = 0l;
		String filepath1 = "";
		String filepath = "";
		if (file.getOriginalFilename().equals("") && file.getSize() == 0l) {
//			j.put("filpath",filepath);
//			j.put("filname",filname);
//			j.put("filsize",filsize);
			j.put("state",state);
			return "";
		}
		try {
			String filename = new Date().getTime() + "";
			String syspath = Global.getConfig("filesavepath");
			filename1 = file.getOriginalFilename();
			filsize = file.getSize();
			// 文件保存路径
			if (syspath == null) {
				syspath = "";
			}
			filepath1 = filename + "." + getExtensionName(file.getOriginalFilename());
			if (syspath.startsWith("/")) {
				filepath = syspath + System.getProperty("file.separator") + filepath1;
			} else if (syspath.indexOf(":") > 0) {
				filepath = syspath + System.getProperty("file.separator") +filepath1;
			} else {
				filepath = request.getSession().getServletContext().getRealPath("")
						+ System.getProperty("file.separator") + syspath + System.getProperty("file.separator")
						+ filepath1;
			}
			File targetFile = new File(filepath);
			if(lunbo){
				InputStream is = file.getInputStream();
				BufferedImage bimage = ImageIO.read(is);
				int width = bimage.getWidth();
				int height = bimage.getHeight();
				double bili = 0.0;
				if(height!=0){
					//16/9
					bili = 	MathUtils.div(width,height);
					bili = MathUtils.round(bili,2);
				}
				if(bili!=1.78){
					return "bili";
				}

			}

			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			state = "1";
		} catch (Exception e) {
			throw new AdminLteException(ErrorCodeConst.FILE_UPLOAD_FAIL, "上传文件失败");
		}
		j.put("filpath",filepath);
		j.put("filname",filename1);
		j.put("filsize",filsize);
		j.put("state",state);
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

	/**
	 * 删除单个文件
	 * @param   sPath    被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	@RequestMapping(value = "deleteOne", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath.trim());
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * @param   sPath 被删除目录的文件路径
	 * @return  目录删除成功返回true，否则返回false
	 */
	@RequestMapping(value = "deleteAllFile", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public boolean deleteDirectory(String sPath) {
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		//删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			//删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) break;
			} //删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) break;
			}
		}
		if (!flag) return false;
		//删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}


}
