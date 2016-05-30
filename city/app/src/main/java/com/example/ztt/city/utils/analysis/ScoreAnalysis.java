package com.example.ztt.city.utils.analysis;

import android.util.Log;

import com.example.ztt.city.model.Score;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 * 成绩解析
 */
public class ScoreAnalysis {
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
        JSONObject score = (JSONObject) mJSONTokener.nextValue();
        //取出status
        String status = score.getString("status");
        return (status.equals("ok")) ? true : false;

    }


    /**
     * 解析grade
     *
     * @param request
     * @return
     * @throws JSONException
     */

    public static Vector<Score> AnalysisGrade(String request) {

        Vector<Score> vector = new Vector<>();
        //request = "{\"grade\":" + request + "}";
        // Log.d("requst" , "requst  " + request );

        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象。
        JSONObject scores = null;
        try {
            scores = (JSONObject) mJSONTokener.nextValue();
            //得到json格式的grade
            JSONArray grade = scores.getJSONArray("grade");
            //遍历json格式的grade
            for (int i = 0; i < grade.length(); i++) {
                Score score = new Score();
                JSONObject temp = grade.getJSONObject(i);
                score.setScoreName(temp.getString("课程名称"));
                score.setCredit(temp.getString("学分"));
                score.setPeacetimeAchievement(temp.getString("平时成绩"));
                score.setScoreAchievement(temp.getString("课程成绩"));
                score.setTerminalAchievement(temp.getString("期末成绩"));
                vector.add(score);
               // Log.d("vector", String.valueOf(vector));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vector;
    }


}


