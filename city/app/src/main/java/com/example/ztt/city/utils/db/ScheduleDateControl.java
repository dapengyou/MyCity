package com.example.ztt.city.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 课表数据库控制类
 */
public class ScheduleDateControl {

    private static ScheduleDateHelp scheduleDateHelp;
    private static SQLiteDatabase sqLiteDatabase;

    /**
     * 创建数据库city.db
     * @param context
     */
    public static void createSQL(Context context){
        //版本号为1，数据库为city.db
        scheduleDateHelp = new ScheduleDateHelp(context,"city.db",null,1);
        sqLiteDatabase = scheduleDateHelp.getReadableDatabase();
    }

    /**
     * 查询数据
     * @param context
     */
    public static  void querySchedule(Context context) {

    }

    /**
     * 删除数据
     * @param context
     */
    public static void deleteSchedule(Context context) {
        createSQL(context);
        sqLiteDatabase.execSQL("delete from schedule");
    }

    /**
     * 更新数据
     */
    public static void updateSchedule() {

    }


}
