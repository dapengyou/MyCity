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
 * 楼层的控制类
 */
public class SteptDateControl {
    public static MessDateHelp messDBHelp;
    public static SQLiteDatabase db;

    //创建数据库
    public static void CreateSQL(Context context) {

        messDBHelp = new MessDateHelp(context, "citybox.db", null, 2);
        db = messDBHelp.getWritableDatabase();
    }
    //删除
    public static void delete(Context context) {
        CreateSQL(context);
        String sql = "DELETE FROM mess";
        db.execSQL(sql);
    }
    //查询
    public static Vector<Mess> QueryMess(Context context,String step){
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

                String stepString = location.trim() +floor.trim();
//                Log.d("stepString",stepString);

                if(step.equals(stepString)) {
                    Mess mess = new Mess();
                    mess.setId(id);
                    mess.setLocation(location);
                    mess.setFloor(floor);
                    mess.setName(name);
                    mess.setTelephone(telephone);
                    vector.add(mess);
                }

//                Log.d("ver",vector.size()+"");
            }while(cursor.moveToNext());
        }
        cursor.close();
        return vector;
    }

}
