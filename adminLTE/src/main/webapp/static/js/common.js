$(document).unbind('keydown').bind(
		'keydown',
		function(event) {
			var doPrevent = false;
			if (event.keyCode === 8) {
				var d = event.srcElement || event.target;
				if ((d.tagName.toUpperCase() === 'INPUT' && (d.type
						.toUpperCase() === 'TEXT'
						|| d.type.toUpperCase() === 'PASSWORD'
						|| d.type.toUpperCase() === 'FILE'
						|| d.type.toUpperCase() === 'SEARCH'
						|| d.type.toUpperCase() === 'EMAIL'
						|| d.type.toUpperCase() === 'NUMBER' || d.type
						.toUpperCase() === 'DATE'))
						|| d.tagName.toUpperCase() === 'TEXTAREA') {
					doPrevent = d.readOnly || d.disabled;
				} else {
					doPrevent = true;
				}
			}

			if (doPrevent) {
				event.preventDefault();
			}
		});

/**
 * 金额大小写转换
 */

function atoc(numberValue) {
	if (isEmpty(numberValue)) {
		return "";
	}
	var numberValue = new String(Math.round(numberValue * 100)); // 数字金额
	var chineseValue = ""; // 转换后的汉字金额
	var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字
	var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位
	var len = numberValue.length; // numberValue 的字符串长度
	var Ch1; // 数字的汉语读法
	var Ch2; // 数字位的汉字读法
	var nZero = 0; // 用来计算连续的零值的个数
	var String3; // 指定位置的数值
	if (len > 15) {
		$.easyui.messager.alert("超出计算范围");
		return "";
	}
	if (numberValue == 0) {
		chineseValue = "零元整";
		return chineseValue;
	}
	String2 = String2.substr(String2.length - len, len); // 取出对应位数的STRING2的值
	for (var i = 0; i < len; i++) {
		String3 = parseInt(numberValue.substr(i, 1), 10); // 取出需转换的某一位的值
		if (i != (len - 3) && i != (len - 7) && i != (len - 11)
				&& i != (len - 15)) {
			if (String3 == 0) {
				Ch1 = "";
				Ch2 = "";
				nZero = nZero + 1;
			} else if (String3 != 0 && nZero != 0) {
				Ch1 = "零" + String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			} else {
				Ch1 = String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			}
		} else { // 该位是万亿，亿，万，元位等关键位
			if (String3 != 0 && nZero != 0) {
				Ch1 = "零" + String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			} else if (String3 != 0 && nZero == 0) {
				Ch1 = String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			} else if (String3 == 0 && nZero >= 3) {
				Ch1 = "";
				Ch2 = "";
				nZero = nZero + 1;
			} else {
				Ch1 = "";
				Ch2 = String2.substr(i, 1);
				nZero = nZero + 1;
			}
			if (i == (len - 11) || i == (len - 3)) { // 如果该位是亿位或元位，则必须写上
				Ch2 = String2.substr(i, 1);
			}
		}
		chineseValue = chineseValue + Ch1 + Ch2;
	}
	if (String3 == 0) { // 最后一位（分）为0时，加上“整”
		chineseValue = chineseValue + "整";
	}
	return chineseValue;
};

/**
 * 根据公司代码获取部门下拉列功能
 * 
 * @author yanzai
 * @param gsdm
 *            公司代码ID
 * @param selectId
 *            控件ID
 */
function commLoadBmBygsdm(gsdm, selectId, height) {
	if (isEmpty(gsdm)) {
		return;
	}
	if (isEmpty(selectId)) {
		return;
	}
	var panelHeight = 100;
	if (!isEmpty(height)) {
		panelHeight = height;
	}
	$.ajax({
		type : "get",
		url : $ptzhCommBaseURL + "/getBmListBygsdm/" + gsdm,
		success : function(data) {
			$('#' + selectId).combobox({
				data : data,
				valueField : 'bm01',
				textField : 'bm02',
				editable : false,
				panelHeight : panelHeight,
			});
		}
	});
};

