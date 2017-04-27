package com.lte.admin.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Sjxx {
    private Long id;

    private String sjlx;

    private BigDecimal sjval;

    private Date ksrq;

    private Date jsrq;

    private String tybj;

    private String createId;

    private String createName;

    private Date createTime;

    private String updateId;

    private String updateName;

    private Date updateTime;
    
    public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSjlx() {
        return sjlx;
    }

    public void setSjlx(String sjlx) {
        this.sjlx = sjlx == null ? null : sjlx.trim();
    }

    public BigDecimal getSjval() {
        return sjval;
    }

    public void setSjval(BigDecimal sjval) {
        this.sjval = sjval;
    }

    public Date getKsrq() {
        return ksrq;
    }

    public void setKsrq(Date ksrq) {
        this.ksrq = ksrq;
    }

    public Date getJsrq() {
        return jsrq;
    }

    public void setJsrq(Date jsrq) {
        this.jsrq = jsrq;
    }

    public String getTybj() {
        return tybj;
    }

    public void setTybj(String tybj) {
        this.tybj = tybj == null ? null : tybj.trim();
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
}