
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.entity.TbaseAttachment;
import com.lte.admin.other.service.TbaseAttachmentService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/tbaseAttachment")
public class TbaseAttachmentController extends BaseController  {
	@Resource
	private TbaseAttachmentService tbaseAttachmentService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(){
		return "other/tbaseAttachmentList";
	}

	/**
	 * 增加附件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		MemberLogin user = UserUtil.getCurrentUser();
		TbaseAttachment tbaseAttachment = getEntity4Request(request);
		if(tbaseAttachment.getId()!=null){
			tbaseAttachment.setLastTime(new Timestamp(System.currentTimeMillis()));
			tbaseAttachment.setLastBy(user.getId());
			tbaseAttachmentService.update(tbaseAttachment);
		}else{
			tbaseAttachment.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tbaseAttachment.setCreateBy(user.getId());
			tbaseAttachment.setLastTime(new Timestamp(System.currentTimeMillis()));
			tbaseAttachment.setLastBy(user.getId());
			tbaseAttachmentService.save(tbaseAttachment);
		}
		return "success";
	}

	/**
	 * 获取附件列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTbaseAttachmentList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getTbaseAttachmentList(HttpServletRequest request){
		Page<TbaseAttachment> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<TbaseAttachment> page1 = tbaseAttachmentService.getList(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建或修改附件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		TbaseAttachment tbaseAttachment = null;
		if(StringUtils.isNotBlank(id))
			tbaseAttachment = tbaseAttachmentService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("tbaseAttachment",tbaseAttachment);
		return "other/tbaseAttachmentForm";
	}

	public TbaseAttachment getEntity4Request(HttpServletRequest request) {
			TbaseAttachment entity=new TbaseAttachment();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("state"))){
			entity.setState(Integer.valueOf(request.getParameter("state")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(Long.valueOf(request.getParameter("lastBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("filename"))){
			entity.setFilename(request.getParameter("filename"));
			}
			if(StringUtils.isNotBlank(request.getParameter("filepath"))){
			entity.setFilepath(request.getParameter("filepath"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("fileSize"))){
			entity.setFileSize(Long.valueOf(request.getParameter("fileSize")));
			}
			return entity;
	}
}