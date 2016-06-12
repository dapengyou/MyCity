package com.example.ztt.city.utils.analysis;

import android.content.Context;
import android.util.Log;

import com.example.ztt.city.model.Menus;
import com.example.ztt.city.utils.db.MenusDateControl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 * 食堂档口解析
 */
public class MenusAnalysis {

    /**
     * @param request
     * @return
     * @throws JSONException
     */

    public static Vector<Menus> AnalysisMenus(String request, String id, Context context) {
        Vector<Menus> vector = new Vector<>();

        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象,data。
        JSONObject data = null;
        try {
            data = (JSONObject) mJSONTokener.nextValue();
            //得到json格式的grade
            JSONArray dataJSONArray = data.getJSONArray("data");

            for (int i = 0; i < dataJSONArray.length(); i++) {
                Menus menus = new Menus();
                JSONObject temp = dataJSONArray.getJSONObject(i);
                menus.setId(id);
                menus.setName(temp.getString("name"));
                menus.setPrice(temp.getString("price"));

                //存入数据库
                MenusDateControl.addSQL(context, menus.getId(), menus.getName(), menus.getPrice());
                vector.add(menus);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return vector;
    }

}


