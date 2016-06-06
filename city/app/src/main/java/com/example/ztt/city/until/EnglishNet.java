package com.example.ztt.city.until;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ztt on 16/6/2.
 */
public class EnglishNet {
    public static String English(String exam, String name) throws IOException {
        InputStream in = null;
        String result; //收到的返回结果
        String content = "name=" + URLEncoder.encode(name,"utf-8")+
                "&zkzh=" + URLEncoder.encode(exam,"utf-8");
        String urlAdress = "http://cityuit.wuxiwei.cn/index.php/Home/Campus/appQueryCet";
        try {
            URL url = new URL(urlAdress);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //设置读取超时为5秒
            httpURLConnection.setConnectTimeout(5000);
            //设置连接超时为5秒
            httpURLConnection.setReadTimeout(5000);
            //设置Post请求方式
            httpURLConnection.setRequestMethod("POST");
            //接受收入流
            httpURLConnection.setDoInput(true);
            //启动输出流，传递参数时使用
            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestProperty("Charset", "UTF-8");

            //建立连接，发起请求
            httpURLConnection.connect();

            //DataOutputStream流
            DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
            //将要上传的内容写入流中
            out.writeBytes(content);

            in = httpURLConnection.getInputStream();
            //读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            result = response.toString();
//            Log.d("in", result);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

}
