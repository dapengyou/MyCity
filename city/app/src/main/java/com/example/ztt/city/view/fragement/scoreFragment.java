package com.example.ztt.city.view.fragement;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ztt.city.R;
import com.example.ztt.city.model.Score;
import com.example.ztt.city.model.User;
import com.example.ztt.city.utils.analysis.ScoreAnalysis;
import com.example.ztt.city.utils.tool.ScoreTool;
import com.example.ztt.city.utils.tool.SharedPreferencesTool;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 成绩Fragment
 */
public class scoreFragment extends Fragment implements View.OnClickListener {

    private TextView mTextView;
    private Spinner mSpinner;
    private View view;
    private ListView mListView;
    public static ProgressDialog sProgressDialog;

    //适配器
    private ArrayAdapter<String> mArrayAdapter;
    //数据源
    private static String[] time;

    //时间段代表termstring
    private String trem;
    //获得context值
    Context context;
    //item数量
    private int item = 0;
    //用于volley
    private String request;

    //用于volley队列
    public RequestQueue mQueue;

    private Vector<Score> mScoreGrade = new Vector<Score>();
    private List<Map<String, Object>> mList;
    private SimpleAdapter mSimpleAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.other_top_scroe, container, false);

        mTextView = (TextView) view.findViewById(R.id.textView_find);
        mSpinner = (Spinner) view.findViewById(R.id.score_spinner);


        mTextView.setOnClickListener(this);

        mQueue = Volley.newRequestQueue(context.getApplicationContext());
        if (context != null) {
            initSpinner();
        }

        return view;
    }

    /**
     * 初始化spinner
     */
    private void initSpinner() {
        //选择学期
        chooseTime();

        //设置Spinner样式，使用的系统默认样式
        mArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, time);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //得到学期时间段
                trem = time[position];
                item = position;
                initList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置spinner可见
        mSpinner.setVisibility(View.VISIBLE);

        trem = time[0];
    }

    /**
     * 选择学期
     */
    private void chooseTime() {

        SharedPreferencesTool mSharedPreferencesTool = new SharedPreferencesTool(context);
        String userId = mSharedPreferencesTool.getUserId();

        //截取学号的前四位
        String year = userId.substring(0, 4);

        //下一年
        String downyear = year;
        //上一年
        String upyear = year;
        int between;

        //可以对每个时间域单独修改
        Calendar c = Calendar.getInstance();
        //得到现在的年份
        int getData_year = c.get(Calendar.YEAR);
        int getData_mouth = c.get(Calendar.MONTH);

        int years = Integer.valueOf(year);

        if (getData_year == years) {
            between = 1;
        } else {
            //得到一共上了几年学
            between = getData_year - years;
            //一年两个学期
            between = between * 2;
            //获得的月份小于8月则在一学年的上学期，所以减一
            if (getData_mouth < 8) {
                between = between - 1;
            }
        }

        between++;
        time = new String[between];
        //  Log.d("score", "between" + between);

        for (int i = 0; i < between; ) {
            //例如：2013+1=2014
            downyear = String.valueOf((Integer.valueOf(downyear) + 1));
            for (int j = 1; j < 3; j++, i++) {
                time[i] = upyear + "-" + downyear + "学年第" + j + "学期";
            }
            upyear = downyear;
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_find:
                sProgressDialog = ProgressDialog.show(getActivity(), null, "获取成绩中......");
                ConnectTask task = new ConnectTask();
                task.execute();
                break;
        }
    }


    /**
     * 连接接口的异步
     * 使用volley
     */

    private class ConnectTask extends AsyncTask<Void, Void, Void> {

        boolean judge = false;
        User user = User.getDefault();

        @Override
        protected Void doInBackground(Void... params) {
            StringRequest postRequest = new StringRequest(Request.Method.POST, "http://120.27.53.146:5000/api/grade",
                    new Response.Listener<String>() {
                        public void onResponse(String response) {
                            request = response;
                            ScoreAnalysis scoreAnalysis = new ScoreAnalysis();
                            try {
                                //得到jude为true
                                judge = scoreAnalysis.getStatu(request);

                                if (judge) {
                                    //存入SharedPreference中
                                    ScoreTool.setSharedPreferenceTool(SharedPreferencesTool.getUserId(),
                                            "" + item, request);
                                    //得到解析后Grade
                                    mScoreGrade = ScoreAnalysis.AnalysisGrade(request);
                                    initList();
                                    sProgressDialog.dismiss();
                                } else {
                                    sProgressDialog.dismiss();
                                    Toast.makeText(getActivity(), "校网或网络问题,请重试,如果多次尝试都未有结果,请检查账号和密码正确.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                            }
                        }
                    }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", user.getUserId());
                    params.put("password", user.getPassword());
                    params.put("termstring", trem);
                    params.put("action", "update");
                    return params;
                }
            };
            mQueue.add(postRequest);

            return null;
        }


    }

    /**
     * 显示成绩列表
     */

    private void initList() {

        mListView = (ListView) view.findViewById(R.id.score_listview);

        ScoreTool st = new ScoreTool(context);
        String trem = st.getTrem(SharedPreferencesTool.getUserId(), "" + item);
       // Log.d("score", "trem: " + trem);

        if (!trem.equals("null")) {

            mList = getData();
            mSimpleAdapter = new SimpleAdapter(context, mList, R.layout.item_score,
                    new String[]{"scoreName", "scoreaAchievement", "peacetimeAchievement",
                            "terminalAchievement", "credit"},
                    new int[]{R.id.item_score_scoreName, R.id.item_score_scoreaAchievement,
                            R.id.item_score_peacetimeAchievement,
                            R.id.item_score_terminalAchievement, R.id.item_score_credit});
            mListView.setAdapter(mSimpleAdapter);
        } else {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            mList = list;
            mSimpleAdapter = new SimpleAdapter(context, mList, R.layout.item_score,
                    new String[]{"scoreName", "scoreaAchievement", "peacetimeAchievement",
                            "terminalAchievement", "credit"},
                    new int[]{R.id.item_score_scoreName, R.id.item_score_scoreaAchievement,
                            R.id.item_score_peacetimeAchievement,
                            R.id.item_score_terminalAchievement, R.id.item_score_credit});
            mListView.setAdapter(mSimpleAdapter);
        }

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mScoreGrade.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("scoreName", "" + mScoreGrade.get(i).getScoreName());
            map.put("scoreaAchievement", "课程成绩: " + mScoreGrade.get(i).getScoreAchievement());
            map.put("peacetimeAchievement", "平时成绩: " + mScoreGrade.get(i).getPeacetimeAchievement());
            map.put("terminalAchievement", "期末成绩: " + mScoreGrade.get(i).getTerminalAchievement());
            map.put("credit", "学分: " + mScoreGrade.get(i).getCredit());
            list.add(map);
        }
        return list;
    }


}
