package com.lte.admin.common.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.utils.Global;
import com.lte.admin.common.utils.StringUtils;
import com.lte.admin.entity.Bm;
import com.lte.admin.entity.Gsxx;
import com.lte.admin.entity.Lcjbxx;
import com.lte.admin.entity.Role;
import com.lte.admin.entity.Sjxx;
import com.lte.admin.entity.ZdlxZdxxBean;
import com.lte.admin.system.utils.UserUtil;

/**
 * 共通处理DAO
 * 
 * @author yanzai
 * @date 2015年12月18日
 */
@Repository
public class CommDao extends BaseDao {

	private static Logger logger = LoggerFactory.getLogger(CommDao.class);

	/**
	 * 根据登陆人查询其所在的公司代码
	 * 
	 * @author yanzai
	 * @return String
	 */
	public String getLoginUserGsdm() {
		String deptcode = UserUtil.getCurrentUser().getDeptCode();
		if (StringUtils.isNotBlank(deptcode)) {
			return deptcode.substring(0, 4);
		} else {
			return "";
		}
	}

	/**
	 * 获取分部公司名称根据分部公司代码
	 * 
	 * @author yanzai
	 * @param fbgsdm
	 * @return
	 */
	public String getFbgsmcByDm(String fbgsdm) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.GsxxMapper.getFbgsmcByDm", fbgsdm);
	}

	/**
	 * 根据代码获取公司V_GSXX视图信息
	 * 
	 * @author yanzai
	 * @param dm
	 * @return
	 */
	public Gsxx getVgsxxByDm(String dm) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.GsxxMapper.selectVgsxxByDm", dm);
	}

	/**
	 * 获取公司信息表中所有的分部公司
	 * 
	 * @author yanzai
	 * @return List
	 */
	public List<Gsxx> getAllFbgsList() {
		return sqlSessionTemplate.selectList("cn.htd.xdpt.mapper.GsxxMapper.selectAllFbgs");
	}

	/**
	 * 获取公司信息表中所有的平台公司
	 * 
	 * @author yanzai
	 * @return List
	 */
	public List<Gsxx> getAllPtgsList() {
		return sqlSessionTemplate.selectList("cn.htd.xdpt.mapper.GsxxMapper.selectAllPtgs");
	}

	/**
	 * 获取分部公司下属的平台公司
	 * 
	 * @author yanzai
	 * @param fbgs
	 * @return List
	 */
	public List<Gsxx> getPtgsOfFbgs(String fbgs) {
		return sqlSessionTemplate.selectList("cn.htd.xdpt.mapper.GsxxMapper.selectPtgsOfFbgs", fbgs);
	}

	/**
	 * 获取平台公司信息根据主键平台公司代码
	 * 
	 * @author yanzai
	 * @param gsxxCode
	 */
	public Gsxx getPtgsInfo(String gsxxCode) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.GsxxMapper.selectByPrimaryKey", gsxxCode);
	};

	/**
	 * 获取流程信息表的主键序列值
	 * 
	 * @author yanzai
	 * @return
	 */
	public Long getLcxxSeq() {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.LcjbxxMapper.selectSeq");
	}

	/**
	 * 获取账户流水表的主键序列值
	 * 
	 * @author yanzai
	 * @return
	 */
	public Long getZhlsLshSeq() {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.ZhlsMapper.getZhlsLshSeq");
	}

	/**
	 * 插入流程信息表
	 * 
	 * @param lcxx
	 * @return
	 */
	public int saveLcjbxx(Lcjbxx lcxx) {
		return sqlSessionTemplate.insert("cn.htd.xdpt.mapper.LcjbxxMapper.insert", lcxx);
	}

	/**
	 * 获取流程基本信息根据处理ID主键
	 * 
	 * @author yanzai
	 * @param clid
	 * @return
	 */
	public Lcjbxx getLcjbxxByKeyClid(Long clid) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.LcjbxxMapper.selectByPrimaryKey", clid);
	}

	/**
	 * 获取流程基本信息根据userid主键
	 * 
	 * @param userid
	 * @return
	 */
	public List<Lcjbxx> getLcjbxxByUserid(PageBounds pb, String userid) {
		return sqlSessionTemplate.selectList("getLcjbxxListByUserid", userid, pb);
	}

	/**
	 * 更新流程基本信息根据处理ID主键
	 * 
	 * @author yanzai
	 * @param clid
	 * @return
	 */
	public int updateLcjbxxByKey(Lcjbxx lcjbxx) {
		return sqlSessionTemplate.update("cn.htd.xdpt.mapper.LcjbxxMapper.updateByPrimaryKey", lcjbxx);
	}

	/**
	 * 根据公司ID获取所有部门信忍
	 * 
	 * @param gsdm
	 * @return
	 */
	public List<Bm> getBmListByGsid(String gsid) {
		return sqlSessionTemplate.selectList("getBmListByGsid", gsid);
	}

	/**
	 * 根据平台公司代码获取平台公司表信息
	 * 
	 * @param param
	 * @return
	 */
	public Gsxx getGsxxByKey(String param) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.GsxxMapper.selectByPrimaryKey", param);
	}

	/**
	 * 获取合同号
	 * 
	 * @param gsbm
	 *            公司编码
	 * @param gysbm
	 *            供应商编码
	 * @param bmbm
	 *            部门编码
	 * @return 合同号
	 */
	public String getHTH(String gsbm, String gysbm, String bmbm) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("gsbm", gsbm);
		param.put("gysbm", gysbm);
		param.put("bmbm", bmbm);

		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.CommonViewMapper.selectHTH", param);
	}

	/**
	 * 取得指定类型的字典信息
	 * 
	 * @param rootId
	 * @return
	 */
	public List<ZdlxZdxxBean> selectZdxxByRootId(Integer rootId) {
		return sqlSessionTemplate.selectList("cn.htd.xdpt.mapper.ZdlxZdxxMapper.selectZdxxByRootId", rootId);
	}

	/**
	 * 取得指定类型定义的根节点
	 * 
	 * @param zdlx
	 * @return
	 */
	public ZdlxZdxxBean selectRootZdlxByZdlx(String zdlx) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.ZdlxZdxxMapper.selectRootZdlxByZdlx", zdlx);
	}

	/**
	 * 取得 总部存票余额
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param gsbm
	 *            公司编码
	 * @return 总部存票余额
	 */
	public BigDecimal getZbcpye(String year, String month, String gsbm) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("year", year);
		param.put("month", month);
		param.put("gsbm", gsbm);
		String subjcode = Global.getConfig("v_oa_wlye_cpye");
		if (StringUtils.isBlank(subjcode)) {
			subjcode = "110005";
		}
		param.put("subjcode", subjcode);
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.CommonViewMapper.selectZbcpye", param);
	}

	/**
	 * 根据供应商编码取得平台公司名称(从金力系统获取)
	 * 
	 * @param gysbm
	 *            供应商编码
	 * @return 平台公司名称
	 */
	public String getPtgsmcByGysbm(String gysbm) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.CommonViewMapper.selectPtgsmc", gysbm);
	}

	/**
	 * 根据供应商编码取得平台公司代码(从金力系统获取)
	 * 
	 * @param gysbm
	 *            供应商编码
	 * @return 平台公司名称
	 */
	public String getPtgsmcByGysdm(String gysbm) {
		return sqlSessionTemplate.selectOne("cn.htd.xdpt.mapper.CommonViewMapper.selectPtgsdm", gysbm);
	}

	/**
	 * 根据登陆人查询其岗位角色
	 * 
	 * @param pkPsnbasdoc
	 * @return
	 */
	public List<Role> getLoginUserGwjs(String pkPsnbasdoc) {
		return sqlSessionTemplate.selectList("cn.htd.xdpt.mapper.RoleMapper.getLoginUserGwjsByPkPsnbasdoc",
				pkPsnbasdoc);
	}

	/**
	 * 取得数据信息
	 * 
	 * @param sjxxKey
	 * @return
	 */
	public List<Sjxx> selectSyDataBySysdt(Sjxx sjxxKey) {
		return sqlSessionTemplate.selectList("selectSyDataBySysdt", sjxxKey);
	}
}
