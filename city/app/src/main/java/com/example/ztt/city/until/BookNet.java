package com.example.ztt.city.until;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ztt on 16/6/2.
 */
public class BookNet {
    public static String Book(String bookName) throws IOException {
        InputStream in = null;
        String result; //收到的返回结果
        //http://cityuit.wuxiwei.cn/index.php/Home/Campus/appLibrary/title/书名
        String urlAdress =
                "http://cityuit.wuxiwei.cn/index.php/Home/Campus/appLibrary/title/" + bookName;

        try {
            URL url = new URL(urlAdress);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //设置读取超时为5秒
            httpURLConnection.setConnectTimeout(5000);
            //设置连接超时为5秒
            httpURLConnection.setReadTimeout(5000);
            //设置Post请求方式
            httpURLConnection.setRequestMethod("GET");
            //接受收入流
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            //建立连接，发起请求
            httpURLConnection.connect();
            in = httpURLConnection.getInputStream();
            //读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
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
