package com.example.ztt.city.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ztt on 16/6/7.
 */
public class MessDateHelp extends SQLiteOpenHelper {
    public static final String CREATE_MESS = "create table mess ("
            + "id text , "
            + "loaction text ,"
            + "floor text ,"
            + "name text ,"
            + "telephone text"
            + ")";
    //建表信息
    private Context mContext;

    public MessDateHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
