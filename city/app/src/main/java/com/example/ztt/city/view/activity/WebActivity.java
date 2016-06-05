package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by ztt on 16/6/5.
 * 快递100 的接口
 */
public class WebActivity extends Activity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);

        try {
            String url = "http://m.kuaidi100.com/index_all.html?type=&postid=";
            //设置打开的页面地址
            webview.loadUrl(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setContentView(webview);
    }
}
