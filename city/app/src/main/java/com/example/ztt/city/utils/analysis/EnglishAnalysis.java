package com.example.ztt.city.utils.analysis;

import android.util.Log;

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
    public boolean getStatu(String request) {
        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象。
        JSONObject english = null;
        String status = null;
        try {
            english = (JSONObject) mJSONTokener.nextValue();
            //取出status
            status = english.getString("status");

        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        //{"status":200,"result":
        // {"name":"姓名","school":"学校","type":"等级","num":"准考证号","time":"时间年月"},
        // "score":{"totleScore":"总分","tlScore":"听力","ydScore":"阅读",
        // "xzpyScore":"写作与翻译"}}
        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象。
        JSONObject scores = null;
        try {
            scores = (JSONObject) mJSONTokener.nextValue();
            String score = scores.getString("score");
            Log.d("score", score);
            //获得score里对象
            JSONObject grade = null;
            JSONTokener mJSONTokeners = new JSONTokener(score);
            grade = (JSONObject) mJSONTokeners.nextValue();

            String totalScore = grade.getString("totleScore");
            Log.d("total", totalScore);
            String listernScore = grade.getString("tlScore");
            String readScore = grade.getString("ydScore");
            String writeScore = grade.getString("xzpyScore");

            FourScore fscore = new FourScore();
            fscore.setTotaleScore(totalScore);
            fscore.setListenScore(listernScore);
            fscore.setReadScore(readScore);
            fscore.setWriteScore(writeScore);
            vector.add(fscore);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vector;
    }


}


