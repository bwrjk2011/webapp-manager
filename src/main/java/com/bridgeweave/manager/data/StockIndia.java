package com.bridgeweave.manager.data;

import jakarta.persistence.Entity;

@Entity
public class StockIndia extends AbstractEntity {
    private String ricTicker;
    private String cortexTicker;
    private String companyName;
    private double companyMarketCap;
    private String country;
    private String instrumentType;
    private String exchangeName;
    private String bwSectors;
    private String bwIndustryGroup;
    private String currencyCode;
    private String currencyName;
    private String gicsIndustryGroup;
    private String gicsSectors;
    private String marketIndex;
    private String sectorCode;
    private double universeId;
    private long companyId;
    private boolean activeFlag;
    private String marketCode;
    private String isinCode;



    public String getRicTicker() {
        return ricTicker;
    }

    public void setRicTicker(String ricTicker) {
        this.ricTicker = ricTicker;
    }

    public String getCortexTicker() {
        return cortexTicker;
    }

    public void setCortexTicker(String cortexTicker) {
        this.cortexTicker = cortexTicker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getCompanyMarketCap() {
        return companyMarketCap;
    }

    public void setCompanyMarketCap(double companyMarketCap) {
        this.companyMarketCap = companyMarketCap;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getBwSectors() {
        return bwSectors;
    }

    public void setBwSectors(String bwSectors) {
        this.bwSectors = bwSectors;
    }

    public String getBwIndustryGroup() {
        return bwIndustryGroup;
    }

    public void setBwIndustryGroup(String bwIndustryGroup) {
        this.bwIndustryGroup = bwIndustryGroup;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getGicsIndustryGroup() {
        return gicsIndustryGroup;
    }

    public void setGicsIndustryGroup(String gicsIndustryGroup) {
        this.gicsIndustryGroup = gicsIndustryGroup;
    }

    public String getGicsSectors() {
        return gicsSectors;
    }

    public void setGicsSectors(String gicsSectors) {
        this.gicsSectors = gicsSectors;
    }

    public String getMarketIndex() {
        return marketIndex;
    }

    public void setMarketIndex(String marketIndex) {
        this.marketIndex = marketIndex;
    }

    public String getSectorCode() {
        return sectorCode;
    }

    public void setSectorCode(String sectorCode) {
        this.sectorCode = sectorCode;
    }

    public double getUniverseId() {
        return universeId;
    }

    public void setUniverseId(double universeId) {
        this.universeId = universeId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }
}
