package com.example.ztt.city.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ztt on 16/6/7.
 * 食堂档口的操作类
 */
public class MessDateHelp extends SQLiteOpenHelper {
    public static final String CREATE_MESS = "create table mess ("
            + "id text , "
            + "location text ,"
            + "floor text ,"
            + "name text ,"
            + "telephone text"
            + ")";

    public MessDateHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists mess");
        db.execSQL(CREATE_MESS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbs, int oldVersion, int newVersion) {
        dbs.execSQL("drop table if exists res");
        onCreate(dbs);
    }
}
