package com.lte.admin.entity;

import java.util.Date;

public class Zdlx {
    private Integer zdlxid;

    private String zdlxdm;

    private String zdlxmc;

    private String zdlxsm;

    private Integer parentLx;
    
    private String parentLxName;

    private Short zt;

    private String zxbj;

    private String createId;

    private String createName;

    private Date createTime;

    private String updateId;

    private String updateName;

    private Date updateTime;

    public String getParentLxName() {
		return parentLxName;
	}

	public void setParentLxName(String parentLxName) {
		this.parentLxName = parentLxName;
	}

	public Integer getZdlxid() {
        return zdlxid;
    }

    public void setZdlxid(Integer zdlxid) {
        this.zdlxid = zdlxid;
    }

    public String getZdlxdm() {
        return zdlxdm;
    }

    public void setZdlxdm(String zdlxdm) {
        this.zdlxdm = zdlxdm == null ? null : zdlxdm.trim();
    }

    public String getZdlxmc() {
        return zdlxmc;
    }

    public void setZdlxmc(String zdlxmc) {
        this.zdlxmc = zdlxmc == null ? null : zdlxmc.trim();
    }

    public String getZdlxsm() {
        return zdlxsm;
    }

    public void setZdlxsm(String zdlxsm) {
        this.zdlxsm = zdlxsm == null ? null : zdlxsm.trim();
    }

    public Integer getParentLx() {
        return parentLx;
    }

    public void setParentLx(Integer parentLx) {
        this.parentLx = parentLx;
    }

    public Short getZt() {
        return zt;
    }

    public void setZt(Short zt) {
        this.zt = zt;
    }

    public String getZxbj() {
        return zxbj;
    }

    public void setZxbj(String zxbj) {
        this.zxbj = zxbj == null ? null : zxbj.trim();
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