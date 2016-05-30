package com.example.ztt.city.view.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ztt.city.R;

/**
 * 设置Fragment
 */
public class fitFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsLayout = inflater.inflate(R.layout.other_top_fit, container, false);
        return newsLayout;
    }

}
