package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ztt.city.R;
import com.example.ztt.city.model.User;
import com.example.ztt.city.utils.analysis.LoginAnalysis;
import com.example.ztt.city.utils.tool.SharedPreferencesTool;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class loginActivity extends Activity implements View.OnClickListener {

    public static String TAG = "LoginActivity";

    private EditText mEditTextUserId, mEditTextPassword;
    private Button mButton;
    private String userId, password, request;
    public static ProgressDialog sProgressDialog;
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    /**
     * 进行初始化
     */

    private void initView() {
        mEditTextUserId = (EditText) findViewById(R.id.userId);
        mEditTextPassword = (EditText) findViewById(R.id.password);
        mButton = (Button) findViewById(R.id.log_button);
        mButton.setOnClickListener(this);
    }

    /**
     * 点击监听事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        userId = mEditTextUserId.getText().toString();
        password = mEditTextPassword.getText().toString();

        sProgressDialog = ProgressDialog.show(loginActivity.this, null, "登陆中......");
        mQueue = Volley.newRequestQueue(this);
        ConnectTask task=new ConnectTask();
        task.execute();
    }

    /**
     * 连接接口的异步
     * 使用volley
     *
     */

    private class ConnectTask extends AsyncTask<Void, Void, Void> {

        boolean judge = false;

        @Override
        protected Void doInBackground(Void... params) {
            StringRequest postRequest = new StringRequest(Request.Method.POST, "http://120.27.53.146:5000/api/login",
                    new Response.Listener<String>() {
                        public void onResponse(String response) {
                            request = response;
                            LoginAnalysis loginAnalysis = new LoginAnalysis();
                            try {
                                judge = loginAnalysis.getStatu(response);

                                if (judge) {
                                    loginSuccess();
                                } else {

                                    //ProgressDialog隐藏
                                    sProgressDialog.dismiss();
                                    Toast.makeText(loginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
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
                    params.put("username", userId);
                    params.put("password", password);
                    return params;
                }
            };
            mQueue.add(postRequest);
            return null;
        }

    }

    /**
     * 登录成功
     * @throws JSONException
     */
    private void loginSuccess() throws JSONException {
        Toast.makeText(this,"登陆成功" , Toast.LENGTH_SHORT).show();
        saveLogin();
        sProgressDialog.dismiss();
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     *保存用户登录信息
     * @throws JSONException
     */
    private void saveLogin() throws JSONException {
        LoginAnalysis la = new LoginAnalysis();
        User user = new User();
        user = la.getUser(request,userId,password);

        Log.d("ID",userId);


        SharedPreferencesTool pt = new SharedPreferencesTool(this);
        pt.setSharedPreferenceTool(user);
        this.finish();
    }

}


