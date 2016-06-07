package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ztt.city.R;

/**
 * Created by ztt on 16/6/7.
 * 食堂Activity
 */
public class FoodActivity extends Activity implements View.OnClickListener {
    private Spinner mSpinner;
    private TextView backTextView;
    private TextView updateTextView;
    private ListView mListView;
    public static ProgressDialog sProgressDialog;

    //适配器
    private ArrayAdapter<String> mArrayAdapter;
    //数据源
    private static String[] mess = {"二食堂上", "二食堂下", "三食堂下", "三食堂上"};
    //得到所选食堂
    public String messSelect;
    //标记食堂选项
    public int messItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);
        initialize();
        initSpinner();
    }

    private void initialize() {
        mSpinner = (Spinner) findViewById(R.id.title);
        backTextView = (TextView) findViewById(R.id.back);
        updateTextView = (TextView) findViewById(R.id.updatefood);
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
//                sProgressDialog = ProgressDialog.show(FoodActivity.this, null, "查询中......");
                messSelect = mess[position];
                messItem = position;
                initList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置spinner可见
        mSpinner.setVisibility(View.VISIBLE);
        messSelect = mess[0];
    }

    private void initList() {

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

    private void updateFood() {

    }
}
