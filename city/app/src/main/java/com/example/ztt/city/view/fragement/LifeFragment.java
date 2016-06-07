package com.example.ztt.city.view.fragement;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ztt.city.R;
import com.example.ztt.city.view.activity.EnglishActivity;
import com.example.ztt.city.view.activity.FoodActivity;
import com.example.ztt.city.view.activity.ScoreActivity;
import com.example.ztt.city.view.activity.WebActivity;

/**
 * 食堂Fragment
 */
public class LifeFragment extends Fragment implements View.OnClickListener{
    //获得context值
    private Context context;
    View view;

    private TextView mMessFindTextView; //食堂档口
    private TextView mGradeFindTextView; //成绩
    private TextView mFourFindTextView; //四六级
    private  TextView mExpressageFindTextView; //快递

    private Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_mess, container, false);
        if (context != null) {
            //初始化
            initialize();
        }
        return view;
    }

    private void initialize() {
        mMessFindTextView = (TextView) view.findViewById(R.id.mess_find);
        mGradeFindTextView = (TextView) view.findViewById(R.id.grade_find);
        mFourFindTextView = (TextView) view.findViewById(R.id.four_find);
        mExpressageFindTextView = (TextView) view.findViewById(R.id.expressage_find);

        mMessFindTextView.setOnClickListener(this);
        mGradeFindTextView.setOnClickListener(this);
        mFourFindTextView.setOnClickListener(this);
        mExpressageFindTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mess_find:
                startActivity(new Intent(activity, FoodActivity.class));
                break;
            case R.id.grade_find:
                startActivity(new Intent(activity, ScoreActivity.class));
                break;
            case R.id.four_find:
                startActivity(new Intent(activity, EnglishActivity.class));
                break;
            case R.id.expressage_find:
                startActivity(new Intent(activity,WebActivity.class));
                break;
        }
    }
}
