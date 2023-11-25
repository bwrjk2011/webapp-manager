package com.bridgeweave.manager.data;

import jakarta.persistence.Entity;

@Entity
public class Equities extends AbstractEntity {
    private String companyName;
    private String industry;
    private String symbol;
    private String isinCode;
    private String series;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndusty() {
        return industry;
    }

    public void setIndusty(String industy) {
        this.industry = industy;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
