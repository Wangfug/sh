package com.lte.admin.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lte.admin.common.consts.ScbzEnum;
import com.lte.admin.common.dao.CommDao;
import com.lte.admin.common.utils.StringUtils;
import com.lte.admin.entity.DictBean;
import com.lte.admin.entity.Sjxx;
import com.lte.admin.entity.ZdlxZdxxBean;

@Service
public class DictionaryService {

	@Autowired
	private CommDao commDao;

	private Map<String, List<DictBean>> zdlxMap = new HashMap<String, List<DictBean>>();

	private Map<String, List<DictBean>> zdlxAllMap = new HashMap<String, List<DictBean>>();

	private Map<String, BigDecimal> sjlxMap = new HashMap<String, BigDecimal>();

	private boolean isBatchFlg = false;

	private void initSjxxMap(String sjlx) {
		List<Sjxx> sjxxList = new ArrayList<Sjxx>();
		Sjxx sjxxKey = new Sjxx();
		String uSjlx = sjlx.toUpperCase();

		// 查询数据信息表中定义数据类型数据
		sjxxKey.setTybj(ScbzEnum.STATE_NORMAL.getCode());
		sjxxKey.setSjlx(uSjlx);
		sjxxList = commDao.selectSyDataBySysdt(sjxxKey);

		if (sjxxList == null || sjxxList.isEmpty()) {
			sjlxMap.put(uSjlx, BigDecimal.ZERO);
		} else {
			sjlxMap.put(uSjlx, sjxxList.get(0).getSjval());
		}
	}

	private void initDictMap(String tzdlx) {
		ZdlxZdxxBean rootZdlx = null;
		List<ZdlxZdxxBean> zdxxList = null;
		String zdlx = tzdlx.toUpperCase();
		DictBean dictBean = null;
		List<DictBean> dictBeanAllList = new ArrayList<DictBean>();
		boolean skipAllFlg = false;
		List<DictBean> dictBeanList = new ArrayList<DictBean>();
		boolean skipFlg = false;
		rootZdlx = commDao.selectRootZdlxByZdlx(zdlx);
		// 字典信息不存在
		if (rootZdlx == null) {
			return;
		}
		zdxxList = commDao.selectZdxxByRootId(rootZdlx.getRootid());
		// 字典信息不存在
		if (zdxxList == null || zdxxList.isEmpty()) {
			return;
		}
		// 做成字典信息List
		for (ZdlxZdxxBean zdxx : zdxxList) {
			skipAllFlg = false;
			skipFlg = false;
			dictBean = new DictBean();
			dictBean.setId(zdxx.getZdxxid());
			dictBean.setCode(zdxx.getZdxxdm().toUpperCase());
			dictBean.setValue(zdxx.getZdxxval());
			dictBean.setExtend("");
			if (zdxx.getParentLx() != 0) {
				// dictBean.setName(zdxx.getZdlxmc() + "-" + zdxx.getZdxxmc());
				dictBean.setName(zdxx.getZdxxmc());
				dictBean.setExtend("," + zdxx.getZdlxdm().toUpperCase() + ",");
			} else {
				dictBean.setName(zdxx.getZdxxmc());
			}
			dictBean.setDescription(
					zdxx.getZdxxmc() + (StringUtils.isBlank(zdxx.getZdxxsm()) ? "" : "(" + zdxx.getZdxxsm() + ")"));
			dictBean.setZxbj(zdxx.getZdxxzxbj());
			for (DictBean tmpDictBean : dictBeanAllList) {
				if (dictBean.getCode().equals(tmpDictBean.getCode())) {
					tmpDictBean.setExtend(tmpDictBean.getExtend() + "," + zdxx.getZdlxdm().toUpperCase() + ",");
					skipAllFlg = true;
					break;
				}
			}
			if (!ScbzEnum.STATE_DELETED.getCode().equals(dictBean.getZxbj())) {
				for (DictBean tmpDictBean : dictBeanList) {
					if (dictBean.getCode().equals(tmpDictBean.getCode())) {
						tmpDictBean.setExtend(tmpDictBean.getExtend() + "," + zdxx.getZdlxdm().toUpperCase() + ",");
						skipFlg = true;
						break;
					}
				}
			}
			if (!skipAllFlg) {
				dictBeanAllList.add(dictBean);
			}
			if (!skipFlg) {
				dictBeanList.add(dictBean);
			}
		}
		zdlxAllMap.put(zdlx, dictBeanAllList);
		if (!ScbzEnum.STATE_DELETED.getCode().equals(rootZdlx.getZdlxzxbj())) {
			zdlxMap.put(zdlx, dictBeanList);
		} else {
			zdlxMap.put(zdlx, new ArrayList<DictBean>());
		}
	}

