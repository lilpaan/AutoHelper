package com.mydiploma.autohelper.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class DiscountCard{
    @PrimaryKey(autoGenerate = true)
    long id;
    String number;
    String nfc;
    String barcode;

    public DiscountCard() {
    }

    public DiscountCard(long id, String number, String nfc, String barcode) {
        this.id = id;
        this.number = number;
        this.nfc = nfc;
        this.barcode = barcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id == that.id && number == that.number && Objects.equals(nfc, that.nfc) && Objects.equals(barcode, that.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, nfc, barcode);
    }

    @NonNull
    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number=" + number +
                ", nfc='" + nfc + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