/**
 * 判断字符串是否为空
 * 
 * @author yanzai
 * @param str
 * @returns {Boolean}
 */
function isEmpty(str) {
	if (str != "" && str != null && str != undefined) {
		return false;
	} else {
		return true;
	}
}

/**
 * 格式获取当前日期的年月日
 * 
 * @param partten
 * @returns {Boolean}
 * @auther yanzai
 * 
 */
Date.prototype.format = function(partten, flag) {
	if (flag) {
		if (partten == null || partten == '') {
			partten = 'Y-m-d';
		}
		var y = this.getFullYear();
		var m = this.getMonth() + 1;
		var d = this.getDate();
		var r = partten.replace(/y+/gi, y);
		r = r.replace(/m+/gi, (m < 10 ? "0" : "") + m);
		r = r.replace(/d+/gi, (d < 10 ? "0" : "") + d);
		return r;
	} else {
		var o = {
			"M+" : this.getMonth() + 1, // month
			"d+" : this.getDate(), // day
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
			"H+" : this.getHours(), // 小时
			"h+" : this.getHours(), // hour
			"m+" : this.getMinutes(), // minute
			"s+" : this.getSeconds(), // second
			"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
			"S" : this.getMilliseconds()
		// millisecond
		}

		if (/(y+)/i.test(partten)) {
			partten = partten.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(partten)) {
				partten = partten.replace(RegExp.$1,
						RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
								.substr(("" + o[k]).length));
			}
		}
		return partten;
	}
}

/**
 * 判断字符串是否以指定的字符串结尾
 * 
 * @param endStr
 * @returns {Boolean}
 * @auther yanzai
 * 
 */
String.prototype.endWith = function(endStr) {
	var d = this.length - endStr.length;
	return (d >= 0 && this.lastIndexOf(endStr) == d)
}

/**
 * 待办审批操作共用处理
 * 
 * @author yanzai
 * @param taskId
 * @param url
 */
