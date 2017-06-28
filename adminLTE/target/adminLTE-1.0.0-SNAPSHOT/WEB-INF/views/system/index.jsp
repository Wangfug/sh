<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lte.admin.common.consts.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>舜昊租车平台</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<%@ include file="/WEB-INF/views/include/validation.jsp"%>
<c:set var="GSBZ_PT" value="<%= DictConsts.GSBZ_PT %>"/>
<c:set var="GSBZ_FB" value="<%= DictConsts.GSBZ_FB %>"/>
<c:set var="GSBZ_ZB" value="<%= DictConsts.GSBZ_ZB %>"/>
<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/artTemplate/dist/template.js"></script>

<!--导入首页启动时需要的相应资源文件(首页相应功能的 js 库、css样式以及渲染首页界面的 js 文件)-->
<script src="${ctx}/static/plugins/easyui/common/index.js" type="text/javascript"></script>
<link href="${ctx}/static/plugins/easyui/common/index.css" rel="stylesheet" />
<link href="${ctx}/static/css/style.css?${currentTime}" rel="stylesheet" />
<script src="${ctx}/static/plugins/easyui/common/index-startup.js"></script>
<script src="${ctx}/static/js/common.js?${currentTime}"></script>
	<script>
	 if (top.location != this.location) {
				top.location = this.location;
		}
	</script>
</head>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<link rel="bookmark" href="${ctx}/static/images/favicon.ico" type="image/x-icon"　/>

<body>
	<!-- 容器遮罩 -->
    <div id="maskContainer">
        <div class="datagrid-mask" style="display: block;"></div>
        <div class="datagrid-mask-msg" style="display: block; left: 50%; margin-left: -52.5px;">
            正在加载...
        </div>
    </div>
    <div id="mainLayout" class="easyui-layout hidden" data-options="fit: true">
        <div id="northPanel" data-options="region: 'north', border: false" style="height: 80px; overflow: hidden;">
            <div id="topbar" class="top-bar">
                <div class="top-bar-left">
                    <span style="color: #0068B7"><img src="${ctx }/static/images/logo-3-5.png" height="52px"/></span>
                </div>
                <div class="top-bar-right">
                    <div id="timerSpan"></div>
                    <div id="themeSpan">
                        <a id="btnHideNorth" class="easyui-linkbutton" data-options="plain: true, iconCls: 'layout-button-up'"></a>
                    </div>
                </div>
            </div>
            <div id="toolbar" class="panel-header panel-header-noborder top-toolbar">
                <div id="infobar">
                    <span class="icon-hamburg-user" style="padding-left: 25px; background-position: left center;">
                       <shiro:principal property="memberName"/>(<shiro:principal property="memberCode"/>)，您好 
                    </span>
                </div>
               
                <div id="buttonbar">
                    <span>更换皮肤：</span>
                    <select id="themeSelector"></select>
                    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_set'" iconCls="icon-standard-cog">系统</a>  
                    <div id="layout_north_set">
                        <div id="btnChangePWD" data-options="iconCls:'key'">修改密码</div>
                    	<div id="btnFullScreen" data-options="iconCls:'key'">全屏切换</div>
						<div id="btnExit" data-options="iconCls:'logout'">退出系统</div>
					</div>
                    <a id="btnShowNorth" class="easyui-linkbutton" data-options="plain: true, iconCls: 'layout-button-down'" style="display: none;"></a>
                </div>
            </div>
        </div>

        <div data-options="region: 'west', title: '菜单导航栏', iconCls: 'icon-standard-map', split: true, minWidth: 200, maxWidth: 400" style="width: 220px; padding: 1px;">
            <div id="myMenu" class="easyui-accordion" data-options="fit:true,border:false">
            <script id="menu" type="text/html">
			{{each data as p_permission}}
				{{if (p_permission.pid==null)}}
   				 <div title="{{p_permission.name }}" style="padding: 5px;" data-options="border:false,iconCls:'{{p_permission.icon }}'">
					<div>
					{{each data as c_permission}}	
						{{if (c_permission.pid==p_permission.id)}}
						<a id="btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'{{c_permission.icon }}'" style="width:98%;margin-bottom:5px;" onclick="window.mainpage.mainTabs.addModule('{{c_permission.name}}','{{c_permission.url }}','{{c_permission.icon }}')">{{c_permission.name}}</a>
						{{/if}}	
					{{/each}}
					</div>
				</div>
				{{/if}}	
			{{/each}}
			</script>
			</div>
        </div>

        <div data-options="region: 'center'">
            <div id="mainTabs_tools" class="tabs-tool">
                <table>
                    <tr>
                        <td><a id="mainTabs_jumpHome" class="easyui-linkbutton easyui-tooltip" title="跳转至主页选项卡" data-options="plain: true, iconCls: 'icon-hamburg-home'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
						<td><a id="mainTabs_toggleAll" class="easyui-linkbutton easyui-tooltip" title="展开/折叠面板使选项卡最大化" data-options="plain: true, iconCls: 'icon-standard-arrow-out'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_refTab" class="easyui-linkbutton easyui-tooltip" title="刷新当前选中的选项卡" data-options="plain: true, iconCls: 'icon-standard-arrow-refresh'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_closeTab" class="easyui-linkbutton easyui-tooltip" title="关闭当前选中的选项卡" data-options="plain: true, iconCls: 'icon-standard-application-form-delete'"></a></td>
                    </tr>
                </table>
            </div>
            <div id="mainTabs" class="easyui-tabs" data-options="fit: true, border: false, showOption: true, enableNewTabMenu: true, tools: '#mainTabs_tools', enableJumpTabMenu: true">
                
                </div>
            </div>
        </div>

        <div data-options="region: 'east', title: '日历', iconCls: 'icon-standard-date', split: true,collapsed: true, minWidth: 160, maxWidth: 500" style="width: 220px;">
            <div id="eastLayout" class="easyui-layout" data-options="fit: true">
                <div data-options="region: 'north', split: false, border: false" style="height: 220px;">
                    <div class="easyui-calendar" data-options="fit: true, border: false"></div>
                </div>
                <div id="linkPanel" data-options="region: 'center', border: false, title: '通知', iconCls: 'icon-hamburg-link', tools: [{ iconCls: 'icon-hamburg-refresh', handler: function () { window.link.reload(); } }]">
                    
                </div>
            </div>
        </div>

    </div>
<div id="dlg"></div>  
<script>



$.ajax({
	async:false,
	type:'get',
	url:"${ctx}/system/permission/i/json",
	success: function(data){
		var menuData={data:data};
		var html = template('menu', menuData);
		$('#myMenu').html(html);
	}
});

$('.easyui-linkbutton').on('click', function(){    
	$('.easyui-linkbutton').linkbutton({selected:false}); 
    $(this).linkbutton({selected:true});  
});   




$(function(){
	window.mainpage.mainTabs.addModule('主页','${accountInfoUrl}','icon-hamburg-home',true, false);
	
	$("#btnChangePWD").click(function () {
		d = $("#dlgpwd").dialog({   
		    title: '修改密码',    
		    width: 350,    
		    height: 200,    
		    href:'${ctx}/system/user/updatePwd',
		    maximizable:true,
		    modal:true,
		    buttons:[{
				text:'确认',
				handler:function(){
					$("#mainform").submit(); 
				}
			},{
				text:'取消',
				handler:function(){
						d.panel('close');
					}
			}]
		});
    });
})


</script>
    <div id="dlgpwd"></div>  
</body>
</html>
