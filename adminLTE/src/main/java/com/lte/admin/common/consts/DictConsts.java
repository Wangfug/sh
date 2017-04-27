package com.lte.admin.common.consts;

import com.lte.admin.common.utils.Global;

/**
 * 字典常量定义
 * 
 * @author yanzai
 * 
 */
public class DictConsts {
	/**
	 * 返回结果字符串标识
	 */
	public static final String IS_SUCCESS = "isSuccess";

	/**
	 * NC返回结果字符串标识
	 */
	public static final String NC_SUCCESSFUL = "successful";

	/**
	 * NC返回结果错误信息
	 */
	public static final String NC_DESCRIPTION = "resultdescription";

	/**
	 * DBLock错误信息
	 */
	public static final String DB_LOCK_ERROR = "java.sql.SQLException: ORA-00054";

	/**
	 * NC返回成功字符串标识：0:未生成, Y:成功, N:失败
	 */
	public static final String NC_RET_Y = "Y";

	/**
	 * NC返回失败字符串标识：0:未生成, Y:成功, N:失败
	 */
	public static final String NC_RET_N = "N";

	/**
	 * NC未生成字符串标识：0:未生成, Y:成功, N:失败
	 */
	public static final String NC_NOT_CREATED = "0";

	/**
	 * 最大长度字符串4000
	 */
	public static final int STRING_MAX_LEN = 4000;

	/**
	 * OA审批返回通过字符串标识
	 */
	public static final String OA_SP_PASS = "1";

	/**
	 * OA审批返回不通过字符串标识
	 */
	public static final String OA_SP_FAIL = "2";
	/**
	 * 返回结果数据信息
	 */
	public static final String DATA = "data";

	/**
	 * 返回结果标识：success,error
	 */
	public static final String RESULT = "result";

	/**
	 * 成功字符串信息标识
	 */
	public static final String SUCCESS = "success";
	/**
	 * 失败字符串信息标识
	 */
	public static final String ERROR = "error";

	/**
	 * 正确字符串信息标识
	 */
	public static final String TRUE = "true";
	/**
	 * 错误字符串信息标识
	 */
	public static final String FALSE = "false";

	/**
	 * 异常字符串信息标识
	 */
	public static final String BUG = "bug";

	/**
	 * 异常信息标识
	 */
	public static final String MSG = "msg";

	/**
	 * 系统默认用户ID
	 */
	public static final String SYS_USR_ID = "admin";
	/**
	 * 系统默认用户名称
	 */
	public static final String SYS_USR_NM = "admin";
	/**
	 * 数字四舍五入保留2位小数
	 */
	public static final int ROUND_TWO = 2;
	/**
	 * 数字四舍五入保留4位小数
	 */
	public static final int ROUND_FOUR = 4;
	/**
	 * 分类分级表变更标志：0：无变更
	 */
	public static final String BGBZ_NOCHANGE = "0";
	/**
	 * 分类分级表变更标志：1：无变更
	 */
	public static final String BGBZ_CHANGE = "1";
	/**
	 * 是否对表进行查询加锁标识
	 */
	public static final String LOCK_YES = "1";
	/**
	 * 公司标志：总部：00
	 */
	public static final String GSBZ_ZB = "00";
	/**
	 * 公司标志：分部：01
	 */
	public static final String GSBZ_FB = "01";
	/**
	 * 公司标志：平台公司：02
	 */
	public static final String GSBZ_PT = "02";
	/**
	 * 总部公司编码
	 */
	public static final String ZBGSBM = "0801";
	/**
	 * NC支付成功状态
	 */
	public static final String NC_PAID_SUCCESSFUL = "1";
	/**
	 * OA返回失败异常字符串标识数组 如果小于0表示失败 <br>
	 * -1：创建流程失败 <br>
	 * -2：用户没有流程创建权限 <br>
	 * -3：创建流程基本信息失败 <br>
	 * -4：保存表单主表信息失败 <br>
	 * -5：更新紧急程度失败 <br>
	 * -6：流程操作者失败 <br>
	 * -7：流转至下一节点失败 <br>
	 * -8：节点附加操作失败
	 */
	public static final String[] OA_RET_FAIL_STRS = { "-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8" };

