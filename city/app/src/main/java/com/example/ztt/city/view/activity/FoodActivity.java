package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ztt.city.R;
import com.example.ztt.city.model.Mess;
import com.example.ztt.city.until.MessNet;
import com.example.ztt.city.utils.analysis.MessAnalysis;
import com.example.ztt.city.utils.db.MessDateControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ztt on 16/6/7.
 * 食堂Activity
 */
public class FoodActivity extends Activity implements View.OnClickListener ,AdapterView.OnItemClickListener{
    private Spinner mSpinner;
    private TextView backTextView;
    private TextView updateTextView;
    private ListView mListView;
    public static ProgressDialog sProgressDialog;

    //适配器
    private ArrayAdapter<String> mArrayAdapter;
    //数据源
    private static String[] mess = {"二食堂楼上", "二食堂楼下", "三食堂楼上", "三食堂楼下"};
    //得到所选食堂
    public String messSelect;
    //标记食堂选项
    public int messItem = 0;
    private Vector<Mess> messVector = new Vector<Mess>();
    private List<Map<String, Object>> mList;
    private SimpleAdapter mSimpleAdapter;

    boolean judge = false;

    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);
        initialize();
        initSpinner();
        initList();
    }

    private void initialize() {
        mSpinner = (Spinner) findViewById(R.id.title);
        backTextView = (TextView) findViewById(R.id.back);
        updateTextView = (TextView) findViewById(R.id.updatefood);
        backTextView.setOnClickListener(this);
        updateTextView.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.food_listview);
    }

    private void initSpinner() {
        //设置Spinner样式，使用的系统默认样式
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mess);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                messSelect = mess[position];
                messItem = position;
//                sProgressDialog = ProgressDialog.show(FoodActivity.this, null, "查询中......");
                initList();
//                ConnectTask task = new ConnectTask();
//                task.execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置spinner可见
        mSpinner.setVisibility(View.VISIBLE);
        messSelect = mess[0];
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.updatefood:
                updateFood();
                break;
        }
    }


    private class ConnectTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                result = MessNet.Mess();

                MessAnalysis.AnalysisMess(result, getApplicationContext());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateUI();

        }
    }

    private void updateUI() {
        sProgressDialog.dismiss();

        messVector = MessDateControl.QueryMess(this);
        initList();
    }


    private void initList() {
        //从本地取
        messVector = MessDateControl.QueryMess(this);
        //本地无数据从网络中取
        if (messVector.size() <= 0) {
            if (judge) {
                updateFood();
                judge = false;
            }
        }

        mList = getData();

        String[] title = new String[]{"title","body"};
        mSimpleAdapter = new SimpleAdapter(this, mList, R.layout.item_food,
                new String[]{title[0]},
                new int[]{R.id.title_food});
        mListView.setAdapter(mSimpleAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < messVector.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            Mess mess = messVector.get(i);
            map.put("title", mess.getName());
            list.add(map);
        }

        return list;
    }



    private void updateFood() {
        sProgressDialog = ProgressDialog.show(FoodActivity.this, null, "查询中......");
        MessDateControl.delete(this);
//        MenusDateControl.delete(this);
        ConnectTask task = new ConnectTask();
        task.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String dangkouid = String.valueOf(position);
        Intent next = new Intent();
        next.putExtra("dangkouid" , ""+dangkouid);
        next.setClass(this,DangKouActivity.class);
        startActivity(next);
    }
}
