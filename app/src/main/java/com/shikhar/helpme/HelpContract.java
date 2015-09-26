package com.shikhar.helpme;

import android.provider.BaseColumns;

/**
 * Created by dell pc on 25-04-2015.
 */
public class HelpContract implements BaseColumns {
    private HelpContract(){}

    public static final String DB_NAME="HelpMe";
    public static final String WORKER_TABLE="Worker";
    public static final String WORKER_TABLE_USERNAME_COL="Worker Username";
    public static final String WORKER_TABLE_PASSWORD_COL="Password";
    public static final String WORKER_TABLE_ID="WORKER_ID";
    public static final String WORKER_TABLE_CHARGES_HOUR_COL="Charges";
    public static final String WORKER_TABLE_NAME_COL="Name";
    public static final String WORKER_TABLE_AGE_COL="Age";
    public static final String WORKER_TABLE_PROFESSION_COL="Profession";
    public static final String WORKER_TABLE_GENDER_COL="Gender";
    public static final String WORKER_TABLE_ADDRESS_COL="Address";
    public static final String WORKER_TABLE_PHONE_NO_COL="Phone Number";
    public static final String WORKER_TABLE_PICTURE_COL="Picture";
    public static final String WORKER_TABLE_NO_OF_RATING_COL="Count";
    public static final String WORKER_TABLE_STARS_COL="Stars";

    public static final String AVAILABLE_TABLE="Available";
    public static final String AVILABLE_TABLE_ID="AVAILABLE_ID";
    public static final String AVAILABLE_TABLE_DATE_COL="Date";
    public static final String AVAILABLE_TABLE_WORKER_ID_COL="Worker ID";

    public static final String HISTORY_TABLE="History";
    public static final String HISTORY_TABLE_ID="HISTORY_ID";
    public static final String HISTORY_TABLE_WORKER_ID_COL="Worker ID";
    public static final String HISTORY_TABLE_DATE_COL="Date";
    public static final String HISTORY_TABLE_CHARGES_COL="Charges";
    public static final String HISTORY_TABLE_MY_RATING_COL="My Rating";

    public static final String USER_TABLE="User";
    public static final String USER_TABLE_ID="USER_ID";
    public static final String USER_TABLE_USERNAME_COL="User Username";
    public static final String USER_TABLE_PASSWORD_COL="Password";
    public static final String USER_TABLE_PHONE_NO_COL="Phone Number";
    public static final String USER_TABLE_NAME_COL="Name";
    public static final String USER_TABLE_ADDRESS_COL="Address";
    public static final String USER_TABLE_HISTORY_ID_COL="History ID";
}
