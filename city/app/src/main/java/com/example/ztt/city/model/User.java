package com.example.ztt.city.model;

import com.example.ztt.city.utils.tool.SharedPreferencesTool;

/**
 * Created by ztt on 15/11/15.
 * 用户
 */
public class User {
    private String name;

    private String password;
    private String UserId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User getDefault() {
        User user = new User();
        user.setUserId(SharedPreferencesTool.getUserId());
        user.setPassword(SharedPreferencesTool.getPassword());
        user.setName(SharedPreferencesTool.getUserName());
        return user;
    }
}