	// ----------------------------------------------------------------
	// Property信息定义
	// ----------------------------------------------------------------
	/**
	 * 提交次数
	 */
	public static final int PROPERTY_SUBMIT_TIMES = Integer.valueOf(Global.getConfig("submit.times"));

	/**
	 * 平台公司账户信息和到期敞口明细信息数据的备份天数
	 */
	public static final int PROPERTY_BACKUP_DAYS = Integer.valueOf(Global.getConfig("backup.days"));

	// ----------------------------------------------------------------
	// 字典信息定义
	// ----------------------------------------------------------------
	/**
	 * 字典类型：公司级别
	 */
	public static final String ZDLX_JB = "JB";
	/**
	 * 字典类型：流程类型
	 */
	public static final String ZDLX_LCLX = "LCLX";
	/**
	 * 字典类型：数据类型
	 */
	public static final String ZDLX_SJLX = "SJLX";
	/**
	 * 字典类型：接口类型
	 */
	public static final String ZDLX_JKLX = "JKLX";
	/**
	 * 字典类型：单据类型
	 */
	public static final String ZDLX_DJLX = "DJLX";
	/**
	 * 字典类型：票据期限
	 */
	public static final String ZDLX_PJQX = "PJQX";

	/**
	 * 票据期限 3个月
	 */
	public static final String PJQX_SGY = "PJQX_SGY";

	/**
	 * 票据期限 6个月
	 */
	public static final String PJQX_LGY = "PJQX_LGY";
	/**
	 * 字典类型：票据类型
	 */
	public static final String ZDLX_PJLX = "PJLX";

