package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.LinearGradient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
import com.example.ztt.city.until.BookNet;
import com.example.ztt.city.until.EnglishNet;
import com.example.ztt.city.utils.analysis.EnglishAnalysis;

import org.json.JSONException;

import java.io.IOException;
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
    private TextView backTextView,titleTextView;
    private Button mButton;
    public static ProgressDialog sProgressDialog;
    private ListView mListView;
    private Vector<FourScore> mFourScore = new Vector<FourScore>();
    private List<Map<String, Object>> mList;
    private SimpleAdapter mSimpleAdapter;
    boolean judge = false;
    String result = null;
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
        mListView = (ListView) findViewById(R.id.listview);
        backTextView = (TextView) findViewById(R.id.back);
        backTextView.setOnClickListener(this);
        titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText("四六级");
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.English_button:
                sProgressDialog = ProgressDialog.show(EnglishActivity.this, null, "获取成绩中......");
                ConnectTask task = new ConnectTask();
                task.execute();
                break;
            case R.id.back:
                this.finish();
                break;
        }
    }

    private class ConnectTask extends AsyncTask<Void, Void, Void> {

        String name = nameEditText.getText().toString();
        String exam = examEditText.getText().toString();

        @Override
        protected Void doInBackground(Void... params) {
            try {
                result = EnglishNet.English(exam, name);
                Log.d("result",result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            EnglishAnalysis analysis = new EnglishAnalysis();

            judge = analysis.getStatu(result);
            if (judge) {
                sProgressDialog.dismiss();
                mFourScore = EnglishAnalysis.AnalysisEnglish(result);
                initList();
            } else {
                sProgressDialog.dismiss();
                Toast.makeText(EnglishActivity.this,
                        "接口问题或姓名或准考证号出错.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void initList() {
        mList = getData();
        mSimpleAdapter = new SimpleAdapter(this, mList, R.layout.item_english,
                new String[]{"totalgrade", "listen", "read", "writer"},
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