function todoSpHandle(taskId, url, title) {
	if (!isEmpty(taskId) && !isEmpty(url)) {
		var jq = top.jQuery;
		if (!jq(mainTabs).tabs('exists', title)) {
			var href = "";
			if (url.endWith("/")) {
				href = $ctxURL + url + taskId;
			} else {
				href = $ctxURL + url + "/" + taskId;
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ href + '" style="width:100%;height:100%;"></iframe>';
			jq(mainTabs).tabs('add', {
				id : taskId,
				title : title,
				content : content,
				closable : true,
				selected : true,
			});
		} else {
			jq(mainTabs).tabs('select', title);
			jq(mainTabs).tabs('refresh', title);
		}
		return;
	} else {
		$.easyui.messager.alert("请求参数不正确！");
		return;
	}
}

/**
 * 已办查看共用处理
 * 
 * @author yanzai
 */
function ybView(taskId, bizKey, title) {
	if (!isEmpty(bizKey)) {
		var jq = top.jQuery;
		if (!jq(mainTabs).tabs('exists', title)) {
			var href = $ctxURL + "workflow/redirectToJsp/" + bizKey + "/"
					+ taskId;
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ href + '" style="width:100%;height:100%;"></iframe>';
			jq(mainTabs).tabs('add', {
				id : taskId,
				title : title,
				content : content,
				closable : true,
				selected : true,
			});
		} else {
			jq(mainTabs).tabs('select', title);
			jq(mainTabs).tabs('refresh', title);
		}
		return;
	} else {
		$.easyui.messager.alert("请求参数不正确！");
		return;
	}
};

/**
 * 新开一个选项卡共通方法
 * 
 * @author yanzai
 * @param title
 *            标题文本，非空
 * @param url
 *            加载远程数据的URL，非空
 * @param tab_id
 *            ID属性
 */
function addNewTab(title, url, tab_id, isNew) {
	if (!isEmpty(title) && !isEmpty(url)) {
		if (isEmpty(tab_id)) {
			tab_id = Math.random() + "";
		}
		var jq = top.jQuery;
		if (!isEmpty(isNew) || isNew == true) {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			jq(mainTabs).tabs('add', {
				id : tab_id,
				title : title,
				content : content,
				closable : true,
				selected : true,
			});
		} else {
			if (!jq(mainTabs).tabs('exists', title)) {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'
						+ url + '" style="width:100%;height:100%;"></iframe>';
				jq(mainTabs).tabs('add', {
					id : tab_id,
					title : title,
					content : content,
					closable : true,
					selected : true,
				});
			} else {
				jq(mainTabs).tabs('select', title);
			}
		}
	}
};

/**
 * 关闭tab当前选项卡共通方法
 * 
 * @author yanzai
 */
function closeCurrentTab() {
	var jq = top.jQuery;
	if (jq(mainTabs)) {
		var tabdb = jq(mainTabs).tabs('getTab', "待办事项一览");
		if (tabdb) {
			var dbtitle = tabdb.panel('options').title;
			if (jq(mainTabs).tabs('exists', dbtitle)) {
				jq(mainTabs).tabs('refresh', dbtitle);
			}
		}
		var tabzc = jq(mainTabs).tabs('getTab', "暂存流程一览");
		if (tabzc) {
			var zctitle = tabzc.panel('options').title;
			if (jq(mainTabs).tabs('exists', zctitle)) {
				jq(mainTabs).tabs('refresh', zctitle);
			}
		}
		var tabyb = jq(mainTabs).tabs('getTab', "已办事项一览");
		if (tabyb) {
			var ybtitle = tabyb.panel('options').title;
			if (jq(mainTabs).tabs('exists', ybtitle)) {
				jq(mainTabs).tabs('refresh', ybtitle);
			}
		}
		var tabbj = jq(mainTabs).tabs('getTab', "办结事项一览");
		if (tabbj) {
			var bjtitle = tabbj.panel('options').title;
			if (jq(mainTabs).tabs('exists', bjtitle)) {
				jq(mainTabs).tabs('refresh', bjtitle);
			}
		}
		var tab_index = jq(mainTabs).tabs('getTab', "主页");
		if (tab_index) {
			var indextitle = tab_index.panel('options').title;
			if (jq(mainTabs).tabs('exists', indextitle)) {
				jq(mainTabs).tabs('refresh', indextitle);
			}
		}
		if($('#dbTable')){
			$('#dbTable').datagrid('reload');
		}
		var tab_title = jq(mainTabs).tabs('getSelected').panel('options').title;
		jq(mainTabs).tabs('close', tab_title);
	}
};

/**
 * 账户流水查看页面
 * 
 * @author yanzai
 */
function viewZhLs(ptgsdm, clid, cllx, cllxmc) {
	// @param ptgsdm 平台公司代码
	// @param cllx 流程类型字典值
	// @param clid 处理ID
	if (!isEmpty(clid) && !isEmpty(cllx)) {
		if (isEmpty(cllxmc)) {
			cllxmc = "账户流水查看";
		}
		var url = $ctxURL + "myAccount/zhls/viewZhLs/" + ptgsdm + "/" + cllx
				+ "/" + clid;
		addNewTab(cllxmc, url, "jylsmxcx");
	}
};

/**
 * 数字Trim
 * 
 * @author yanzai
 */
function trimNumber(n, direction) {
	var startZero = true;
	var x;
	var p = "";
	var t = "";
	var w = n + "";
	if (direction == "f")
		x = w.split("");
	if (direction == "b")
		x = w.split("").reverse();
	for (var i = 0; i < x.length; i++) {
		if (startZero && x[i] == "0") {
			continue;
		}
		p += x[i];
		startZero = false;
	}
	p = p == "" ? "0" : p;
	if (direction == "f")
		return p;
	if (direction == "b")
		return p.split("").reverse().join("");
}

/**
 * 数字format 999999.9 -> 999,999.9
 * 
 * @author yanzai
 */
function fNumber(n) {
	if (n) {
		var temp = Number(n);
		if (!isNaN(temp)) {
			n = temp.toFixed(2);
		}
	}
	var prefix = "";
	var w = (n + "").replace(/[^\d\.-]/g, "");
	var hasPoint = false;
	var startZero = true;
	if (isNaN(w) || w == "")
		return "";
	if (w.indexOf('.') > -1 && w.indexOf('.') != w.length - 1)
		hasPoint = true;
	var q = w.split(".")[0];
	if (q.indexOf('-') != -1) {
		prefix = "-";
		q = q.substring(1);
	}
	q = trimNumber(q, "f");
	var l = q.split("").reverse();
	var r = ".00";
	if (hasPoint) {
		r = trimNumber(w.split(".")[1], "b");
		r= r.length < 2 ? r + "0" : r;
		r = "." + r;
	}
	if (parseFloat(q + r) == 0)
		prefix = "";
	var t = "";
	for (var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return prefix + t.split("").reverse().join("") + r + "";
};

/**
 * 数字format 999,999.9 -> 999999.9
 * 
 * @author yanzai
 */
function rNumber(n) {
	var w = (n + "").replace(/[^\d\.-]/g, "");
	if (isNaN(w) || w == "")
		return "";
	var hasPoint = false;
	if (w.indexOf('.') > -1 && w.indexOf('.') != w.length - 1)
		hasPoint = true;
	var l = w.split(".")[0];
	var r = hasPoint ? "." + w.split(".")[1] : "";
	return l + r + "";
};

/**
 * 导出csv文件功能共通
 * 
 * @author yanzai
 * @param fileName
 *            csv文件名
 * @param dataGrid_id
 *            DataGrid控件id
 */
var eptJeF = false;// 导出金额是否有逗号标识
var eptAllData = true;// 导出所有页数据
function commExport(fileName, dataGrid_id) {
	if (isEmpty(dataGrid_id)) {
		$.easyui.messager.alert("参数不正确无法导出！");
		return;
	}
	var dataGridObj = $("#" + dataGrid_id);
	if (!dataGridObj) {
		$.easyui.messager.alert("数据表格不存在无法导出！");
		return;
	}
	var rows = dataGridObj.datagrid("getRows"); // 获取当前页的所有行
	if (isEmpty(rows) || rows.length <= 0) {
		$.easyui.messager.alert("没有可以导出的数据记录！");
		return;
	}
	var columns = dataGridObj.datagrid("options").columns; // 得到columns对象
	if (isEmpty(columns)) {
		$.easyui.messager.alert("没有可以导出的数据列表！");
		return;
	}
	if (eptAllData) {
		var totalRows = dataGridObj.datagrid("getData").total;
		var sort = dataGridObj.datagrid("options").sortName;
		var order = dataGridObj.datagrid("options").sortOrder;
		var queryParams = dataGridObj.datagrid("options").queryParams;// 得到queryParams查询参数
		queryParams.page = 1;
		queryParams.rows = totalRows;
		queryParams.sort = sort;
		queryParams.order = order;
		queryParams.isExport = true;
		var url = dataGridObj.datagrid("options").url;
		console.log(url);
		var allExportData = null;
		$.ajax({
			dataType : "json",
			async : false,
			cache : false,
			url : url,
			data : queryParams,
			success : function(jsonData) {
				if (jsonData && typeof jsonData == 'object') {
					allExportData = jsonData.rows;
				}
			}
		});
	}

	var headNameList = [];
	var headFieldList = [];
	var exportData = [];
	if (columns.length > 1) {
		// 有冻结在左侧的frozenColumns对象列
		var frozenColumns = dataGridObj.datagrid("options").frozenColumns; // 得到frozenColumns对象
		if (!isEmpty(frozenColumns)) {
			var eachObj = frozenColumns[0];
			$(eachObj).each(
					function(index) {
						if (!isEmpty(eachObj[index].title)
								&& !eachObj[index].hidden
								&& !eachObj[index].title != '详情'
								&& !eachObj[index].title != '操作') {
							if(eachObj[index].field == "pmcpr" || eachObj[index].field == "pmdqr"){
								headFieldList.push(eachObj[index].field+"Str");
							}else{
								headFieldList.push(eachObj[index].field);
							}
							headNameList.push(eachObj[index].title);
						}
					});
		}
		if (!isEmpty(columns)) {
			var eachObj = columns[0];// 复列头名
			var headNamesPre = [];
			var headNamesPreCols = [];
			$(eachObj).each(
					function(index) {
						if (!isEmpty(eachObj[index].title)
								&& !eachObj[index].hidden
								&& !eachObj[index].title != '详情'
								&& !eachObj[index].title != '操作') {
							headNamesPre.push(eachObj[index].title);
							headNamesPreCols.push(eachObj[index].colspan);
						}
					});
			var preColsSum = RecursiveSum(headNamesPreCols);
			eachObj = columns[1];// 复列子名
			$(eachObj).each(
					function(index) {

						if (!isEmpty(eachObj[index].title)
								&& !eachObj[index].hidden) {
							if(eachObj[index].field == "pmcpr" || eachObj[index].field == "pmdqr"){
								headFieldList.push(eachObj[index].field+"Str");
							}else{
								headFieldList.push(eachObj[index].field);
							}
							var namePreIndex = 0;
							for (var c = 0; c < preColsSum.length; ++c) {
								if (index < preColsSum[c]) {
									namePreIndex = c;
									break;
								}
							}
							var compoundName = headNamesPre[namePreIndex]
									+ eachObj[index].title;
							headNameList.push(compoundName);
						}
					});
		}
	} else {
		// 没有冻结在左侧的frozenColumns对象列，所有列不跨列显示的排版
		if (!isEmpty(columns)) {
			var eachObj = columns[0];
			$(eachObj).each(
					function(index) {
						if (!isEmpty(eachObj[index].title)
								&& !eachObj[index].hidden
								&& !eachObj[index].title != '详情'
								&& !eachObj[index].title != '操作') {
							if(eachObj[index].field == "pmcpr" || eachObj[index].field == "pmdqr"){
								headFieldList.push(eachObj[index].field+"Str");
							}else{
								headFieldList.push(eachObj[index].field);
							}
							headNameList.push(eachObj[index].title);
						}
					});
		}
	}
	if (eptAllData) {
		rows = allExportData;
	}
	for (var i = 0; i < rows.length; ++i) {
		var rowData = [];
		for (var j = 0; j < headFieldList.length; ++j) {
			rowData.push(rows[i][headFieldList[j]]);
		}
		exportData.push(rowData.join("_"));
	}
	var href = $ctxURL + "system/export/exportCSV";
	var f = $('<form action="' + href + '" method="post" id="fm1"></form>');
	var l = $('<input type="hidden" id="fileName" name="fileName" />');
	var t = $('<input type="hidden" id="fileType" name="fileType" />');
	var i = $('<input type="hidden" id="exportData" name="exportData" />');
	var h = $('<input type="hidden" id="head" name="head" />');
	l.val(fileName);
	l.appendTo(f);
	t.val("csv");
	t.appendTo(f);
	i.val(exportData.join(";"));
	i.appendTo(f);
	h.val(headNameList.join(";"));
	h.appendTo(f);
	f.appendTo(document.body).submit();
	document.body.remove(f);
};

/**
 * 对数组中的数字递归求和 retAry[0] = digitAry[0] retAry[1] = digitAry[1] + digitAry[0]
 * retAry[2] = digitAry[2] + digitAry[1] + digitAry[0] ...
 * 
 * @param digitAry
 */
function RecursiveSum(digitAry) {
	var retAry = [];
	if (digitAry && digitAry.length > 0) {
		for (var i = 0; i < digitAry.length; ++i) {
			if (i > 0) {
				retAry[i] = digitAry[i] + retAry[i - 1];
			} else {
				retAry[i] = digitAry[i];
			}

		}
	}
	return retAry;
}

function commLoadJbCombobox(combobox_id, height, width) {
	if (isEmpty(combobox_id)) {
		return;
	}
	var comboboxObj = $("#" + combobox_id);
	if (!comboboxObj) {
		return;
	}
	var panelHeight = 100;
	var panelWidth = 135;
	if (!isEmpty(height)) {
		panelHeight = height;
	}
	if (!isEmpty(width)) {
		panelWidth = width;
	}
	comboboxObj.combobox({
		url : $ctxURL + "system/flfjqysj/getJbCombobox/json",
		method : "GET",
		valueField : 'id',
		textField : 'text',
		editable : false,
		panelWidth : panelWidth,
		panelHeight : panelHeight,
	});
};

function spztName(spzt) {
	var spztname = "";
	if (spzt == 10) {
		spztname = "已处理"
	}
	if (spzt == 5) {
		spztname = "待确认"
	}
	if (spzt == 9) {
		spztname = "关闭"
	}
	if (spzt == 1) {
		spztname = "提交审批"
	}
	if (spzt == 2) {
		spztname = "审批通过"
	}
	if (spzt == 3) {
		spztname = "审批退回"
	}
	if (spzt == 4) {
		spztname = "票据维护"
	}
	if (spzt == 7) {
		spztname = "票据岗确认"
	}
	if (spzt == 8) {
		spztname = "备案"
	}
	return spztname;
};

/**
 * datagrid列表编辑数据
 * 
 * @author yanzai
 */
var onEditing = false;
$.extend($.fn.datagrid.methods, {
	editCell : function(jq, param) {
		return jq.each(function() {
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields', true).concat(
					$(this).datagrid('getColumnFields'));
			for (var i = 0; i < fields.length; i++) {
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field) {
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for (var i = 0; i < fields.length; i++) {
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

var editIndex = undefined;
function onClickCell(index, field) {
	var dgEdit = $(this);
	if (dgEdit && endEditing(dgEdit)) {
		dgEdit.datagrid('selectRow', index).datagrid('editCell', {
			index : index,
			field : field,
		});
		editIndex = index;
		if ($(this).datagrid('getColumnOption', field).editor) {
			onEditing = true;
		}
	}
};

function endEditing(dgEdit) {
	if (editIndex == undefined) {
		return true;
	}
	if (dgEdit.datagrid('validateRow', editIndex)) {
		dgEdit.datagrid('endEdit', editIndex);
		editIndex = undefined;
		onEditing = false;
		return true;
	} else {
		return false;
	}
};

/**
 * 日期限制
 * @author yanzai
 * @param smDateboxId
 * @param lgDateboxId
 */
function dateboxCommLimit(smDateboxId, lgDateboxId) {
	if (!(isEmpty(smDateboxId)) && !(isEmpty(lgDateboxId))) {
		var smDatebox = $('#' + smDateboxId);
		var lgDatebox = $('#' + lgDateboxId);
		smDatebox.datebox({
			editable : false,
			currentText : "",
			onSelect : function(date) {
				var lgDateVal = $.trim(lgDatebox.datebox('getValue'));
				if (!(isEmpty(lgDateVal))) {
					var lgDate = new Date(lgDateVal);
					var smDateVal = $.trim(smDatebox.datebox('getValue'));
					var smDate = new Date(smDateVal);
					if (smDate > lgDate) {
						smDatebox.datebox('setValue', '').datebox('showPanel');
					}
				}
				// 只允许选择lgDate之前的日期
				lgDatebox.datebox('calendar').calendar({
					validator : function(lgd) {
						return lgd >= date;
					}
				});
			}

		});
		lgDatebox.datebox({
			editable : false,
			currentText : "",
			onSelect : function(date) {
				var smDateVal = $.trim(smDatebox.datebox('getValue'));
				if (!(isEmpty(smDateVal))) {
					var smDate = new Date(smDateVal);
					var lgDateVal = $.trim(lgDatebox.datebox('getValue'));
					var lgDate = new Date(lgDateVal);
					if (lgDate < smDate) {
						lgDatebox.datebox('setValue', '').datebox('showPanel');
					}
				}
				// 只允许选择lgDate之后的日期
				smDatebox.datebox('calendar').calendar({
					validator : function(smd) {
						return smd <= date;
					}
				});
			}

		});
	}
};

function onSelect(d) {
	var issd = this.id == 'pmcpr', pmcpr = issd ? d : new Date($('#pmcpr')
			.datebox('getValue')), pmdqr = issd ? new Date($('#pmdqr').datebox(
			'getValue')) : d;
	if (pmdqr < pmcpr) {
		alert('到期日小于出票日');
		// 只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
		$('#pmdqr').datebox('setValue', '').datebox('showPanel');
	}
}

/**
 * 获取所有分部公司下拉列表数据
 * 
 * @author yanzai
 */
function loadCommAllFbgs(fbgsboxId, ptgsboxId) {
	if(isEmpty(fbgsboxId)){
		return;
	}
	var fbgsbox = $('#' + fbgsboxId);
	fbgsbox.combobox({
		url : $ptzhCommBaseURL + "/getAllFbgsDropdownbox/json",
		method : "POST",
		valueField : 'id',
		textField : 'text',
		panelHeight : 200,
		panelWidht : 300,
		editable : false,
		onChange : function(newValue, oldValue) {
			if (isEmpty(newValue)) {
				var ptgsbox = $('#' + ptgsboxId);
				ptgsbox.combobox('clear');
				return;
			} else {
				if(!isEmpty(ptgsboxId)){
					var ptgsbox = $('#' + ptgsboxId);
					var fbgs = encodeURI(encodeURI(newValue));
					ptgsbox.combobox('clear');
					ptgsbox.combobox('enable');
					var url = $ptzhCommBaseURL + "/getPtgsOfFbgs/json/" + fbgs;
					ptgsbox.combobox('reload', url);
				}
			}
		}
	});
};

/**
 * 获取所有平台公司下拉列表数据
 * 
 * @author yanzai
 */
function loadCommAllPtgs(disableFlag, ptgsboxId) {
	if(typeof(disableFlag) != "boolean"){
		disableFlag = true;
	}
	if(isEmpty(ptgsboxId)){
		return;
	}
	var ptgsbox = $('#' + ptgsboxId);
	ptgsbox.combobox({
		url : $ptzhCommBaseURL + "/getAllPtgsDropdownbox/json",
		method : "POST",
		valueField : 'id',
		textField : 'text',
		disabled : disableFlag,
		editable : false,
		panelHeight : 200,
		panelWidht : 300,
	});
};

/**
 * 获取分部下属的平台公司列表数据
 * 
 * @author yanzai
 */
function loadCommPtgsOfFbgs(fbgs,disableFlag, ptgsboxId) {
	if(isEmpty(fbgs)){
		fbgs = "-1";
	}
	if(typeof(disableFlag) != "boolean"){
		disableFlag = true;
	}
	if(isEmpty(ptgsboxId)){
		return;
	}
	var ptgsbox = $('#' + ptgsboxId);
	ptgsbox.combobox({
		url : $ptzhCommBaseURL + "/getPtgsOfFbgs/json/" + fbgs,
		method : "POST",
		valueField : 'id',
		textField : 'text',
		disabled : disableFlag,
		editable : true,
		panelHeight : 200,
		panelWidht : 300,
	});
};

function commJeChange(jexx,jexxId,jedxId) {
	if (jexx == undefined || $.trim(jexx) == "") {
		if(!isEmpty(jexxId)){
			$("#"+jexxId).val("");
		}
		if(!isEmpty(jedxId)){
			$("#"+jedxId).val("");
		}
	} else {
		var jedx = atoc(jexx);
		if(!isEmpty(jedxId)){
			$("#"+jedxId).val(jedx);
		}
	}
}

function commJeBlurFNumber(jexx,jexxId,jedxId) {
	if (jexx == undefined || $.trim(jexx) == "") {
		if(!isEmpty(jexxId)){
			$("#"+jexxId).val("");
		}
		if(!isEmpty(jedxId)){
			$("#"+jedxId).val("");
		}
	} else {
		if(!isEmpty(jexxId)){
			$("#"+jexxId).val(fNumber(jexx));
		}
	}
}
function commJeFocusRNumber(jexx,jexxId,jedxId) {
	if (jexx == undefined || $.trim(jexx) == "") {
		if(!isEmpty(jexxId)){
			$("#"+jexxId).val("");
		}
		if(!isEmpty(jedxId)){
			$("#"+jedxId).val("");
		}
	} else {
		if(!isEmpty(jexxId)){
			$("#"+jexxId).val(rNumber(jexx));
		}
	}
}

/**
 * 允许账户流水页面查看流程过滤数组
 * 
 */
var AllowViewZhlsAry = ['51','12','13','14','23','33','21','32','22','31'];


//获得某月的天数 
function comm_getMonthDays(myYear, myMonth){ 
	var monthStartDate = new Date(myYear, myMonth, 1); 
	var monthEndDate = new Date(myYear, myMonth + 1, 1); 
	var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24); 
	return days; 
} 


/**
 * 自动填入期限日期
 * @param fromdate 开始
 * @param qxnum 期限
 * @param todate 到期
 */
function commSetQXDATE(fromdate, qxnum,todate){
	fromdate = '#'+fromdate;
	fromdate = $(fromdate);
	qxnum = '#'+qxnum;
	qxnum = $(qxnum);
	todate = '#'+todate;
	todate = $(todate);
	var qx = 0;
	if(qxnum.combobox('getValue')==''){
		return;
	}else{
		qx = parseInt(qxnum.combobox('getValue'));
	}
//	if(fromdate.datebox('getValue')!='' && todate.datebox('getValue')!=''){
//		return;
//	}else if(fromdate.datebox('getValue')!='' && todate.datebox('getValue')==''){
		var fd = new Date(fromdate.datebox('getValue'));
		var nowDay = fd.getDate(); //当前日 
		var nowMonth = fd.getMonth(); //当前月 
		var nowYear = fd.getYear(); //当前年 
		nowYear += (nowYear < 2000) ? 1900 : 0;
		if(nowMonth+qx>11){
			nowMonth=nowMonth+qx-12;
			nowYear=nowYear+1;
		}else{
			nowMonth=nowMonth+qx;
		}
		var dd = comm_getMonthDays(nowYear, nowMonth);
		if(nowDay>dd){
			nowDay = dd;
		}
		todate.datebox('setValue',new Date(nowYear, nowMonth, nowDay).format('yyyy-MM-dd'));
//	}else if(fromdate.datebox('getValue')=='' && todate.datebox('getValue')!=''){
//	}
}

/**
 * 格式金额共通
 * @param value
 * @param rowData
 * @param rowIndex
 * @param field
 * @returns {String}
 */
function formatterRowJe(value, rowData, rowIndex, field) {
	var showValue = 0;
	if (value != null && value != undefined){
		showValue = value;
	}
	if (eptJeF) {
		if(!isEmpty(field)){
			rowData[field] = fNumber(showValue);// 更新数据行数据
		}
	}
	return fNumber(showValue);
}

function isBankAccount(bankAccount) {
	if (bankAccount == null || bankAccount == "") {
		return true;
	}
	var reg =/^[\d\-]*$/;
	return reg.test(bankAccount); 
}

function isTicketNo(ticketNo) {
	if (ticketNo == null || ticketNo == "") {
		return true;
	}
	var reg =/^[\d\s\/\-]*$/;
	return reg.test(ticketNo); 
}

function checkLenth(str, len) {
	if (str == null || str == "") {
		return true;
	}
	
	if (str.length > len) {
		return false;
	}
	
	return true;
}