	/**
	 * 字典类型：领取方式
	 */
	public static final String ZDLX_LQFS = "LQFS";
	/**
	 * 字典类型：资金类型
	 */
	public static final String ZDLX_ZJLX = "ZJLX";
	/**
	 * 字典类型：提现用途
	 */
	public static final String ZDLX_TXYT = "TXYT";
	/**
	 * 字典类型：票据下拨方式
	 */
	public static final String ZDLX_PJXBFS = "PJXBFS";
	/**
	 * 字典类型：流程审批状态
	 */
	public static final String ZDLX_SPZT = "SPZT";
	/**
	 * 字典类型：票据审核状态
	 */
	public static final String ZDLX_SHZT = "SHZT";
	/**
	 * 字典类型：OA工作流程
	 */
	public static final String ZDLX_OAWORKFLOW = "OAWORKFLOW";
	/**
	 * 字典类型：利息类型
	 */
	public static final String ZDLX_LXLX = "LXLX";
	/**
	 * 字典类型：充值
	 */
	public static final String LCLX_CZ = "CZ";
	/**
	 * 字典类型：用信
	 */
	public static final String LCLX_YX = "YX";
	/**
	 * 字典类型：资金导入类型
	 */
	public static final String ZJLX_ZJDRLX = "ZJDRLX";
	/**
	 * 字典类型：通知公告
	 */
	public static final String ZDLX_TZGGLX = "TZGGLX";
	/**
	 * 流程类型：银企直连
	 */
	public static final String LCLX_YQZL = "LCLX_YQZL";
	/**
	 * 流程类型：线下电汇
	 */
	public static final String LCLX_XXDH = "LCLX_XXDH";
	/**
	 * 流程类型：总部借款
	 */
	public static final String LCLX_ZBJK = "LCLX_ZBJK";
	/**
	 * 流程类型：票据贴现
	 */
	public static final String LCLX_PJTX = "LCLX_PJTX";
	/**
	 * 流程类型：汇农贷
	 */
	public static final String LCLX_HND = "LCLX_HND";
	/**
	 * 流程类型：自有现金
	 */
	public static final String LCLX_ZYXJ = "LCLX_ZYXJ";
	/**
	 * 流程类型：自有承兑
	 */
	public static final String LCLX_ZYCD = "LCLX_ZYCD";
	/**
	 * 流程类型：总部新办
	 */
	public static final String LCLX_ZBXB = "LCLX_ZBXB";
	/**
	 * 流程类型：总部背书
	 */
	public static final String LCLX_ZBBS = "LCLX_ZBBS";
	/**
	 * 流程类型：三方转款
	 */
	public static final String LCLX_SFZK = "LCLX_SFZK";
	/**
	 * 流程类型：票据下拨
	 */
	public static final String LCLX_PJXB = "LCLX_PJXB";
	/**
	 * 流程类型：平台公司上线
	 */
	public static final String LCLX_PTSX = "LCLX_PTSX";
	/**
	 * 流程类型：调整授信额度
	 */
	public static final String LCLX_TZSX = "LCLX_TZSX";
	/**
	 * 流程类型：期初数据导入
	 */
	public static final String LCLX_QCDR = "LCLX_QCDR";
	/**
	 * 流程类型：差错调整
	 */
	public static final String LCLX_CCTZ = "LCLX_CCTZ";
	/**
	 * 流程类型：提现
	 */
	public static final String LCLX_TX = "LCLX_TX";
	/**
	 * 流程类型：利息导入
	 */
	public static final String LCLX_LXDR = "LCLX_LXDR";
	/**
	 * 流程类型：手动还款
	 */
	public static final String LCLX_SDHK = "LCLX_SDHK";
	/**
	 * 流程类型：自动还款
	 */
	public static final String LCLX_ZDHK = "LCLX_ZDHK";
	/**
	 * 流程类型：提前冻结
	 */
	public static final String LCLX_TQDJ = "LCLX_TQDJ";
	/**
	 * 资金类型：现款
	 */
	public static final String ZJLX_CASH = "ZJLX_CASH";
	/**
	 * 资金类型：敞口
	 */
	public static final String ZJLX_EXPOSURES = "ZJLX_EXPOSURES";
	/**
	 * 资金类型：资金往来利息
	 */
	public static final String ZJLX_ZJWLLX = "ZJLX_ZJWLLX";
	/**
	 * 资金类型：居间费用
	 */
	public static final String ZJLX_JJFY = "ZJLX_JJFY";
	/**
	 * 资金类型：管理费用
	 */
	public static final String ZJLX_GLFY = "ZJLX_GLFY";
	/**
	 * 资金类型：红包结算费用
	 */
	public static final String ZJLX_HBJS = "ZJLX_HBJS";
	/**
	 * 资金类型：商城套餐结算费用
	 */
	public static final String ZJLX_SCTC = "ZJLX_SCTC";
	/**
	 * 资金类型：市内交通费用
	 */
	public static final String ZJLX_SNJTF = "ZJLX_SNJTF";
	/**
	 * 资金类型：社保、公积金费用
	 */
	public static final String ZJLX_SBGJJ = "ZJLX_SBGJJ";
	/**
	 * 数据类型：对于到期现款敞口提前锁定天数
	 */
	public static final String SJLX_SDTS = "SJLX_SDTS";
	/**
	 * 数据类型：存款年化利率
	 */
	public static final String SJLX_CKLL = "SJLX_CKLL";
	/**
	 * 数据类型：逾期年化利率
	 */
	public static final String SJLX_YQLL = "SJLX_YQLL";
	/**
	 * 数据类型：贴现率
	 */
	public static final String SJLX_TXL = "SJLX_TXL";
	/**
	 * 数据类型：活期存款利息计算用年度标准天数
	 */
	public static final String SJLX_HQNDBJTS = "SJLX_HQNDBJTS";
	/**
	 * 数据类型：借款利息计算用年度标准天数
	 */
	public static final String SJLX_JKNDBJTS = "SJLX_JKNDBJTS";
	/**
	 * 数据类型：逾期利息计算用年度标准天数
	 */
	public static final String SJLX_YQNDBJTS = "SJLX_YQNDBJTS";
	/**
	 * 数据类型：贴现息计算用半年度标准天数
	 */
	public static final String SJLX_TXBNDBJTS = "SJLX_TXBNDBJTS";
	/**
	 * 数据类型：增值税专票的税率（6％）
	 */
	public static final String SJLX_ZPSL = "SJLX_ZPSL";
	/**
	 * 接口类型：NC凭证
	 */
	public static final String JKLX_NCPZ = "JKLX_NCPZ";

