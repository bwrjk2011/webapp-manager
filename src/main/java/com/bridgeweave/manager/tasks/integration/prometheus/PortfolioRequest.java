package com.bridgeweave.manager.tasks.integration.prometheus;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PortfolioRequest {


    @JsonProperty("portfolioId")
    private String portfolioId;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("constituents")
    private List<Constituent> constituents;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Constituent> getConstituents() {
        return constituents;
    }

    public void setConstituents(List<Constituent> constituents) {
        this.constituents = constituents;
    }
}
