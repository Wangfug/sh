package com.lte.admin.entity;

public class Gwxx {
    private String pkOmJob;

    private String jobcode;

    private String jobname;

    private String pkDeptdoc;

    private String ts;

    private Long dr;

    private String isabort;

    private String deptcode;

    private String deptname;

    public String getPkOmJob() {
        return pkOmJob;
    }

    public void setPkOmJob(String pkOmJob) {
        this.pkOmJob = pkOmJob == null ? null : pkOmJob.trim();
    }

    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode == null ? null : jobcode.trim();
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }

    public String getPkDeptdoc() {
        return pkDeptdoc;
    }

    public void setPkDeptdoc(String pkDeptdoc) {
        this.pkDeptdoc = pkDeptdoc == null ? null : pkDeptdoc.trim();
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public Long getDr() {
        return dr;
    }

    public void setDr(Long dr) {
        this.dr = dr;
    }

    public String getIsabort() {
        return isabort;
    }

    public void setIsabort(String isabort) {
        this.isabort = isabort == null ? null : isabort.trim();
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode == null ? null : deptcode.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }
}