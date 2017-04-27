package com.lte.admin.entity;

import java.math.BigDecimal;

import com.lte.admin.common.utils.ConvertUtils;

public class Txxx {
	private Long clid;

	private String txyt;

	private BigDecimal txjexx;

	private String txjedx;

	private String fkfs;

	private String fkhmc;

	private String fkhzh;

	private String skhmc;

	private String skhzh;

	private String bz;

	private BigDecimal spje;

	private String opt;

	private String ptgsdm;

	private String scbj;

	private String taskId;

	private boolean test;

	public Long getClid() {
		return clid;
	}

	public void setClid(Long clid) {
		this.clid = clid;
	}

	public String getTxyt() {
		return txyt;
	}

	public void setTxyt(String txyt) {
		this.txyt = txyt == null ? null : txyt.trim();
	}

	public BigDecimal getTxjexx() {
		return txjexx == null ? BigDecimal.ZERO : txjexx;
	}

	public void setTxjexx(BigDecimal txjexx) {
		this.txjexx = txjexx == null ? BigDecimal.ZERO : ConvertUtils.getDecimalScale4(txjexx);
	}

	public String getTxjedx() {
		return txjedx;
	}

	public void setTxjedx(String txjedx) {
		this.txjedx = txjedx == null ? null : txjedx.trim();
	}

	public String getFkfs() {
		return fkfs;
	}

	public void setFkfs(String fkfs) {
		this.fkfs = fkfs == null ? null : fkfs.trim();
	}

	public String getFkhmc() {
		return fkhmc;
	}

	public void setFkhmc(String fkhmc) {
		this.fkhmc = fkhmc == null ? null : fkhmc.trim();
	}

	public String getFkhzh() {
		return fkhzh;
	}

	public void setFkhzh(String fkhzh) {
		this.fkhzh = fkhzh == null ? null : fkhzh.trim();
	}

	public String getSkhmc() {
		return skhmc;
	}

	public void setSkhmc(String skhmc) {
		this.skhmc = skhmc == null ? null : skhmc.trim();
	}

	public String getSkhzh() {
		return skhzh;
	}

	public void setSkhzh(String skhzh) {
		this.skhzh = skhzh == null ? null : skhzh.trim();
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz == null ? null : bz.trim();
	}

	public BigDecimal getSpje() {
		return spje == null ? BigDecimal.ZERO : spje;
	}

	public void setSpje(BigDecimal spje) {
		this.spje = spje == null ? null : ConvertUtils.getDecimalScale4(spje);
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getPtgsdm() {
		return ptgsdm;
	}

	public void setPtgsdm(String ptgsdm) {
		this.ptgsdm = ptgsdm;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public void writeFromParamBean(Txxx form) {
		this.txyt = form.getTxyt();
		this.txjexx = form.getTxjexx();
		this.txjedx = form.getTxjedx();
		this.fkfs = form.getFkfs();
		this.fkhmc = form.getFkhmc();
		this.fkhzh = form.getFkhzh();
		this.skhmc = form.getSkhmc();
		this.skhzh = form.getSkhzh();
		this.bz = form.getBz();
		this.spje = form.getSpje();
		this.opt = form.getOpt();
		this.ptgsdm = form.getPtgsdm();
		this.scbj = form.getScbj();
	}
}