package com.bridgeweave.manager.data;

import jakarta.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class UserNotifications extends AbstractEntity{
    private String userId;
    private Date createdDate;
    private String notificationType;
    private String message;
    private String successCode;
    private Boolean hasBeenSeen;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccesCode() {
        return successCode;
    }

    public void setSuccesCode(String successCode) {
        this.successCode = successCode;
    }

    public Boolean getHasBeenSeen() {
        return hasBeenSeen;
    }

    public void setHasBeenSeen(Boolean hasBeenSeen) {
        this.hasBeenSeen = hasBeenSeen;
    }
}