	/**
	 * 接口类型：NC凭证
	 */
	public static final String JKLX_NCPZ_ZDXXVAL = "701";

	/**
	 * 接口类型：NC单据
	 */
	public static final String JKLX_NCDJ = "JKLX_NCDJ";

	/**
	 * 接口类型：NC凭证
	 */
	public static final String JKLX_NCDJ_ZDXXVAL = "702";

	/**
	 * 接口类型：JL单据
	 */
	public static final String JKLX_JLDJ = "JKLX_JLDJ";
	/**
	 * 单据类型：NC凭证（总部）
	 */
	public static final String DJLX_NCPZZB = "DJLX_NCPZZB";
	/**
	 * 单据类型：NC凭证（平台）
	 */
	public static final String DJLX_NCPZPT = "DJLX_NCPZPT";
	/**
	 * 单据类型：NC凭证（收款公司）
	 */
	public static final String DJLX_NCPZSK = "DJLX_NCPZSK";
	/**
	 * 单据类型：NC凭证（三方）
	 */
	public static final String DJLX_NCPZSF = "DJLX_NCPZSF";
	/**
	 * 单据类型：收付款
	 */
	public static final String DJLX_SFK = "DJLX_SFK";
	/**
	 * 单据类型：预付款申请
	 */
	public static final String DJLX_YFKSQ = "DJLX_YFKSQ";
	/**
	 * 单据类型：预付款申请审核
	 */
	public static final String DJLX_YFKSQSH = "DJLX_YFKSQSH";
	/**
	 * 单据类型：结算付款
	 */
	public static final String DJLX_JSFK = "DJLX_JSFK";
	/**
	 * 票据下拨方式：总部新办
	 */
	public static final String PJXBFS_ZBXB = "PJXBFS_ZBXB";
	/**
	 * 票据下拨方式：总部背书
	 */
	public static final String PJXBFS_ZBBS = "PJXBFS_ZBBS";
	/**
	 * 票据下拨方式：三方转款
	 */
	public static final String PJXBFS_SFZK = "PJXBFS_SFZK";
	/**
	 * 审批状态：未提交
	 */
	public static final String SPZT_UNSUBMIT = "SPZT_UNSUBMIT";
	/**
	 * 审批状态：提交审批
	 */
	public static final String SPZT_SUBMIT = "SPZT_SUBMIT";
	/**
	 * 审批状态：审批通过
	 */
	public static final String SPZT_PASS = "SPZT_PASS";
	/**
	 * 审批状态：已处理
	 */
	public static final String SPZT_HANDLED = "SPZT_HANDLED";
	/**
	 * 审批状态：待确认
	 */
	public static final String SPZT_DQR = "SPZT_DQR";
	/**
	 * 审批状态：审批退回
	 */
	public static final String SPZT_FAIL = "SPZT_FAIL";
	/**
	 * 审批状态：关闭
	 */
	public static final String SPZT_CLOSE = "SPZT_CLOSE";
	/**
	 * 审批状态：待支付
	 */
	public static final String SPZT_DZF = "SPZT_DZF";
	/**
	 * 审批状态：自动关闭
	 */
	public static final String SPZT_AUTO_CLOSE = "SPZT_AUTO_CLOSE";
	/**
	 * 审批状态：票据岗确认
	 */
	public static final String SPZT_PJGQR = "SPZT_PJGQR";
	/**
	 * 审批状态：备案
	 */
	public static final String SPZT_BA = "SPZT_BA";
	/**
	 * 审核状态：未批
	 */
	public static final String SHZT_WP = "SHZT_WP";
	/**
	 * 审核状态：已批
	 */
	public static final String SHZT_YP = "SHZT_YP";
	/**
	 * 审核状态：确认
	 */
	public static final String SHZT_QR = "SHZT_QR";
	/**
	 * 审核状态：完成
	 */
	public static final String SHZT_WC = "SHZT_WC";
	/**
	 * 审核状态：取消
	 */
	public static final String SHTZ_QX = "SHTZ_QX";
	// 自有承兑流程203
	// 自有现金流程204
	// 三方转款流程200
	// 新办承兑流程202
	// 总部背书流程205
	// 总部票据下拨流程206
	// 总部现金借款流程207
	// 提现流程201
	/**
	 * OA工作流：203 ：自有承兑
	 */
	public static final String WORKFLOW_ZYCD = "WORKFLOW_ZYCD";
	/**
	 * OA工作流：204 ：自有现金
	 */
	public static final String WORKFLOW_ZYXJ = "WORKFLOW_ZYXJ";
	/**
	 * OA工作流：205 ：总部背书
	 */
	public static final String WORKFLOW_ZBBS = "WORKFLOW_ZBBS";
	/**
	 * OA工作流：202 ：新办承兑
	 */
	public static final String WORKFLOW_XBCD = "WORKFLOW_XBCD";
	/**
	 * OA工作流：200 ：三方转款
	 */
	public static final String WORKFLOW_SFZK = "WORKFLOW_SFZK";
	/**
	 * OA工作流：206 ：票据下拨
	 */
	public static final String WORKFLOW_PJXB = "WORKFLOW_PJXB";
	/**
	 * OA工作流：201 ：提现
	 */
	public static final String WORKFLOW_TX = "WORKFLOW_TX";
	/**
	 * OA工作流：207 ：总部借款
	 */
	public static final String WORKFLOW_ZBJK = "WORKFLOW_ZBJK";
	/**
	 * 利息类型：活期利息
	 */
	public static final String LXLX_HQLX = "LXLX_HQLX";
	/**
	 * 利息类型：借款利息
	 */
	public static final String LXLX_JKLX = "LXLX_JKLX";
	/**
	 * 利息类型：逾期利息
	 */
	public static final String LXLX_YQLX = "LXLX_YQLX";
	/**
	 * 利息类型：贴现息
	 */
	public static final String LXLX_TXX = "LXLX_TXX";

