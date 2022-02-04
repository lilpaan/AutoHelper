package com.mydiploma.autohelper;

public class Constants {
    public static final int CAR_ADAPTER_COLOR_ALPHA = 190;
    public static final int CAR_ADAPTER_COLOR_RED = 127;
    public static final int CAR_ADAPTER_COLOR_GREEN = 255;
    public static final int CAR_ADAPTER_COLOR_BLUE = 0;

    public static final String CAR = "car";

    public static final String  INFLATE_PARAMS = "InflateParams";

    public static final String SQL_CAR_ALL_INFO = "SELECT * FROM car";
    public static final String SQL_CAR_TITLE_INFO = "SELECT id, maker, model, engineVolume," +
            " productionYear, fuelAmount FROM car";
    public static final String SQL_CAR_BY_ID = "SELECT * FROM car WHERE id = :id";
}
