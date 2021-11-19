package com.mydiploma.autohelper;

import java.util.Date;

public class Car {
    boolean model;
    String maker;
    float engineVolume;
    transmissionEnum transmission;
    String color;
    Date changeOilDate;
    int productionYear;
    boolean health;
    Date insuranceEndDate;

    public boolean isModel() {
        return model;
    }

    public void setModel(boolean model) {
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public transmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(transmissionEnum transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getChangeOilDate() {
        return changeOilDate;
    }

    public void setChangeOilDate(Date changeOilDate) {
        this.changeOilDate = changeOilDate;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public boolean isHealth() {
        return health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public Car(boolean model) {
    }

    enum transmissionEnum{
        MANUAL,
        AUTOMATIC,
        CONTINUOUSLY
    };
}
