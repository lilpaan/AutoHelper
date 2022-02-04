package com.mydiploma.autohelper;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Car {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    long id;
    String maker;
    String model;
    float engineVolume;
    String transmission;
    String color;
    int productionYear;
    float fuelAmount;
    String currentOilBrand;
    String wheelsType;
    String insuranceRunOutDate;

    public Car() {
    }

    @Ignore
    public Car(long id, String maker, String model, float engineVolume, int productionYear, float fuelAmount) {
        this.id = id;
        this.maker = maker;
        this.model = model;
        this.engineVolume = engineVolume;
        this.productionYear = productionYear;
        this.fuelAmount = fuelAmount;
    }

    public Car(long id, String maker, String model, float engineVolume, String transmission,
               String color, int productionYear, float fuelAmount, String currentOilBrand,
               String wheelsType, String insuranceRunOutDate) {
        this.id = id;
        this.maker = maker;
        this.model = model;
        this.engineVolume = engineVolume;
        this.transmission = transmission;
        this.color = color;
        this.productionYear = productionYear;
        this.fuelAmount = fuelAmount;
        this.currentOilBrand = currentOilBrand;
        this.wheelsType = wheelsType;
        this.insuranceRunOutDate = insuranceRunOutDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public String getCurrentOilBrand() {
        return currentOilBrand;
    }

    public void setCurrentOilBrand(String currentOilBrand) {
        this.currentOilBrand = currentOilBrand;
    }

    public String getWheelsType() {
        return wheelsType;
    }

    public void setWheelsType(String wheelsType) {
        this.wheelsType = wheelsType;
    }

    public String getInsuranceRunOutDate() {
        return insuranceRunOutDate;
    }

    public void setInsuranceRunOutDate(String insuranceRunOutDate) {
        this.insuranceRunOutDate = insuranceRunOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Float.compare(car.engineVolume, engineVolume) == 0 && productionYear == car.productionYear && Float.compare(car.fuelAmount, fuelAmount) == 0 && Objects.equals(maker, car.maker) && Objects.equals(model, car.model) && Objects.equals(transmission, car.transmission) && Objects.equals(color, car.color) && Objects.equals(currentOilBrand, car.currentOilBrand) && Objects.equals(wheelsType, car.wheelsType) && Objects.equals(insuranceRunOutDate, car.insuranceRunOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maker, model, engineVolume, transmission, color, productionYear, fuelAmount, currentOilBrand, wheelsType, insuranceRunOutDate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", engineVolume=" + engineVolume +
                ", transmission='" + transmission + '\'' +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                ", fuelAmount=" + fuelAmount +
                ", currentOilBrand='" + currentOilBrand + '\'' +
                ", wheelsType='" + wheelsType + '\'' +
                ", insuranceRunOutDate='" + insuranceRunOutDate + '\'' +
                '}';
    }

    enum TransmissionEnum{
        MECHANICAL,
        AUTOMATIC,
        ROBOTIC,
        VARIABLE
    };
}
