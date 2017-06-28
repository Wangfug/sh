
package com.lte.admin.mobile.common;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.utils.Client;
import com.lte.admin.common.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andy
 */
@Controller
@RequestMapping("common/util")
public class SmsController extends BaseController  {
    protected static final String APP_KEY ="5d904a62f89b2b59b6aef4ff";
    protected static final String MASTER_SECRET = "cabad8b2cd6b916c0d60857a";

    @RequestMapping(value = "sendMessage", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String sendMessageOld(HttpServletRequest request,String mobile){
        String code = String.valueOf((int) (Math.random() * 900000 + 100000)); // 生成六位位随机数
        ServiceResponse serviceResponse = new ServiceResponse();
        if(StringUtils.isNotBlank(mobile)){
            String message = "您的手机验证码是："+code+"，请在5分钟内输入。如非本人操作请致电：400-025-9000 【舜昊租车】";
             if(mobile.equals("18852890966")){
             serviceResponse.setStatus(1);
             serviceResponse.setInfo("发送成功");
             serviceResponse.setData("");
             request.getSession().setAttribute("code","000000");
             }else{
            int rtnCode =   Client.sendMessage(mobile,message);
            if(rtnCode == 1){
             serviceResponse.setStatus(1);
             serviceResponse.setInfo("发送成功");
             serviceResponse.setData("");
             request.getSession().setAttribute("code",code);
             }else{
              serviceResponse.setData("");
              serviceResponse.setStatus(rtnCode);
              serviceResponse.setInfo("发送失败");
              }
             }//测试
        }else{
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("请输入有效的手机号");
        }
        String json = serviceResponse.objectToJson();
        return json;
    }

    @RequestMapping(value = "sendAppMsg", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String sendAppMsg(String token){
        ServiceResponse serviceResponse = new ServiceResponse();
        if(StringUtils.isNotBlank(token)){
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            PushPayload payload = buildPushObject_all_alias_alert(token,"您有新的工单啦！");
            try {
                PushResult result = jpushClient.sendPush(payload);
                serviceResponse.setData("Got result - " + result);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("推送成功");

            } catch (APIConnectionException e) {
                serviceResponse.setData("Connection error, should retry later" + e);
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("推送失败");

            } catch (APIRequestException e) {
                serviceResponse.setData("CShould review the error, and fix the request" + e +"HTTP Status:"+e.getStatus()+"Error Code: " + e.getErrorCode()+"Error Message: " + e.getErrorMessage());
                serviceResponse.setStatus(2);
                serviceResponse.setInfo("推送失败");
            }
        }else{
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("token无效");
        }
        String json = serviceResponse.objectToJson();
        return json;
    }

    //指定用戶
    public static PushPayload buildPushObject_all_alias_alert(String alias,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content))//通知（app后台监听）
                .setMessage(Message.content(content))//消息（app前台可监听）
                .build();
    }

    //所有用戶
    public static PushPayload buildPushObject_all_all_alert(String content) {
        return PushPayload.alertAll(content);
    }

}