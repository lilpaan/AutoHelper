package com.mydiploma.autohelper.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Refill {
    @PrimaryKey(autoGenerate = true)
    long id;
    String name;
    String address;

    public Refill() {
    }

    public Refill(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Refill refill = (Refill) o;
        return id == refill.id && Objects.equals(name, refill.name) && Objects.equals(address, refill.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @NonNull
    @Override
    public String toString() {
        return "Refill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
