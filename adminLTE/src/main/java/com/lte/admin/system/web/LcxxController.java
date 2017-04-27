package com.lte.admin.system.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.Blsx;
import com.lte.admin.system.service.LcxxService;

/**
 * 流程信息controller
 * 
 * @author jiangt
 */
@Controller
@RequestMapping("system/lcxx")
public class LcxxController extends BaseController {

	@Autowired
	private LcxxService lcxxService;

	/**
	 * 待办页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "dblc", method = RequestMethod.GET)
	public String dblc() {
		return "system/lcdbList";
	}

	/**
	 * 已办页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "yblc", method = RequestMethod.GET)
	public String yblc() {
		return "system/lcybList";
	}

	/**
	 * 获取listjson
	 */
	// @RequiresPermissions("sys:user:view")
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<Blsx> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Blsx> page1 = lcxxService.getLcxxList(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * response.setContentType("application/msexcel;charset=GBK");
		 * 
		 * List<Log> list = logService.getAll();//获取数据
		 * 
		 * String title = "log"; String[] hearders = new String[] {"操作编码",
		 * "详细描述", "执行时间(mm)", "操作系统", "浏览器", "IP","MAC","操作者","操作时间"};//表头数组
		 * String[] fields = new String[] {"operationCode", "description",
		 * "executeTime", "os", "browser",
		 * "ip","mac","creater","createDate"};//People对象属性数组 TableData td =
		 * ExcelUtils.createTableData(list,
		 * ExcelUtils.createTableHeader(hearders),fields); JsGridReportBase
		 * report = new JsGridReportBase(request, response);
		 * report.exportToExcel(title, "admin", td);
		 */
	}
}
