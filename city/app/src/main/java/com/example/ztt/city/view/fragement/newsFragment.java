package com.example.ztt.city.view.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ztt.city.R;

/**
 * 新闻Fragment
 */
public class newsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.other_top_news, container, false);
        return messageLayout;
    }

}
