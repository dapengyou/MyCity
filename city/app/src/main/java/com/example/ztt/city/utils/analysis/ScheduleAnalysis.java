package com.example.ztt.city.utils.analysis;


import com.example.ztt.city.model.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

public class ScheduleAnalysis {
    /**
     * 获得status并判断返回
     *
     * @param request
     * @return
     * @throws JSONException
     */
    public boolean getStatu(String request) throws JSONException {
        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象。
        JSONObject schedule = (JSONObject) mJSONTokener.nextValue();
        //取出status
        String status = schedule.getString("status");
        return (status.equals("ok")) ? true : false;

    }
    /**
     * 解析grade
     *
     * @param request
     * @return
     * @throws JSONException
     */

    public static Vector<Schedule> AnalysisSchedule(String request) {

        Vector<Schedule> vector = new Vector<>();

        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象。
        JSONObject scores = null;
        try {
            scores = (JSONObject) mJSONTokener.nextValue();
            //得到json格式的grade
            JSONArray grade = scores.getJSONArray("grade");
            //遍历json格式的grade
            for (int i = 0; i < grade.length(); i++) {

                // Log.d("vector", String.valueOf(vector));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vector;
    }
}
