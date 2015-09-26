package com.shikhar.helpme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell pc on 25-04-2015.
 */
public class HelpSQLiteOpenHelper extends SQLiteOpenHelper {
    public HelpSQLiteOpenHelper(Context context) {
        super(context, HelpContract.WORKER_TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Worker (WORKER_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+HelpContract.WORKER_TABLE_NAME_COL+" TEXT,"
                +HelpContract.WORKER_TABLE_AGE_COL+" INTEGER,"+
                HelpContract.WORKER_TABLE_USERNAME_COL+" TEXT," +
                HelpContract.WORKER_TABLE_CHARGES_HOUR_COL+" INTEGER," +
                HelpContract.WORKER_TABLE_PASSWORD_COL+" TEXT," +
                HelpContract.WORKER_TABLE_PROFESSION_COL+" TEXT," +
                HelpContract.WORKER_TABLE_GENDER_COL+" TEXT," +
                HelpContract.WORKER_TABLE_ADDRESS_COL+" TEXT," +
                HelpContract.WORKER_TABLE_PHONE_NO_COL+" TEXT," +
                HelpContract.WORKER_TABLE_PICTURE_COL+" BLOB," +
                HelpContract.WORKER_TABLE_NO_OF_RATING_COL+" INTEGER," +
                HelpContract.WORKER_TABLE_STARS_COL+" INTEGER," +
                HelpContract.WORKER_TABLE_PROFESSION_COL+" TEXT," +

                ");");

        db.execSQL("CREATE TABLE Available(AVAILABLE_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+HelpContract.AVAILABLE_TABLE_DATE_COL+" TEXT," +
                HelpContract.AVAILABLE_TABLE_WORKER_ID_COL+" INTEGER,"+
                " FOREIGN KEY ("+HelpContract.AVAILABLE_TABLE_WORKER_ID_COL+") REFERENCES "+HelpContract.WORKER_TABLE+" ("+HelpContract.WORKER_TABLE_ID+")" +
                ");");

        db.execSQL("CREATE TABLE History(HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+HelpContract.HISTORY_TABLE_DATE_COL+" TEXT," +
                HelpContract.HISTORY_TABLE_WORKER_ID_COL+" INTEGER,"+
                HelpContract.HISTORY_TABLE_CHARGES_COL+" INTEGER,"+
                HelpContract.HISTORY_TABLE_MY_RATING_COL+" INTEGER,"+
                " FOREIGN KEY ("+HelpContract.HISTORY_TABLE_WORKER_ID_COL+") REFERENCES "+HelpContract.WORKER_TABLE+" ("+HelpContract.WORKER_TABLE_ID+")" +

                ")");

        db.execSQL("CREATE TABLE User (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+HelpContract.USER_TABLE_NAME_COL+" TEXT," +
                HelpContract.USER_TABLE_ADDRESS_COL+" TEXT,"+
                HelpContract.USER_TABLE_USERNAME_COL+" TEXT," +
                HelpContract.USER_TABLE_PASSWORD_COL+" TEXT," +
                HelpContract.USER_TABLE_PHONE_NO_COL+" TEXT,"+
                HelpContract.USER_TABLE_HISTORY_ID_COL+" INTEGER,"+
                " FOREIGN KEY ("+HelpContract.USER_TABLE_HISTORY_ID_COL+") REFERENCES "+HelpContract.HISTORY_TABLE+" ("+HelpContract.HISTORY_TABLE_ID+")" +

                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
