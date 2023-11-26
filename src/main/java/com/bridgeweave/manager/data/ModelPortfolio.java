package com.bridgeweave.manager.data;

import jakarta.persistence.Entity;

@Entity
public class ModelPortfolio extends AbstractEntity{
    private String ownerCompanyId;
    private String ownerCompanyName;
    private Long bid;
    private String symbol;
    private String name;
    private Float allocation;
    private String rationale;


    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }



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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAllocation() {
        return allocation;
    }

    public void setAllocation(Float allocation) {
        this.allocation = allocation;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }




}

