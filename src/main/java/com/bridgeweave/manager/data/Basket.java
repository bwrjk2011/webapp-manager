package com.bridgeweave.manager.data;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Basket extends AbstractEntity{
    private String ownerCompanyId;



    private String ownerCompanyName;
    private String ownerCompanyLogoURL;

    private String basketId;
    private String basketName;
    private String manager;
    @DateTimeFormat
    private String dateLaunch;
    private String risk;
    private Long minimumInvestment;
    private Long subscriptionFee;
    private String benchMark;
    private String urlBasketIcon;
    private String urlFactSheet;
    private String basketDescription;
    private String basketMethodology;


    public String getOwnerCompanyId() {
        return ownerCompanyId;
    }

    public void setOwnerCompanyId(String ownerCompanyId) {
        this.ownerCompanyId = ownerCompanyId;
    }

    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDateLaunch() {
        return dateLaunch;
    }

    public void setDateLaunch(String dateLaunch) {
        this.dateLaunch = dateLaunch;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Long getMinimumInvestment() {
        return minimumInvestment;
    }

    public void setMinimumInvestment(Long minimumInvestment) {
        this.minimumInvestment = minimumInvestment;
    }

    public Long getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(Long subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    public String getBenchMark() {
        return benchMark;
    }

    public void setBenchMark(String benchMark) {
        this.benchMark = benchMark;
    }

    public String getUrlBasketIcon() {
        return urlBasketIcon;
    }

    public void setUrlBasketIcon(String urlBasketIcon) {
        this.urlBasketIcon = urlBasketIcon;
    }

    public String getUrlFactSheet() {
        return urlFactSheet;
    }

    public void setUrlFactSheet(String urlFactSheet) {
        this.urlFactSheet = urlFactSheet;
    }

    public String getBasketDescription() {
        return basketDescription;
    }

    public void setBasketDescription(String basketDescription) {
        this.basketDescription = basketDescription;
    }

    public String getBasketMethodology() {
        return basketMethodology;
    }

    public void setBasketMethodology(String basketMethodology) {
        this.basketMethodology = basketMethodology;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
    }

    public String getOwnerCompanyLogoURL() {
        return ownerCompanyLogoURL;
    }

    public void setOwnerCompanyLogoURL(String ownerCompanyLogoURL) {
        this.ownerCompanyLogoURL = ownerCompanyLogoURL;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }



}
