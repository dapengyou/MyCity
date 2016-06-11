package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ztt.city.R;
import com.example.ztt.city.model.Mess;
import com.example.ztt.city.until.DangkouNet;
import com.example.ztt.city.until.MessNet;
import com.example.ztt.city.utils.analysis.MessAnalysis;
import com.example.ztt.city.utils.db.MessDateControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ztt on 16/6/11.
 */
public class DangKouActivity extends Activity {
    private TextView dangkouName,priceTextView;
    private ListView mListView;
    String result;
    String dangkouNameString;
    int dangkouId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangkou_activity);
        initialize();
    }

    private void initialize() {
        dangkouName = (TextView) findViewById(R.id.dangkou_name);
        priceTextView = (TextView) findViewById(R.id.price);
        mListView = (ListView) findViewById(R.id.dangkou_listview);
        //档口Id的转化
        Intent intent = getIntent();
        dangkouNameString = intent.getStringExtra("dangkouid");
        dangkouId = Integer.valueOf(dangkouNameString);
        dangkouId++;
        dangkouNameString = String.valueOf(dangkouId);

    }
    private class ConnectTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                result = DangkouNet.DangKou(dangkouNameString);

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

}
