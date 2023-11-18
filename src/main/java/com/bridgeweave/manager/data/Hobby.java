package com.bridgeweave.manager.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

@Entity
public class Hobby extends AbstractEntity {



    private String name;
    private String dangerLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }
}
