package com.example.ztt.city.utils.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 建库建表
 */

public class ScheduleDateHelp extends SQLiteOpenHelper {
    //数据库的文件名
   // private final static String DATABASE_NAME = "city.db";

    public ScheduleDateHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql  = "create table schedule ("
                + "className text , "
                + "classRoom text ,"
                + "weeks text ,"
                + "colors text ,"
                + "day text ," //哪一天
                + "bar text " //哪一节
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists schedule");
        onCreate(db);
    }
}
