package com.lte.admin.entity;

import java.util.Date;

public class Zdxx {
    private Integer zdxxid;

    private String zdxxdm;
    private String zdxxval;
    private Integer zdlxid;
    private String zdlxName;
    private String zdxxmc;

    private String zdxxsm;

    private Short pxz;

    private String zxbj;

    private Short zt;

    private String createId;

    private String createName;

    private Date createTime;

    private String updateId;

    private String updateName;

    private Date updateTime;

    public String getZdxxval() {
		return zdxxval;
	}

	public void setZdxxval(String zdxxval) {
		this.zdxxval = zdxxval;
	}

	public String getZdlxName() {
		return zdlxName;
	}

	public void setZdlxName(String zdlxName) {
		this.zdlxName = zdlxName;
	}

	public Integer getZdxxid() {
        return zdxxid;
    }

    public void setZdxxid(Integer zdxxid) {
        this.zdxxid = zdxxid;
    }

    public String getZdxxdm() {
        return zdxxdm;
    }

    public void setZdxxdm(String zdxxdm) {
        this.zdxxdm = zdxxdm == null ? null : zdxxdm.trim();
    }

    public Integer getZdlxid() {
        return zdlxid;
    }

    public void setZdlxid(Integer zdlxid) {
        this.zdlxid = zdlxid;
    }

    public String getZdxxmc() {
        return zdxxmc;
    }

    public void setZdxxmc(String zdxxmc) {
        this.zdxxmc = zdxxmc == null ? null : zdxxmc.trim();
    }

    public String getZdxxsm() {
        return zdxxsm;
    }

    public void setZdxxsm(String zdxxsm) {
        this.zdxxsm = zdxxsm == null ? null : zdxxsm.trim();
    }

    public Short getPxz() {
        return pxz;
    }

    public void setPxz(Short pxz) {
        this.pxz = pxz;
    }

    public String getZxbj() {
        return zxbj;
    }

    public void setZxbj(String zxbj) {
        this.zxbj = zxbj == null ? null : zxbj.trim();
    }

    public Short getZt() {
        return zt;
    }

    public void setZt(Short zt) {
        this.zt = zt;
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
}