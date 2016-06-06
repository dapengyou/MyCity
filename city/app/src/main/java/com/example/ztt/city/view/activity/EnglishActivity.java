package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ztt.city.R;
import com.example.ztt.city.model.Book;
import com.example.ztt.city.model.FourScore;
import com.example.ztt.city.utils.analysis.EnglishAnalysis;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ztt on 16/6/5.
 */
public class EnglishActivity extends Activity implements View.OnClickListener {
    private EditText examEditText;
    private EditText nameEditText;
    private Button mButton;
    public static ProgressDialog sProgressDialog;
    //用于volley队列
    public RequestQueue mQueue;
    private ListView mListView;
    private Vector<FourScore> mFourScore = new Vector<FourScore>();
    private List<Map<String, Object>> mList;
    private SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_six);
        initialize();
    }

    private void initialize() {
        examEditText = (EditText) findViewById(R.id.exam);
        nameEditText = (EditText) findViewById(R.id.name);
        mButton = (Button) findViewById(R.id.English_button);

        mButton.setOnClickListener(this);
        mQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.English_button:
                sProgressDialog = ProgressDialog.show(EnglishActivity.this, null, "获取成绩中......");
                ConnectTask task = new ConnectTask();
                task.execute();
                break;
        }
    }

    private class ConnectTask extends AsyncTask<Void, Void, Void> {
        boolean judge = false;

        @Override
        protected Void doInBackground(Void... params) {
            StringRequest postRequest = new StringRequest(Request.Method.POST, "http://localhost/index.php/Home/Campus/appQueryCet",
                    new Response.Listener<String>() {
                        public void onResponse(String response) {
                            EnglishAnalysis analysis = new EnglishAnalysis();
                            try {
                                //得到jude为true
                                judge = analysis.getStatu(response);

                                if (judge) {
                                    sProgressDialog.dismiss();
                                    mFourScore = EnglishAnalysis.AnalysisEnglish(response);
                                    initList();


                                } else if (examEditText.equals("") || nameEditText.equals("")) {
                                    sProgressDialog.dismiss();
                                    Toast.makeText(EnglishActivity.this,
                                            "姓名或准考证号出错（或为空）.", Toast.LENGTH_SHORT).show();
                                } else {
                                    sProgressDialog.dismiss();
                                    Toast.makeText(EnglishActivity.this,
                                            "接口问题.", Toast.LENGTH_SHORT).show();
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
                    params.put("exam", examEditText.getText().toString());
                    params.put("name", nameEditText.getText().toString());
                    return params;
                }
            };
            mQueue.add(postRequest);

            return null;
        }

    }


    private void initList() {
        mList = getData();
        mSimpleAdapter = new SimpleAdapter(this, mList, R.layout.item_book,
                new String[]{"totalgrade", "listen", "read","writer"},
                new int[]{R.id.totalgrade, R.id.listen,
                        R.id.read, R.id.writer});
        mListView.setAdapter(mSimpleAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mFourScore.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            FourScore score = mFourScore.get(i);
            map.put("totalgrade", score.getTotaleScore());
            map.put("listen", score.getListenScore());
            map.put("read", score.getReadScore());
            map.put("writer", score.getWriteScore());
            list.add(map);
        }
        return list;
    }


}

