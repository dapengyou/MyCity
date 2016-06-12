package com.example.ztt.city.utils.analysis;

import android.content.Context;
import android.util.Log;

import com.example.ztt.city.model.Book;
import com.example.ztt.city.model.FourScore;
import com.example.ztt.city.model.Mess;
import com.example.ztt.city.utils.db.MessDateControl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 * 食堂档口解析
 */
public class MessAnalysis {

    /**
     *
     * @param request
     * @return
     * @throws JSONException
     */

    public static Vector<Mess> AnalysisMess(String request, Context context) {
        Vector<Mess> vector = new Vector<>();

        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象,yy。
        JSONObject yy = null;
        try {
            yy = (JSONObject) mJSONTokener.nextValue();
            //得到json格式的grade
            JSONArray yyJSONArray = yy.getJSONArray("yy");

            JSONTokener dataTwo = new JSONTokener(String.valueOf(yyJSONArray.get(0)));
            //直接读取就是一个JSONObject对象,二食堂,21ge。
            JSONObject dataJSONObjectTwo = (JSONObject) dataTwo.nextValue();
            JSONArray messArrayTwo = dataJSONObjectTwo.getJSONArray("data");

            JSONTokener dataThree = new JSONTokener(String.valueOf(yyJSONArray.get(1)));
            //直接读取就是一个JSONObject对象,三食堂，24个。
            JSONObject dataJSONObjectThree = (JSONObject) dataThree.nextValue();
            JSONArray messArrayThree = dataJSONObjectThree.getJSONArray("data");

            int num = messArrayTwo.length() + messArrayThree.length();

            //对二食堂所有数据进行循环
            for (int i = 0; i < messArrayTwo.length(); i++) {
                Mess mess = new Mess();
                JSONObject temp = messArrayTwo.getJSONObject(i);

                mess.setId(temp.getString("id"));
                mess.setLocation(temp.getString("location"));
                mess.setFloor(temp.getString("floor"));
                mess.setName(temp.getString("name"));
                mess.setTelephone(temp.getString("telephone"));

                //存入数据库
                MessDateControl.addSQL(context, mess.getId(), mess.getLocation(),
                        mess.getFloor(), mess.getName(), mess.getTelephone());
                vector.add(mess);
            }

            //对三食堂所有数据进行循环
            for (int i = messArrayTwo.length(); i < num; i++) {
                Mess mess = new Mess();
                JSONObject temp = messArrayThree.getJSONObject(i-messArrayTwo.length());
                mess.setId(temp.getString("id"));
                mess.setLocation(temp.getString("location"));
                mess.setFloor(temp.getString("floor"));
                mess.setName(temp.getString("name"));
                mess.setTelephone(temp.getString("telephone"));
                //存入数据库
                MessDateControl.addSQL(context, mess.getId(), mess.getLocation(),
                        mess.getFloor(), mess.getName(), mess.getTelephone());
                vector.add(mess);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return vector;
    }

}


