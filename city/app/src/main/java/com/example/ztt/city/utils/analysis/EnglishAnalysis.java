package com.example.ztt.city.utils.analysis;

import com.example.ztt.city.model.FourScore;
import com.example.ztt.city.model.Score;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 * 四六级解析
 */
public class EnglishAnalysis {
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
        JSONObject english = (JSONObject) mJSONTokener.nextValue();
        //取出status
        String status = english.getString("status");

        return (status.equals("200")) ? true : false;

    }


    /**
     * 解析grade
     *
     * @param request
     * @return
     * @throws JSONException
     */

    public static Vector<FourScore> AnalysisEnglish(String request) {

        Vector<FourScore> vector = new Vector<>();

        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象。
        JSONObject scores = null;
        try {
            scores = (JSONObject) mJSONTokener.nextValue();
            //得到json格式的grade
            JSONArray score = scores.getJSONArray("score");
            //遍历json格式的grade
            for (int i = 0; i < score.length(); i++) {
                //Score score = new Score();
                FourScore fscore = new FourScore();
                JSONObject temp = score.getJSONObject(i);
                fscore.setTotaleScore(temp.getString("totleScore"));
                fscore.setListenScore(temp.getString("tlScore"));
                fscore.setReadScore(temp.getString("ydScore"));
                fscore.setWriteScore(temp.getString("xzpyScore"));
                vector.add(fscore);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vector;
    }


}


