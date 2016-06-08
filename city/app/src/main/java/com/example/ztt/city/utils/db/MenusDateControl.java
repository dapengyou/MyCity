package com.example.ztt.city.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ztt.city.model.Menus;
import com.example.ztt.city.model.Mess;

import java.util.Vector;

/**
 * Created by ztt on 16/6/7.
 * 菜单的控制类
 */
public class MenusDateControl {
    public static MenusDateHelp menusDBHelp;
    public static SQLiteDatabase db;

    //创建数据库
    public static void CreateSQL(Context context) {

        menusDBHelp = new MenusDateHelp(context, "citybox.db", null, 1);
        db = menusDBHelp.getWritableDatabase();
    }

    //增加
    public static void addSQL(Context context, String id, String name,String price) {
        CreateSQL(context);
        //构造ContentValues实例
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("price", price);
        db.insert("menus", null, values);

    }

    //删除
    public static void delete(Context context) {
        CreateSQL(context);
        String sql = "DELETE FROM menus";
        db.execSQL(sql);
    }
    //查询
    public static Vector<Menus> QueryMenus(Context context){
        CreateSQL(context);
        Vector<Menus> vector = new Vector<>();
        Menus menus = new Menus();
        Cursor cursor = db.query("menus", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while(cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String price = cursor.getString(cursor.getColumnIndex("price"));

                menus.setId(id);
                menus.setName(name);
                menus.setPrice(price);
                vector.add(menus);
            }
        }
        cursor.close();
        return vector;
    }

}
