package com.bridgeweave.manager.data;

import jakarta.persistence.Entity;

@Entity
public class ModelPortfolio extends AbstractEntity{
    private String ownerCompanyId;
    private String ownerCompanyName;
    private Long bid;

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    private String ticker;
    private String name;
    private Long allocation;
    private String rationale;

    public String getOwnerCompanyId() {
        return ownerCompanyId;
    }

    public void setOwnerCompanyId(String ownerCompanyId) {
        this.ownerCompanyId = ownerCompanyId;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAllocation() {
        return allocation;
    }

    public void setAllocation(Long allocation) {
        this.allocation = allocation;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }




}

