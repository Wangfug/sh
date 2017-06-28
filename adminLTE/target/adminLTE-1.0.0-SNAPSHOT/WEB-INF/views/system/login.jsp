<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="currentTime" value="<%=System.currentTimeMillis() %>"/>
<%
String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
request.setAttribute("error", error);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>舜昊租车平台</title>
	<script src="${ctx}/static/plugins/easyui/jquery/jquery-1.11.1.min.js"></script>	
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style_login.css?${currentTime}" />
	<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<link rel="bookmark" href="${ctx}/static/images/favicon.ico" type="image/x-icon"　/>
</head>
	<script>
	 if (top.location != this.location) {
				top.location = this.location;
		}
	</script>
<body>
<div class="bg"></div>
<div class="login_box">
  <div class="name">舜昊租车</div>
  <div class="left">
    <div class="login_frame">
      <div class="hyc">欢迎您登录!</div>
      <div id="err" class="warning" style="display:none"> <img src="${ctx}/static/images/Unknown.png" alt=".."/> 帐号或密码错误，请重试<span></span> </div>
      <div class="LoginWindow">
      <form class="login" id="loginForm" action="${ctx}/a/login" method="post">
        <div>
          
            <p>
              <label for="login">用户名:</label>
              <input type="text" id="username" name="username" value=""/>
            </p>
            <p>
              <label for="password">密　码:</label>
              <input type="password" id="password" name="password" value=""/>
            </p>
            <p class="login_main_submit">
              <button onclick="" class="login-button">登&nbsp;&nbsp;录</button>
            </p>
          
        </div>
         <!-- 
        <div class="rememberPw">
          <label>
             <input type="checkbox" name="rememberMe" value="true"/>
            &nbsp;记住账号 </label> 
           <span> <a href="#">忘记密码？</a> </span> </div>
           -->
          </form>
      </div>
    </div>
    <div class="kf">
      <p>若登录出现问题，请拨打服务热线进行
        咨询：<span>400-639-9988</span></p>
    </div>
  </div>
  <div class="right"><img src="${ctx}/static/images/xd_gg.png" alt="舜昊租车系统"/></div>
</div>
<script>
$(function(){
	$("#loginForm").submit(function(){
		if($("#username").val()==""){
			alert("请输入用户名");
			return false;
		}
		if($("#password").val()==""){
			alert("请输入密码");
			return false;
		}
	})
})
</script>
	<c:choose>
		<c:when test="${error eq 'org.apache.shiro.authc.UnknownAccountException'}">
			<script>
			$('#err').show();
			</script>
		</c:when>
		<c:when test="${error eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
			<script>
			$('#err').show();
			</script>
		</c:when>
	</c:choose>
</body>
</html>
