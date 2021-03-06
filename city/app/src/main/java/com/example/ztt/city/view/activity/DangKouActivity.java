package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ztt.city.R;
import com.example.ztt.city.model.Menus;
import com.example.ztt.city.until.DangkouNet;
import com.example.ztt.city.utils.analysis.MenusAnalysis;
import com.example.ztt.city.utils.db.MenusDateControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ztt on 16/6/11.
 */
public class DangKouActivity extends Activity implements View.OnClickListener{
    private TextView dangkouName, priceTextView,titleTextView,backTextView;
    private ListView mListView;
    String result;
    String dangkouNameString;
    int dangkouId;
    private Vector<Menus> menusVector = new Vector<>();
    private List<Map<String, Object>> mList;
    private SimpleAdapter mSimpleAdapter;
    boolean judge = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menus_activity);
        initialize();
        initList();
    }

    private void initialize() {
        dangkouName = (TextView) findViewById(R.id.dangkou_name);
        priceTextView = (TextView) findViewById(R.id.price);
        mListView = (ListView) findViewById(R.id.dangkou_listview);
        backTextView = (TextView) findViewById(R.id.back);
        backTextView.setOnClickListener(this);
        titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText("菜单");
        //档口Id的转化
        Intent intent = getIntent();
        dangkouNameString = intent.getStringExtra("dangkouid");
        dangkouId = Integer.valueOf(dangkouNameString);
        dangkouId++;
        dangkouNameString = String.valueOf(dangkouId);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                this.finish();
                break;
        }
    }

    private class ConnectTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                result = DangkouNet.DangKou(dangkouNameString);

                MenusAnalysis.AnalysisMenus(result, dangkouNameString, getApplicationContext());

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
        menusVector = MenusDateControl.QueryMenus(this,dangkouNameString);
        initList();
    }

    private void updateFood() {
        MenusDateControl.delete(this);
        ConnectTask task = new ConnectTask();
        task.execute();
    }

    private void initList() {
        //从本地取
        menusVector = MenusDateControl.QueryMenus(this, dangkouNameString);
//        Log.d("menusVector",""+menusVector);
        //本地无数据从网络中取
        if (menusVector.size() <= 0) {
            if (judge) {
                updateFood();
                judge = false;
            }
        }

        mList = getData();

        mSimpleAdapter = new SimpleAdapter(this, mList, R.layout.item_food_price,
                new String[]{"name", "price"},
                new int[]{R.id.dangkou_name, R.id.price});
        mListView.setAdapter(mSimpleAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < menusVector.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            Menus menus = menusVector.get(i);
            map.put("name", menus.getName());
            map.put("price", menus.getPrice());
            list.add(map);
        }

        return list;
    }

}
