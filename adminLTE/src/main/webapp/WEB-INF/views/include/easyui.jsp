<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.lte.admin.common.consts.*" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="currentTime" value="<%=System.currentTimeMillis() %>"/>
<!-- easyui皮肤 -->
<link href="${ctx}/static/plugins/easyui/jquery-easyui-theme/<c:out value="${cookie.themeName.value}" default="default"/>/easyui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/plugins/easyui/jquery-easyui-theme/icon.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/plugins/easyui/icons/icon-all.css" rel="stylesheet" type="text/css" />
<!-- ztree样式 -->
<link href="${ctx}/static/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<!-- 国家城市三级联动 -->
<link href='${ctx}/static/js/worldCity/chosen.min.css' rel='stylesheet'>
<script src="${ctx}/static/plugins/easyui/jquery/jquery-1.11.1.min.js"></script>

<script src="${ctx}/static/plugins/easyui/jquery-easyui-1.3.6/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jquery-easyui-1.3.6/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jquery-easyui-1.3.6/plugins/jquery.messager.js" type="text/javascript"></script>

<!-- jquery扩展 -->
<script src="${ctx}/static/plugins/easyui/release/jquery.jdirk.min.js"></script>

<!-- easyui扩展 -->
<link href="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.progressbar.js"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.slider.js"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.linkbutton.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.validatebox.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combo.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combobox.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.menu.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.searchbox.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.panel.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.window.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.dialog.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.layout.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.tree.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.datagrid.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.treegrid.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combogrid.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combotree.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.tabs.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.theme.js" type="text/javascript"></script>

<!--<script src="${ctx}/static/plugins/easyui/release/jeasyui.extensions.all.min.js"></script>-->


<script src="${ctx}/static/plugins/easyui/icons/jeasyui.icons.all.js" type="text/javascript"></script>
<!--<script src="${ctx}/static/plugins/easyui/release/jeasyui.icons.all.min.js"></script>-->
    
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.icons.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.gridselector.js" type="text/javascript"></script>

<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jquery.toolbar.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jquery.comboicons.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jquery.comboselector.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jquery.portal.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jquery.my97.js" type="text/javascript"></script>    
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.ty.js"></script>

<!-- ztree扩展 -->
<script src="${ctx}/static/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctx}/static/plugins/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>
<!-- 金额转大写 -->
<script src="${ctx}/static/js/common.js?${currentTime}" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx }/static/plugins/easyui/common/other.css"></link>

<script type="text/javascript" src="${ctx}/static/js/order/city.js"></script>

<!-- 全局css样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/global.css" />

<script type="text/javascript">
var $ctxURL  = "${ctx}"+"/";
var $ptzhCommBaseURL = $ctxURL + "myAccount/platformAccount";// 平台账户共通请求路径
var mainTabs  = "#mainTabs";

var mainTabsObj  = $(mainTabs);
//全局的AJAX访问，处理AJAX清求时SESSION超时
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
          //通过XMLHttpRequest取得响应头，sessionstatus
        if(XMLHttpRequest.getResponseHeader){
            var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
            if(sessionstatus=="timeout"){
                //跳转的登录页面
                window.location.replace('${ctx}/a/login');
            }
        }
    }
});

var $GSBZ_ZB= '<%= DictConsts.GSBZ_ZB %>';
var $GSBZ_FB= '<%= DictConsts.GSBZ_FB %>';
var $GSBZ_PT= '<%= DictConsts.GSBZ_PT %>';
$.extend($.fn.validatebox.defaults.rules, {
    number: {
        validator: function(value){
            var rex=/^[1-9]\d*$/;
            if(rex.test(value))
            {
                return true;
            }else
            {
                return false;
            }
        },
        message: '请输入正整数'
    },
    money: {
        validator: function(value){
            var rex=/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d{1})$|^[+]{0,1}(\d+\.\d{2})$/;
            if(rex.test(value))
            {
                return true;
            }else
            {
                return false;
            }
        },
        message: '请输入正确价格，保留两位小数'
    },
    phoneRex: {
        validator: function(value){
            var rex=/^1[3-8]+\d{9}$/;
            //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
            //电话号码：7-8位数字： \d{7,8
            //分机号：一般都是3位数字： \d{3,}
            //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/
            var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            if(rex.test(value)||rex2.test(value))
            {
                return true;
            }else
            {
                return false;
            }
        },
        message: '请输入正确电话或手机格式'
    },
    certificate:{
        validator: function(value){
    //身份证正则表达式(15位)
//身份证正则表达式(18位)
    var rex=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    var rex2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    if(rex.test(value)||rex2.test(value))
    {
        return true;
    }else
    {
        return false;
    }
},
message: '请输入正确的身份证格式'
},
carCode: {
        validator: function(value){
            var rex=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
            if(rex.test(value))
            {
                var checkR=$.ajax({// 数据库比对车牌号是否重复
                    async : false,
                    type : 'post',
                    url : '${ctx}/web/car/checkCarCode',
                    data : {
                        'carCode' : value
                    }
                }).responseText;
                return checkR==="true";
            }else
            {
                return false;
            }
        },
        message: '请输入正确车牌号,且没有录入系统'
    }
});
</script>
