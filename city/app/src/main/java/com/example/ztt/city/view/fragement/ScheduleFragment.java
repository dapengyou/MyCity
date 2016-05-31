package com.example.ztt.city.view.fragement;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.ztt.city.model.User;
import com.example.ztt.city.utils.analysis.ScheduleAnalysis;
import com.example.ztt.city.utils.analysis.ScoreAnalysis;
import com.example.ztt.city.utils.db.ScheduleDateControl;
import com.example.ztt.city.utils.tool.ScoreTool;
import com.example.ztt.city.utils.tool.SharedPreferencesTool;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * 课表Fragment
 */
public class ScheduleFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private View view;
    private TextView mTextView;
    private Spinner mSpinner;
    private static ProgressDialog sProgressDialog;

    //用于volley队列
    private RequestQueue mQueue;

    //适配器
    private ArrayAdapter<String> mArrayAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_schedule,
                container, false);
        mTextView = (TextView) view.findViewById(R.id.updateSchedule);
        mSpinner = (Spinner) view.findViewById(R.id.schedule_spinner);

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
//        //选择第几周
//        chooseWeek();
//
//        mArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, time);
//        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        mSpinner.setAdapter(mArrayAdapter);
//        mSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                week = time[position];
//                item = position;
//                //initSchedule();
//
//            }
//        });
//        //设置spinner可见
//        mSpinner.setVisibility(View.VISIBLE);
 }


    /**
     * 连接接口的异步
     * 使用volley
     */

//    private class ConnectTask extends AsyncTask<Void, Void, Void> {
//
//        User user = User.getDefault();
//        boolean judge = false;
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            StringRequest postRequest = new StringRequest(Request.Method.POST, "http://120.27.53.146:5000/api/schedule",
//                    new Response.Listener<String>() {
//                        public void onResponse(String response) {
//                            ScheduleAnalysis scheduleAnalysis = new ScheduleAnalysis();
//                            try {
//                                //得到jude为true
//                                judge = scheduleAnalysis.getStatu(response);
//
//                                if (judge) {
//                                    ScheduleDateControl.deleteSchedule(context);
//
//
//
//                                    sProgressDialog.dismiss();
//                                } else {
//                                    sProgressDialog.dismiss();
//                                    Toast.makeText(getActivity(), "校网或网络问题,请重试,如果多次尝试都未有结果,请检查账号和密码正确.", Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            }) {
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("username", user.getUserId());
//                    params.put("password", user.getPassword());
//                    params.put("action", "update");
//                    return params;
//                }
//            };
//            mQueue.add(postRequest);
//            return null;
//        }
//    }
//
//    /**
//     * 显示课表
//     */
//    private void initSchedule() {
//    }
//
//    /**
//     * 选择spinner中的周数
//     */
//    private void chooseWeek() {
//
//    }
//
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.updateSchedule:
//                sProgressDialog = ProgressDialog.show(getActivity(), null, "获取课表中......");
//                ConnectTask task = new ConnectTask();
//                task.execute();
//                break;
//        }
    }
}
