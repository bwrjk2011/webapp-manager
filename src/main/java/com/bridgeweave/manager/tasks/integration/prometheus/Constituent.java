package com.bridgeweave.manager.tasks.integration.prometheus;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Constituent {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("weight")
    private float weight;

    @JsonProperty("isin")
    private String isin;

    @JsonProperty("status")
    private String status;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

