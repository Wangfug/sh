package com.lte.admin.common.utils;

import com.sun.jersey.api.client.Client;
		import com.sun.jersey.api.client.ClientResponse;
		import com.sun.jersey.api.client.WebResource;
		import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
		import com.sun.jersey.core.util.MultivaluedMapImpl;
		import java.util.logging.Level;
		import java.util.logging.Logger;
		import javax.ws.rs.core.MediaType;

		import org.json.JSONException;
		import org.json.JSONObject;
/**
 *
 * @author Administrator
 */
public class SmsUtil {
	public static int sendMessage(String mobile, String message) {
		SmsUtil api = new SmsUtil();
		String apiKey="key-0e9e53592f174f2963d16160c51afba7";
		String url="http://sms-api.luosimao.com/v1/send.json";
//		String mobile = "";
//		String message ="您的验证码是123456，请在页面中提交验证码完成验证。【舜昊租车】";
		String httpResponse =  api.testSend(apiKey,url,mobile,message);
		int rtnCode = 0;
		try {
			JSONObject jsonObj = new JSONObject( httpResponse );
			int error_code = jsonObj.getInt("error");
			String error_msg = jsonObj.getString("msg");
			if(error_code==0){
				System.out.println("Send message success:");
				rtnCode = 1;
			}else{
				rtnCode = error_code;
				System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
			}
		} catch (JSONException ex) {
			Logger.getLogger(SmsUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rtnCode;
		/*httpResponse =  api.testStatus();
		try {
			JSONObject jsonObj = new JSONObject( httpResponse );
			int error_code = jsonObj.getInt("error");
			if( error_code == 0 ){
				int deposit = jsonObj.getInt("deposit");
				System.out.println("Fetch deposit success :"+deposit);
			}else{
				String error_msg = jsonObj.getString("msg");
				System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
			}
		} catch (JSONException ex) {
			Logger.getLogger(MessageUtil.class.getName()).log(Level.SEVERE, null, ex);
		}*/
	}
//error_code对应的错误信息
//-10	验证信息失败	检查api key是否和各种中心内的一致，调用传入是否正确
//-11	用户接口被禁用	滥发违规内容，验证码被刷等，请联系客服解除
//-20	短信余额不足	进入个人中心购买充值
//-30	短信内容为空	检查调用传入参数：message
//-31	短信内容存在敏感词	接口会同时返回  hit 属性提供敏感词说明，请修改短信内容，更换词语
//-32	短信内容缺少签名信息	短信内容末尾增加签名信息eg.【公司名称】
//-33	短信过长，超过300字（含签名）	调整短信内容或拆分为多条进行发送
//-34	签名不可用	在后台 短信->签名管理下进行添加签名
//-40	错误的手机号	检查手机号是否正确
//-41	号码在黑名单中	号码因频繁发送或其他原因暂停发送，请联系客服确认
//-42	验证码类短信发送频率过快	前台增加60秒获取限制
//-50	请求发送IP不在白名单内	查看触发短信IP白名单的设置

	//发送验证码
	private String testSend(String apiKey,String url,String mobile,String message){
		// just replace key here
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(
				"api",apiKey));
		WebResource webResource = client.resource(
				url);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile", mobile);
		formData.add("message", message);
		ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
				post(ClientResponse.class, formData);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		//System.out.print(textEntity);
		//System.out.print(status);
		return textEntity;
	}

	//查询余额等信息
	private String testStatus(){
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(
				"api","key-0e9e53592f174f2963d16160c51afba7"));
		WebResource webResource = client.resource( "http://sms-api.luosimao.com/v1/status.json" );
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		ClientResponse response =  webResource.get( ClientResponse.class );
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		//System.out.print(status);
		//System.out.print(textEntity);
		return textEntity;
	}
}