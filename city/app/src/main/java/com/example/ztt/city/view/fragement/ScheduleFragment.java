package com.example.ztt.city.view.fragement;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.ztt.city.model.Mess;
import com.example.ztt.city.model.User;
import com.example.ztt.city.until.MessNet;
import com.example.ztt.city.utils.analysis.MessAnalysis;
import com.example.ztt.city.utils.analysis.ScheduleAnalysis;
import com.example.ztt.city.utils.analysis.ScoreAnalysis;
import com.example.ztt.city.utils.db.MessDateControl;
import com.example.ztt.city.utils.db.ScheduleDateControl;
import com.example.ztt.city.utils.db.SteptDateControl;
import com.example.ztt.city.utils.tool.ScoreTool;
import com.example.ztt.city.utils.tool.SharedPreferencesTool;
import com.example.ztt.city.view.activity.DangKouActivity;
import com.example.ztt.city.view.activity.FoodActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 课表Fragment
 */
public class ScheduleFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
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
    public String messSelect = null;
    //标记食堂选项
    public int messItem = 0;
    private Vector<Mess> messVector = new Vector<Mess>();
    private List<Map<String, Object>> mList;
    private SimpleAdapter mSimpleAdapter;
    View view;
    boolean judge = false;

    String result = null;
    //获得context值
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.food_activity, container, false);

        if (context != null) {
            //初始化
            initialize();
            initSpinner();
            initList(messSelect);
        }

        return view;
    }



    private void initialize() {
        mSpinner = (Spinner) view.findViewById(R.id.title);
        updateTextView = (TextView) view.findViewById(R.id.updatefood);
        updateTextView.setOnClickListener(this);
        mListView = (ListView) view.findViewById(R.id.food_listview);
        mListView.setOnItemClickListener(this);
    }

    private void initSpinner() {
        //设置Spinner样式，使用的系统默认样式
        mArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, mess);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                messSelect = mess[position];
                messItem = position;
                initList(messSelect);

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

                MessAnalysis.AnalysisMess(result, context);

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

        messVector = SteptDateControl.QueryMess(context, messSelect);
        initList(messSelect);
    }


    private void initList(String step) {
        //从本地取
        messVector = SteptDateControl.QueryMess(context, step);
        //本地无数据从网络中取
        if (messVector.size() <= 0) {
            if (judge) {
                updateFood();
                judge = false;
            }
        }

        mList = getData();

        mSimpleAdapter = new SimpleAdapter(context, mList, R.layout.item_food,
                new String[]{"title", "phone"},
                new int[]{R.id.title_food, R.id.phone_food});
        mListView.setAdapter(mSimpleAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        Log.d("ddd",messVector.size()+"");
        for (int i = 0; i < messVector.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            Mess mess = messVector.get(i);
            map.put("title", mess.getName());
            map.put("phone", mess.getTelephone());
            list.add(map);
        }

        return list;
    }

    private void updateFood() {
        sProgressDialog = ProgressDialog.show(getActivity(), null, "查询中......");
//        MenusDateControl.delete(this);
        SteptDateControl.delete(context);
        MessDateControl.delete(context);
        ConnectTask task = new ConnectTask();
        task.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String dangkouid = String.valueOf(position);
        Intent next = new Intent(getActivity(), DangKouActivity.class);
        next.putExtra("dangkouid", "" + dangkouid);
        startActivity(next);
    }
}

