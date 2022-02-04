package com.mydiploma.autohelper.entity;

import java.util.Date;
import java.util.Objects;

public class SparePart {
    String type;
    String maker;
    Date installationDate;

    public SparePart() {
    }

    public SparePart(String type, String maker, Date installationDate) {
        this.type = type;
        this.maker = maker;
        this.installationDate = installationDate;
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
        return Objects.equals(type, sparePart.type) && Objects.equals(maker, sparePart.maker) && Objects.equals(installationDate, sparePart.installationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, maker, installationDate);
    }

    @Override
    public String toString() {
        return "SparePart{" +
                "type='" + type + '\'' +
                ", maker='" + maker + '\'' +
                ", installationDate=" + installationDate +
                '}';
    }
}