	public BigDecimal getSjxx(String sjlx) {
		String uSjlx = sjlx.toUpperCase();
		if (!sjlxMap.containsKey(uSjlx) || this.isBatchFlg) {
			initSjxxMap(uSjlx);
		}
		return sjlxMap.get(uSjlx);
	}

	public List<DictBean> getZdxx(String tzdlx) {
		List<DictBean> retList = new ArrayList<DictBean>();
		String zdlx = tzdlx.toUpperCase();
		if (!zdlxMap.containsKey(zdlx) || this.isBatchFlg) {
			initDictMap(zdlx);
		}
		if (!zdlxMap.get(zdlx).isEmpty()) {
			for (DictBean bean : zdlxMap.get(zdlx)) {
				retList.add(bean.clone());
			}
		}
		return retList;
	}

	public List<DictBean> getZdxxByExtend(String tzdlx, String extend) {
		List<DictBean> zdxxList = new ArrayList<DictBean>();
		List<DictBean> retList = new ArrayList<DictBean>();
		String zdlx = tzdlx.toUpperCase();

		zdxxList = getZdxx(zdlx);
		if (!zdxxList.isEmpty()) {
			for (DictBean bean : zdxxList) {
				if (bean.getExtend().contains("," + extend.toUpperCase() + ",")) {
					retList.add(bean.clone());
				}
			}
		}
		return retList;
	}

	public String getName(String zdlx, String val) {
		DictBean bean = getDictBeanByVal(zdlx, val);
		return StringUtils.trimToEmpty(bean.getName());
	}

	public String getDescription(String zdlx, String val) {
		DictBean bean = getDictBeanByVal(zdlx, val);
		return StringUtils.trimToEmpty(bean.getDescription());
	}

	public String getValueByCd(String zdlx, String code) {
		DictBean bean = getDictBeanByCd(zdlx, code);
		return StringUtils.trimToEmpty(bean.getValue());
	}

	public String getNameByCd(String zdlx, String code) {
		DictBean bean = getDictBeanByCd(zdlx, code);
		return StringUtils.trimToEmpty(bean.getName());
	}

	public String getDescriptionByCd(String zdlx, String code) {
		DictBean bean = getDictBeanByCd(zdlx, code);
		return StringUtils.trimToEmpty(bean.getDescription());
	}

	public DictBean getDictBeanByCd(String tzdlx, String code) {
		List<DictBean> zdxxList = new ArrayList<DictBean>();
		zdxxList = getAllZdxx(tzdlx);
		if (!zdxxList.isEmpty()) {
			for (DictBean bean : zdxxList) {
				if (code.toUpperCase().equals(bean.getCode())) {
					return bean;
				}
			}
		}
		return new DictBean();
	}

	public DictBean getDictBeanByVal(String tzdlx, String val) {
		List<DictBean> zdxxList = new ArrayList<DictBean>();
		zdxxList = getAllZdxx(tzdlx);
		if (!zdxxList.isEmpty()) {
			for (DictBean bean : zdxxList) {
				if (val.equals(bean.getValue())) {
					return bean;
				}
			}
		}
		return new DictBean();
	}

	private List<DictBean> getAllZdxx(String tzdlx) {
		List<DictBean> retList = new ArrayList<DictBean>();
		String zdlx = tzdlx.toUpperCase();
		if (!zdlxAllMap.containsKey(zdlx) || this.isBatchFlg) {
			initDictMap(zdlx);
		}
		if (!zdlxAllMap.get(zdlx).isEmpty()) {
			for (DictBean bean : zdlxAllMap.get(zdlx)) {
				retList.add(bean.clone());
			}
		}
		return retList;
	}

	public void setBatchFlg() {
		this.isBatchFlg = true;
	}
}
