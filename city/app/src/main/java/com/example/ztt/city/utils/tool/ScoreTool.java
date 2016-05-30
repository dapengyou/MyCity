package com.example.ztt.city.utils.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class ScoreTool {

    private static SharedPreferences sRead = null;//读取信息
    private static SharedPreferences.Editor sSave = null; //保存信息

    public ScoreTool(Context context){
        sRead = context.getSharedPreferences("score" , context.MODE_PRIVATE);
        sSave = context.getSharedPreferences("score" , context.MODE_PRIVATE).edit();
    }

    /**
     * 存入学期,trem是整个成绩
     */

    public static void  setSharedPreferenceTool(String id ,String position, String trem){
        String t = "trem" + id + position;
        sSave.putString(t , trem);
        sSave.commit();
    }

    /**
     * 获得学期
     */
    public static String getTrem(String id ,String position){
        String t = "trem" + id + position;
        return sRead.getString(t , "null");
    }



}
