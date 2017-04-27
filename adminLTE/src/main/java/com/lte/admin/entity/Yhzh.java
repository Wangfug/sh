package com.lte.admin.entity;

/**
 * 调用金力预付款申请单时获取银行相关信息.
 * @author pangsw
 *
 */
public class Yhzh {

	/**
	 * 银行信息
	 */
	private String yhxx;
	
	/**
	 * 银行代码
	 */
	private String yhdm;
	
	private String yhmc;
	
	private String yh;

	/**
	 * @return the yhmc
	 */
	public String getYhmc() {
		return yhmc;
	}

	/**
	 * @param yhmc the yhmc to set
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	/**
	 * @return the yh
	 */
	public String getYh() {
		return yh;
	}

	/**
	 * @param yh the yh to set
	 */
	public void setYh(String yh) {
		this.yh = yh;
	}

	/**
	 * @return the yhxx
	 */
	public String getYhxx() {
		return yhxx;
	}

	/**
	 * @param yhxx the yhxx to set
	 */
	public void setYhxx(String yhxx) {
		this.yhxx = yhxx;
	}

	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}

	/**
	 * @param yhdm the yhdm to set
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	
	
}
