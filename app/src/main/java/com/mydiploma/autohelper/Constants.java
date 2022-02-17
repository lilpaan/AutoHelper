package com.mydiploma.autohelper;

public class Constants {
    // properties of car adapter
    public static final String ADAPTER_COLOR = "#212870";

    // constants for yandex maps
    public static final String YANDEX_API_KEY = "454f475e-9c32-495c-befa-f45546f51016";

    // titles
    public static final String CAR = "car";
    public static final String BUSINESS_CARD = "businessCard";
    public static final String DISCOUNT_CARD = "discountCard";
    public static final String ID = "id";

    // sql queries for car
    public static final String SQL_CAR_ALL_INFO = "SELECT * FROM car";
    public static final String SQL_CAR_TITLE_INFO = "SELECT id, maker, model, engineVolume," +
            " productionYear, fuelAmount FROM car";
    public static final String SQL_CAR_BY_ID = "SELECT * FROM car WHERE id = :id";

    // sql queries for business card
    public static final String SQL_BUSINESS_CARD_ALL_INFO = "SELECT * FROM BusinessCard";
    public static final String SQL_BUSINESS_CARD_TITLE_INFO = "SELECT id, address FROM BusinessCard";
    public static final String SQL_BUSINESS_CARD_BY_ID = "SELECT * FROM BusinessCard WHERE id = :id";
    public static final String SQL_BUSINESS_CARD_COUNT =
            "SELECT COUNT(*) as businessCount FROM BusinessCard";

    // sql queries for discount card
    public static final String SQL_DISCOUNT_CARD_ALL_INFO = "SELECT * FROM DiscountCard";
    public static final String SQL_DISCOUNT_CARD_TITLE_INFO = "SELECT id, number FROM DiscountCard";
    public static final String SQL_DISCOUNT_CARD_BY_ID = "SELECT * FROM DiscountCard WHERE id = :id";
    public static final String SQL_DISCOUNT_CARD_COUNT =
            "SELECT COUNT(*) as discountCount FROM DiscountCard";

    // sql queries for spare part
    public static final String SQL_SPARE_PART_ALL_INFO = "SELECT * FROM sparePart";
    public static final String SQL_SPARE_PART_TITLE_INFO =
            "SELECT * FROM sparePart WHERE carID = :carID;";
    public static final String SQL_SPARE_PART_BY_ID = "SELECT * FROM sparePart WHERE id = :id";
    public static final String SQL_DELETE_SPARE_PART = "DELETE FROM sparePart WHERE carID = :id";

}
