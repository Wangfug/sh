package com.lte.admin.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.lte.admin.common.utils.ConvertUtils;

public class Lcjbxx {
	private Long clid;

	private Short lcnd;

	private String lclx;

	private String lclxmc;

	private String ptgsdm;

	private String ptgsjb;

	private String zjlx;

	private BigDecimal sxed;

	private BigDecimal xkfwbl;

	private BigDecimal ckbzjbl;

	private String spzt;

	private String sqrid;

	private String sqrmc;

	private Date sqsj;

	private String sprid;

	private String sprmc;

	private Date spsj;

	private String scbj;

	private String createId;

	private String createName;

	private Date createTime;

	private String updateId;

	private String updateName;

	private Date updateTime;

	private Long wfid;

	private String hth;

	private String spsjStr;

	private String pjwhrid;

	private String pjwhrm;

	private Date pjwhsj;

	private String pjwhspzt;

	private String ptycqrrid;

	private String ptycqrrm;

	private Date ptycqrsj;

	private String ptycspzt;

	/**
	 * @return the hth
	 */
	public String getHth() {
		return hth;
	}

	/**
	 * @param hth
	 *            the hth to set
	 */
	public void setHth(String hth) {
		this.hth = hth;
	}

	public Long getClid() {
		return clid;
	}

	public void setClid(Long clid) {
		this.clid = clid;
	}

	public Short getLcnd() {
		return lcnd;
	}

	public void setLcnd(Short lcnd) {
		this.lcnd = lcnd;
	}

	public String getLclx() {
		return lclx;
	}

	public void setLclx(String lclx) {
		this.lclx = lclx;
	}

	public String getLclxmc() {
		return lclxmc;
	}

	public void setLclxmc(String lclxmc) {
		this.lclxmc = lclxmc == null ? null : lclxmc.trim();
	}

	public String getPtgsdm() {
		return ptgsdm;
	}

	public void setPtgsdm(String ptgsdm) {
		this.ptgsdm = ptgsdm == null ? null : ptgsdm.trim();
	}

	public String getPtgsjb() {
		return ptgsjb;
	}

	public void setPtgsjb(String ptgsjb) {
		this.ptgsjb = ptgsjb == null ? null : ptgsjb.trim();
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx == null ? null : zjlx.trim();
	}

	public BigDecimal getSxed() {
		return sxed == null ? BigDecimal.ZERO : ConvertUtils.getDecimalScale2(sxed);
	}

	public void setSxed(BigDecimal sxed) {
		this.sxed = sxed == null ? BigDecimal.ZERO : ConvertUtils.getDecimalScale4(sxed);
	}

	public BigDecimal getXkfwbl() {
		return xkfwbl;
	}

	public void setXkfwbl(BigDecimal xkfwbl) {
		this.xkfwbl = xkfwbl;
	}

	public BigDecimal getCkbzjbl() {
		return ckbzjbl;
	}

	public void setCkbzjbl(BigDecimal ckbzjbl) {
		this.ckbzjbl = ckbzjbl;
	}

	public String getSpzt() {
		return spzt;
	}

	public void setSpzt(String spzt) {
		this.spzt = spzt;
	}

	public String getSqrid() {
		return sqrid;
	}

	public void setSqrid(String sqrid) {
		this.sqrid = sqrid == null ? null : sqrid.trim();
	}

	public String getSqrmc() {
		return sqrmc;
	}

	public void setSqrmc(String sqrmc) {
		this.sqrmc = sqrmc == null ? null : sqrmc.trim();
	}

	public Date getSqsj() {
		return sqsj;
	}

	public void setSqsj(Date sqsj) {
		this.sqsj = sqsj;
	}

	public String getSprid() {
		return sprid;
	}

	public void setSprid(String sprid) {
		this.sprid = sprid == null ? null : sprid.trim();
	}

	public String getSprmc() {
		return sprmc;
	}

	public void setSprmc(String sprmc) {
		this.sprmc = sprmc == null ? null : sprmc.trim();
	}

	public Date getSpsj() {
		return spsj;
	}

	public void setSpsj(Date spsj) {
		this.spsj = spsj;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj == null ? null : scbj.trim();
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName == null ? null : createName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName == null ? null : updateName.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getWfid() {
		return wfid;
	}

	public void setWfid(Long wfid) {
		this.wfid = wfid;
	}

	public String getSpsjStr() {
		return spsjStr;
	}

	public void setSpsjStr(String spsjStr) {
		this.spsjStr = spsjStr;
	}

	public String getPjwhrid() {
		return pjwhrid;
	}

	public void setPjwhrid(String pjwhrid) {
		this.pjwhrid = pjwhrid;
	}

	public String getPjwhrm() {
		return pjwhrm;
	}

	public void setPjwhrm(String pjwhrm) {
		this.pjwhrm = pjwhrm;
	}

	public Date getPjwhsj() {
		return pjwhsj;
	}

	public void setPjwhsj(Date pjwhsj) {
		this.pjwhsj = pjwhsj;
	}

	public String getPjwhspzt() {
		return pjwhspzt;
	}

	public void setPjwhspzt(String pjwhspzt) {
		this.pjwhspzt = pjwhspzt;
	}

	public String getPtycqrrid() {
		return ptycqrrid;
	}

	public void setPtycqrrid(String ptycqrrid) {
		this.ptycqrrid = ptycqrrid;
	}

	public String getPtycqrrm() {
		return ptycqrrm;
	}

	public void setPtycqrrm(String ptycqrrm) {
		this.ptycqrrm = ptycqrrm;
	}

	public Date getPtycqrsj() {
		return ptycqrsj;
	}

	public void setPtycqrsj(Date ptycqrsj) {
		this.ptycqrsj = ptycqrsj;
	}

	public String getPtycspzt() {
		return ptycspzt;
	}

	public void setPtycspzt(String ptycspzt) {
		this.ptycspzt = ptycspzt;
	}

}