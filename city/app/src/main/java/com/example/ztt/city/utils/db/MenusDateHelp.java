package com.example.ztt.city.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ztt on 16/6/7.
 * 菜单操作类
 */
public class MenusDateHelp extends SQLiteOpenHelper {
    public static final String CREATE_MENUS = "create table dangkou ("
            + "dangkouid text , "
            + "name text ,"
            + "price text"
            + ")";


    public MenusDateHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("ds","fg");
        db.execSQL("drop table if exists dangkou");
        db.execSQL(CREATE_MENUS);
        Log.d("ds","fa");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists dangkou");
        onCreate(db);
    }
}
