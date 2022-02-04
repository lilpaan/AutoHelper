package com.mydiploma.autohelper.entity;

import java.util.Objects;

public class Card {
    String company;
    String description;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Card() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(company, card.company) && Objects.equals(description, card.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, description);
    }

    @Override
    public String toString() {
        return "Card{" +
                "company='" + company + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
