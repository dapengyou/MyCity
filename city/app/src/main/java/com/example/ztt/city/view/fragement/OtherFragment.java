package com.example.ztt.city.view.fragement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.ztt.city.R;
import com.example.ztt.city.utils.db.MessDateControl;
import com.example.ztt.city.utils.tool.SharedPreferencesTool;
import com.example.ztt.city.view.activity.loginActivity;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他界面
 */
public class OtherFragment extends Fragment implements View.OnClickListener {
    View view;
    private TextView quitTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_other,
                container, false);

        quitTextView = (TextView) view.findViewById(R.id.setting_esc);
        quitTextView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_esc:
                esc();
                break;
        }
    }

    private void esc() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否确认退出?"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SharedPreferencesTool sharedPreferencesTool = new SharedPreferencesTool(getActivity());
                SharedPreferencesTool.clear();
                MessDateControl.delete(getActivity());


                getActivity().finish();
                startActivity(new Intent(getActivity(), loginActivity.class));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }
}