	/**
	 * 平台公司级别：JB_GOLD 金牌 1
	 */
	public static final String JB_GOLD = "JB_GOLD";

	/**
	 * 平台公司级别：JB_SILVER 银牌 2
	 */
	public static final String JB_SILVER = "JB_SILVER";

	/**
	 * 平台公司级别：JB_BRONZE 铜牌 3
	 */
	public static final String JB_BRONZE = "JB_BRONZE";

	/**
	 * 平台公司级别：JB_RED 红牌 8
	 */
	public static final String JB_RED = "JB_RED";

	/**
	 * 票据类型：纸质票据
	 */
	public static final String PJLX_ZZPJ = "PJLX_ZZPJ";
	/**
	 * 票据类型：电子票据
	 */
	public static final String PJLX_DZPJ = "PJLX_DZPJ";

	/**
	 * 是否是已办查看参数：ybView
	 */
	public static final String IS_VIEW_YB = "ybView";

	/**
	 * 是否是账户交易流水查看参数：zhlsView
	 */
	public static final String IS_VIEW_ZHLS = "zhlsView";

	/**
	 * 流程图定义bizKey名称：
	 */
	public static class LcName {

		/**
		 * 流程名称：票据贴现 pjtx
		 */
		public static final String PJTX = "pjtx";

		/**
		 * 流程名称：票据下拨 pjxb
		 */
		public static final String PJXB = "pjxb";

		/**
		 * 流程名称：三方转款 sfzk
		 */
		public static final String SFZK = "sfzk";

		/**
		 * 流程名称：线下电汇 xxdh
		 */
		public static final String XXDH = "xxdh";

		/**
		 * 流程名称：总部背书 zbbs
		 */
		public static final String ZBBS = "zbbs";

		/**
		 * 流程名称：总部借款 zbjk
		 */
		public static final String ZBJK = "zbjk";

		/**
		 * 流程名称：总部提现 zbtx
		 */
		public static final String ZBTX = "zbtx";

		/**
		 * 流程名称：总部新办承兑 zbxbcd
		 */
		public static final String ZBXBCD = "zbxbcd";

		/**
		 * 流程名称：自有承兑 zycd
		 */
		public static final String ZYCD = "zycd";

		/**
		 * 流程名称：自有现金 zyxj
		 */
		public static final String ZYXJ = "zyxj";

	}

}
