package com.mydiploma.autohelper;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Car {
    String maker;
    String model;
    float engineVolume;
    TransmissionEnum transmission;
    String color;
    int productionYear;
    float fuelAmount;
    String currentOilBrand;
    String wheelsType;
    Date insuranceRunOutDate;
    SparePart[] spareParts;

    public Car() {
    }

    public Car(String maker, String model, float engineVolume, TransmissionEnum transmission,
               String color, int productionYear, float fuelAmount, String currentOilBrand,
               String wheelsType, Date insuranceRunOutDate, SparePart[] spareParts) {
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
        this.spareParts = spareParts;
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

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
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

    public Date getInsuranceRunOutDate() {
        return insuranceRunOutDate;
    }

    public void setInsuranceRunOutDate(Date insuranceRunOutDate) {
        this.insuranceRunOutDate = insuranceRunOutDate;
    }

    public SparePart[] getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(SparePart[] spareParts) {
        this.spareParts = spareParts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Float.compare(car.engineVolume, engineVolume) == 0 && productionYear == car.productionYear && Float.compare(car.fuelAmount, fuelAmount) == 0 && Objects.equals(maker, car.maker) && Objects.equals(model, car.model) && transmission == car.transmission && Objects.equals(color, car.color) && Objects.equals(currentOilBrand, car.currentOilBrand) && Objects.equals(wheelsType, car.wheelsType) && Objects.equals(insuranceRunOutDate, car.insuranceRunOutDate) && Arrays.equals(spareParts, car.spareParts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(maker, model, engineVolume, transmission, color, productionYear, fuelAmount, currentOilBrand, wheelsType, insuranceRunOutDate);
        result = 31 * result + Arrays.hashCode(spareParts);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", engineVolume=" + engineVolume +
                ", transmission=" + transmission +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                ", fuelAmount=" + fuelAmount +
                ", currentOilBrand='" + currentOilBrand + '\'' +
                ", wheelsType='" + wheelsType + '\'' +
                ", insuranceRunOutDate=" + insuranceRunOutDate +
                ", spareParts=" + Arrays.toString(spareParts) +
                '}';
    }

    enum TransmissionEnum{
        MECHANICAL,
        AUTOMATIC,
        ROBOTIC,
        VARIABLE
    };
}
