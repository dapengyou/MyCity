package com.example.ztt.city.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ztt.city.model.Mess;

import java.util.Vector;

/**
 * Created by ztt on 16/6/7.
 * 食堂档口的控制类
 */
public class MessDateControl {
    public static MessDateHelp messDBHelp;
    public static SQLiteDatabase db;

    //创建数据库
    public static void CreateSQL(Context context) {

        messDBHelp = new MessDateHelp(context, "citybox.db", null, 2);
        db = messDBHelp.getWritableDatabase();
    }

    //增加
    public static void addSQL(Context context, String id, String location,
                              String floor, String name, String telephone) {
        CreateSQL(context);
        //构造ContentValues实例
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("location", location);
        values.put("floor", floor);
        values.put("name", name);
        values.put("telephone", telephone);
        db.insert("mess", null, values);
        values.clear();

    }

    //删除
    public static void delete(Context context) {
        CreateSQL(context);
        String sql = "DELETE FROM mess";
        db.execSQL(sql);
    }
    //查询
    public static Vector<Mess> QueryMess(Context context){
        CreateSQL(context);
        Vector<Mess> vector = new Vector<Mess>();

        Cursor cursor = db.query("mess", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String floor = cursor.getString(cursor.getColumnIndex("floor"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String telephone = cursor.getString(cursor.getColumnIndex("telephone"));

                Mess mess = new Mess();
                mess.setId(id);
                mess.setLocation(location);
                mess.setFloor(floor);
                mess.setName(name);
                mess.setTelephone(telephone);

                vector.add(mess);
//                Log.d("ver",vector.toString());
            }while(cursor.moveToNext());
        }
        cursor.close();
        return vector;
    }

}
