package com.mydiploma.autohelper.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity
public class SparePart {
    @PrimaryKey(autoGenerate = true)
    long id;
    long carID;
    String type;
    String maker;
    Date installationDate;

    public SparePart() {
    }

    public SparePart(long id, long carID, String type, String maker, Date installationDate) {
        this.id = id;
        this.carID = carID;
        this.type = type;
        this.maker = maker;
        this.installationDate = installationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparePart sparePart = (SparePart) o;
        return id == sparePart.id && carID == sparePart.carID && Objects.equals(type, sparePart.type) && Objects.equals(maker, sparePart.maker) && Objects.equals(installationDate, sparePart.installationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carID, type, maker, installationDate);
    }

    @NonNull
    @Override
    public String toString() {
        return "SparePart{" +
                "id=" + id +
                ", carID=" + carID +
                ", type='" + type + '\'' +
                ", maker='" + maker + '\'' +
                ", installationDate=" + installationDate +
                '}';
    }

}
