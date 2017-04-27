package com.lte.admin.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Tzgg {
    private BigDecimal id;

    private String tzbt;

    private String lx;

    private String zdbz;

    private String scbz;

    private String createId;

    private String createName;

    private Date createTime;

    private String updateId;

    private String updateName;

    private Date updateTime;

    private byte[] tznr;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTzbt() {
        return tzbt;
    }

    public void setTzbt(String tzbt) {
        this.tzbt = tzbt == null ? null : tzbt.trim();
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx == null ? null : lx.trim();
    }

    public String getZdbz() {
        return zdbz;
    }

    public void setZdbz(String zdbz) {
        this.zdbz = zdbz == null ? null : zdbz.trim();
    }

    public String getScbz() {
        return scbz;
    }

    public void setScbz(String scbz) {
        this.scbz = scbz == null ? null : scbz.trim();
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

    public byte[] getTznr() {
        return tznr;
    }

    public void setTznr(byte[] tznr) {
        this.tznr = tznr;
    }
}