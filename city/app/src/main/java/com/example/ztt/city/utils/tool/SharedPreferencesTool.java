package com.example.ztt.city.utils.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ztt.city.model.User;


public class SharedPreferencesTool {
    //读取信息
    private static SharedPreferences READ = null;
    //保存信息
    private static SharedPreferences.Editor SAVE = null;

    public SharedPreferencesTool(Context context) {
        //context.MODE_PRIVATE:该文件是私有数据，只能被应用本身访问
        READ = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SAVE = context.getSharedPreferences("login", Context.MODE_PRIVATE).edit();
    }

    /**
     * 保存用户信息
     * @param user
     */

    public static void setSharedPreferenceTool(User user) {
        SAVE.putString("userId", user.getUserId());
        SAVE.putString("password", user.getPassword());
        SAVE.putString("username", user.getName());
        SAVE.putBoolean("loginStatu", true);
        SAVE.commit();

    }
    //获得用户Id
    public static String getUserId() {return READ.getString("userId", "");}
    //获得用户密码
    public static String getPassword() {
        return READ.getString("password", "");
    }
    //获得用户名
    public static String getUserName() {
        return READ.getString("username", "");
    }
    //获得登录状态

    public static boolean getloginstatu() {
        return READ.getBoolean("loginStatu", false);
    }



}
