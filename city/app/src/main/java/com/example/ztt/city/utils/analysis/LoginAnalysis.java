package com.example.ztt.city.utils.analysis;



import com.example.ztt.city.model.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by ztt on 15/11/15.
 * 登录信息解析
 */
public class LoginAnalysis {

    public boolean getStatu(String result) throws JSONException {
        JSONTokener jsonParser = new JSONTokener(result);
        JSONObject person = (JSONObject) jsonParser.nextValue();
        String status = person.getString("status");
        if (status.equals("ok")) {
            return true;
        }else {
            return false;
        }
    }

    public User getUser(String request ,String userId , String password) throws JSONException {
        User user = new User();
        JSONTokener jsonParser = new JSONTokener(request);
        JSONObject person = (JSONObject) jsonParser.nextValue();
        String man = person.getString("man");
        user.setName(man);
        user.setUserId(userId);
        user.setPassword(password);

        return user;
    }
}
