package com.mydiploma.autohelper.entities;

import java.util.Objects;

public class DiscountCard extends Card{
    boolean NFC;
    int cardNumber;

    public boolean isNFC() {
        return NFC;
    }

    public void setNFC(boolean NFC) {
        this.NFC = NFC;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public DiscountCard() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountCard that = (DiscountCard) o;
        return NFC == that.NFC && cardNumber == that.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), NFC, cardNumber);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "NFC=" + NFC +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
