package com.lte.admin.car.controller;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * 通用工具类
 */
public class CommonUtil {

	
	public static String uploadFile(String savePath,MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {
//      //上传文件存在的位置
//      String path=request.getSession().getServletContext().getRealPath("uploadImg");
      //获得上传文件的名字
      String filename=file.getOriginalFilename();
      String datestring = new Date().getTime() + "";//自定义文件名称;
//文件名为保存时间加原文件名
 	  filename = datestring+file.getOriginalFilename().toLowerCase();
      String result="";
      //加载上传的文件
      File targetfile=new File(savePath,filename);
      if(!targetfile.exists()){
          //查看目录是否存在，如何不存在就创建这个目录
          targetfile.mkdirs();
      }
      try{
          //保存文件
          file.transferTo(targetfile);
          result = targetfile.toString();
      }catch(Exception e){
    	  result = null;
          System.out.println(e.getMessage());
      }
	return result;
	}
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
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