package com.lte.admin.mobile.customer;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.other.entity.Activity;
import com.lte.admin.other.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王福桂 on 2017/6/9.
 * 公开的数据
 */
@Controller
@RequestMapping("mobile/public")
public class ActForPublicController extends BaseController {
    @Resource
    private ActivityService activityService;
    /*
    根据类型查询活动
     */
    @RequestMapping(value = "getACTList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getACTList(String type,HttpServletRequest request,String cityName){
        ServiceResponse sr = new ServiceResponse();
        try{
            Page<Activity> page = getPage(request);
            page.setOrder("DESC");
            page.setOrderBy("main_page");
            Map<String,Object> filter = new HashMap<String,Object>();
            filter.put("activityType",type);
            filter.put("state","80001");
            filter.put("cityName",cityName);
            PageList<Map> page1 = activityService.getList1(page,filter);
            sr.setStatus(1);
            sr.setInfo("获取活动列表成功");
            sr.setData(page1);
        }catch(Exception e){
            sr.setInfo("获取活动列表失败");
        }
        return sr.objectToJson();
    }
    /*
   根据类型查询活动
    */
    @RequestMapping(value = "getOneACT",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getOneACT(Long id){
        ServiceResponse sr = new ServiceResponse();
        try{
            Activity act = activityService.get(id);
            sr.setData(act);
            sr.setStatus(1);
            sr.setInfo("获取活动成功");
        }catch(Exception e){
            sr.setInfo("获取活动失败");
        }
        return sr.objectToJson();
    }

    /**
     * 轮播图
     */
    @RequestMapping(value = "getHotACTList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getHotACTList(String cityName){
        ServiceResponse sr = new ServiceResponse();
        try{
            Map<String,Object> filter = new HashMap<String,Object>();
//            filter.put("activityType",type);
            filter.put("state","80001");
            filter.put("cityName",cityName);
            List<Map> page1 = activityService.getHotACTList(filter);
            sr.setStatus(1);
            sr.setInfo("获取活动列表成功");
            sr.setData(page1);
        }catch(Exception e){
            sr.setInfo("获取活动列表失败");
        }
        return sr.objectToJson();
    }
    /*
  根据类型查询活动
   */
    @RequestMapping(value = "getHotOneACT",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getHotOneACT(String type,String cityName){
        ServiceResponse sr = new ServiceResponse();
        try{
            Map<String,Object> filter = new HashMap<String,Object>();
            filter.put("activityType",type);
            filter.put("state","80001");
            filter.put("cityName",cityName);
            Activity act = activityService.getHotOneACT(filter);
            sr.setData(act);
            sr.setStatus(1);
            sr.setInfo("获取活动成功");
        }catch(Exception e){
            sr.setInfo("获取活动失败");
        }
        return sr.objectToJson();
    }
}
