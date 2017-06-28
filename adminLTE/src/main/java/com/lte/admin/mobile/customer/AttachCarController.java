package com.lte.admin.mobile.customer;

import cn.jiguang.common.utils.StringUtils;
import com.lte.admin.car.entity.CarAttachApply;
import com.lte.admin.car.service.CarAttachApplyService;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/customer")
public class AttachCarController extends BaseController {
    @Resource
    private CarAttachApplyService carAttachApplyService;
    @Resource
    private CustomerService customerService;
    /**
     * 提交加盟信息
     * @param token
     * @return
     */
    @RequestMapping(value = "applyForJoining", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String applyForJoining(String token,String driveLicImg,String carAttachDetail,String carInfo,Long carShop){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{

            Map<String,Object> filter = new HashMap<String,Object>();
            filter.put("password",token);
            Customer customer = customerService.getOneCustomerByMobile(filter);

            if(StringUtils.isNotEmpty(driveLicImg)&&StringUtils.isNotEmpty(carAttachDetail)&&StringUtils.isNotEmpty(carInfo) && (null != customer)){
                driveLicImg = driveLicImg.replaceAll("&quot;","\\\"");
                carAttachDetail = carAttachDetail.replaceAll("&quot;","\\\"");
                carInfo = carInfo.replaceAll("&quot;","\\\"");
                CarAttachApply carAttachApply = new CarAttachApply();
                carAttachApply.setCarAttachDetail(carAttachDetail);
                carAttachApply.setCarInfo(carInfo);
                carAttachApply.setCreateTime(new Timestamp(System.currentTimeMillis()));
                carAttachApply.setCustomerId(customer.getId());
                carAttachApply.setDriveLicImg(driveLicImg);
                carAttachApply.setState(0);//状态：0-未审核/1-已审核通过/2-审核未通过
                carAttachApply.setCarShop(carShop);
                carAttachApplyService.saveCarAttachApply(carAttachApply);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("已提交审核，请耐心等待");
            }else{
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("申请失败，审核信息提供不全");
            }
        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setStatus(2);
            serviceResponse.setInfo("申请异常，请仔细确认");
        }
        return serviceResponse.objectToJson();
    }
}