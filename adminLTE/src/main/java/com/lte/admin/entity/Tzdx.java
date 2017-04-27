package com.lte.admin.entity;

import java.util.Date;

public class Tzdx {
    private Long id;

    private String jsq;

    private String dqbz;

    private Date dqsj;

    private Long tzid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJsq() {
        return jsq;
    }

    public void setJsq(String jsq) {
        this.jsq = jsq == null ? null : jsq.trim();
    }

    public String getDqbz() {
        return dqbz;
    }

    public void setDqbz(String dqbz) {
        this.dqbz = dqbz == null ? null : dqbz.trim();
    }

    public Date getDqsj() {
        return dqsj;
    }

    public void setDqsj(Date dqsj) {
        this.dqsj = dqsj;
    }

    public Long getTzid() {
        return tzid;
    }

    public void setTzid(Long tzid) {
        this.tzid = tzid;
    }
}