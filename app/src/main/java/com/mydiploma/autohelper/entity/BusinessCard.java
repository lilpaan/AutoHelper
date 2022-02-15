package com.mydiploma.autohelper.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class BusinessCard {
    @PrimaryKey(autoGenerate = true)
    long id;
    String phoneNumber;
    String address;
    String email;
    String site;

    public BusinessCard() {
    }

    public BusinessCard(long id, String phoneNumber, String address, String email, String site) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.site = site;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessCard that = (BusinessCard) o;
        return id == that.id && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(site, that.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, address, email, site);
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
