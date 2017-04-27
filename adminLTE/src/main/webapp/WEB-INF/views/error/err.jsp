<%@ page import="com.lte.admin.common.exception.AdminLteException" %>
<%@ page contentType="application/json;charset=UTF-8" language="java"%>
<%Exception e = (Exception) request.getAttribute("ex");
if(e != null ){
	if(e instanceof XdptException){
		%><%=((XdptException)e).getErrorMsg()%><%
	}else{
		%><%=e.getMessage()%><%
	}
}
%>
