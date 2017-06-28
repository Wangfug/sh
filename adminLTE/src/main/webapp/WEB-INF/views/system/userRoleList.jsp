<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
    <%--<select name="companyCode" id="companyCode">
        <c:forEach var="com" items="${coms}">
            <c:if test="${com.companyCode eq member.companyCode}">
                <option value="${com.companyCode}" selected>${com.companyName}</option>
            </c:if>
            <c:if test="">
                <option value="${com.companyCode}">${com.companyName}</option>
            </c:if>
        </c:forEach>
    </select>
    <select name="deptCode" id="deptCode">
        <c:forEach var="dept" items="${depts}">
            <c:if test="${dept.deptCode eq member.deptCode}">
                <option value="${dept.deptCode}" selected>${com.deptName}</option>
            </c:if>
            <c:if test="">
                <option value="${dept.deptCode}">${com.deptName}</option>
            </c:if>
        </c:forEach>
    </select>--%>
    <table id="ur_dg"></table>
<script type="text/javascript">
//    changeDeptOption($("#companyCode").val());
	var ur_dg;
	ur_dg=$('#ur_dg').datagrid({    
	method: "get",
	url:'${ctx}/system/role/jsona', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'jobCode',
	pagination:false,
	striped:true,
    columns:[[    
		{field:'ck',checkbox:true},
        {field:'id',title:'id',hidden:true,sortable:true,width:100},    
        {field:'jobName',title:'岗位名称',sortable:true,width:100},
        {field:'jobCode',title:'岗位编码',sortable:true,width:100},
        {field:'deptCode',title:'部门编号',sortable:true,width:100,tooltip: true},
        {field:'deptName',title:'部门名称',sortable:true,width:100,tooltip: true}
    ]],
    onLoadSuccess:function(){
    	//获取用户拥有角色,选中
    	$.ajax({
    		async:false,
			type:'get',
			url:"${ctx}/system/user/${userId}/role",
			success: function(data){
				if(data){
					for(var i=0,j=data.length;i<j;i++){
						ur_dg.datagrid('selectRecord',data[i].jobCode);
					}
				} 
			}
		});
    	
    }
	});

	/*function changeDeptOption(companyCode){
        $.ajax({
            async:false,
            type:'POST',
//            data:{companyCode:companyCode},
            contentType:'application/json;charset=utf-8',	//必须
            url:"/system/user/"+companyCode+"/changeDeptOption",
            success: function(data){
                console.log(data);
               /!* if(data=='success'){
                    parent.$.messager.show({ title : "提示",msg: "操作成功！", position: "bottomRight" });
                }else{
                    $.easyui.messager.alert(data);
                }*!/
            }
        });
    }*/

//保存用户角色
function saveUserRole(){
	var newRoleList=[];
	var data=ur_dg.datagrid('getSelections');
	//所选的角色列表
	for(var i=0,j=data.length;i<j;i++){
		newRoleList.push(data[i].jobCode);
	}
	$.ajax({
		async:false,
		type:'POST',
		data:JSON.stringify(newRoleList),
		contentType:'application/json;charset=utf-8',	//必须
		url:"${ctx}/system/user/${userId}/updateRole",
		success: function(data){
			if(data=='success'){
				parent.$.messager.show({ title : "提示",msg: "操作成功！", position: "bottomRight" });
			}else{
				$.easyui.messager.alert(data);
			}
		}
	});
}
</script>
</body>
</html>