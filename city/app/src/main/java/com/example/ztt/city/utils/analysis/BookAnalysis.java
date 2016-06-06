package com.example.ztt.city.utils.analysis;

import android.util.Log;

import com.example.ztt.city.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 *图书解析
 * Created by ztt on 16/5/31.
 */
public class BookAnalysis {
    /**
     * 解析book
     *
     * @param request
     * @return
     * @throws JSONException
     */

    public static Vector<Book> AnalysisBook(String request) {
//        Log.d("request",request);
        Vector<Book> vector = new Vector<>();

        JSONTokener mJSONTokener = new JSONTokener(request);
        //直接读取就是一个JSONObject对象。
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) mJSONTokener.nextValue();
            //得到json格式的book
            JSONArray bookArray = jsonObject.getJSONArray("book");


            Log.d("book" , "book" + bookArray);

            //遍历json格式的book
            for (int i = 0; i < bookArray.length(); i++) {
                Book book = new Book();
                JSONObject temp = bookArray.getJSONObject(i);

                book.setBookName(temp.getString("title"));
                book.setAuthor(temp.getString("auther"));
                book.setBookPress(temp.getString("press"));
                book.setPressTime(temp.getString("time"));
                book.setAdress(temp.getString("place"));
                book.setState(temp.getString("state"));

                vector.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vector;
    }


}
