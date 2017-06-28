package com.lte.admin.custom.entity;

import java.util.Date;

public class CustomerBalanceCash {
    private Long id;

    private Long createBy;

    private Date createTime;

    private Long lastBy;

    private Date lastTime;

    private Double money;

    private String name;

    private String bank;

    private Long accountNum;

    private Integer state;//0-申请提现中..1-已提现.

    private Long transactionNo;

    private Long balanceChangeId;

    private Long cashId;

    public Long getCashId() {
        return cashId;
    }

    public void setCashId(Long cashId) {
        this.cashId = cashId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLastBy() {
        return lastBy;
    }

    public void setLastBy(Long lastBy) {
        this.lastBy = lastBy;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public Long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Long accountNum) {
        this.accountNum = accountNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(Long transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Long getBalanceChangeId() {
        return balanceChangeId;
    }

    public void setBalanceChangeId(Long balanceChangeId) {
        this.balanceChangeId = balanceChangeId;
    }
}