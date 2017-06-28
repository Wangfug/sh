package com.lte.admin.common.web;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lte.admin.common.utils.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.exception.AdminLteException;
import com.lte.admin.common.persistence.Page;

/**
 * 基础控制器 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 * 
 * @description
 * @author ty
 * @date 2014年3月19日
 */
public class BaseController {

	Logger log = LoggerFactory.getLogger(getClass());

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});

		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});

		// Timestamp 类型转换
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Date date = DateUtils.parseDate(text);
				setValue(date == null ? null : new Timestamp(date.getTime()));
			}
		});
	}

	/**
	 * 获取page对象
	 * 
	 * @param request
	 * @return page对象
	 */
	public <T> Page<T> getPage(HttpServletRequest request) {
		int pageNo = 1; // 当前页码
		int pageSize = 20; // 每页行数
		String orderBy = ""; // 排序字段
		String order = ""; // 排序顺序
		if (StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo = Integer.valueOf(request.getParameter("page"));
		if (StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize = Integer.valueOf(request.getParameter("rows"));
		if (StringUtils.isNotEmpty(request.getParameter("sort")))
			orderBy = request.getParameter("sort").toString();
				if(orderBy.indexOf("_")==-1){
					orderBy = parseString.camel2Underline(orderBy);
				}
		if (StringUtils.isNotEmpty(request.getParameter("order")))
			order = request.getParameter("order").toString();
		if (StringUtils.notBlank(orderBy, order)) {
			return new Page<T>(pageNo, pageSize, orderBy, order);
		} else {
			return new Page<T>(pageNo, pageSize);
		}
	}

	/**
	 * 获取easyui分页数据
	 * 
	 * @return map对象
	 */
	public <T> Map<String, Object> getEasyUIData(PageList<T> pageList, HttpServletRequest request) {
		String isExport = request.getParameter("isExport");
		if (Boolean.parseBoolean(isExport)) {
			if (StringUtils.notNull(pageList) && pageList.size() > 0) {
				for (T bean : pageList) {
					Field[] fields = bean.getClass().getDeclaredFields();
					for (Field field : fields) {
						String name = field.getName();
						Type type = field.getType();
						if (type.equals(BigDecimal.class)) {
							BigDecimal dec = BigDecimal.ZERO;
							try {
								dec = (BigDecimal) Reflections.invokeGetter(bean, name);
								BigDecimal newVal = ConvertUtils.getDecimalScale2(dec);
								Reflections.setFieldValue(bean, name, newVal);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", pageList);
		map.put("total", pageList.getPaginator().getTotalCount());
		return map;
	}

	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		String resultViewName = "error/500";
		ex.printStackTrace();
		// 记录日志
		log.error(ex.getMessage(), ex);
		request.setAttribute("ex", ex);
		// 根据不同错误转向不同页面
		if (ex instanceof AdminLteException) {
			resultViewName = "error/err";
			return resultViewName;
		}

		if (ex instanceof UnauthenticatedException) {
			resultViewName = "error/unauth";
			return resultViewName;
		}

		if (ex instanceof UnauthorizedException) {
			resultViewName = "error/unauth";
			return resultViewName;
		}

		String xRequestedWith = request.getHeader("X-Requested-With");
		if (!StringUtils.isEmpty(xRequestedWith)) {
			// ajax请求
			resultViewName = "error/ajax";

		}

		return resultViewName;
	}
}
