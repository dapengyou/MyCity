package com.example.ztt.city.view.fragement;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ztt.city.R;
import com.example.ztt.city.model.Book;
import com.example.ztt.city.until.BookNet;
import com.example.ztt.city.utils.analysis.BookAnalysis;
import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.GrowEffect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 图书Fragment
 */

public class BookFragment extends Fragment implements View.OnClickListener {
    //查询的图标
    private TextView mTextView;
    //输入框
    private EditText mEditText;
    //ListView第三方库
    private JazzyListView mListView;

    private View view;
    //提示框
    public static ProgressDialog sProgressDialog;
    //获得context值
    Context context;

    private Vector<Book> mBookVector = new Vector<>();
    private List<Map<String, Object>> mList;
    private SimpleAdapter mSimpleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        view = inflater.inflate(R.layout.fragment_book, container, false);

        if (context != null) {
            //初始化
            initialize();
            initList();
        }

        return view;
    }

    private void initialize() {
        mTextView = (TextView) view.findViewById(R.id.textView_bookfind);
        mEditText = (EditText) view.findViewById(R.id.book_input);
        mListView = (JazzyListView) view.findViewById(R.id.book_listview);
        //设置监听
        mTextView.setOnClickListener(this);
        //设置滑动效果
        mListView.setTransitionEffect(new GrowEffect());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_bookfind:
                sProgressDialog = ProgressDialog.show(getActivity(), null, "获取图书中......");
                ConnectTask task = new ConnectTask();
                task.execute();
                break;

        }
    }

    /**
     * 连接接口的异步
     * 使用volley
     */

    private class ConnectTask extends AsyncTask<Void, Void, Void> {
        //获取到输入的书名
        String bookName = mEditText.getText().toString();

        String resultBook = null;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                resultBook = BookNet.Book(bookName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //更新UI
        protected void onPostExecute(Void result) {
            updateUI(resultBook);

        }
    }

    private void updateUI(String result) {
        sProgressDialog.dismiss();
        mBookVector = BookAnalysis.AnalysisBook(result);
        initList();

    }

    /**
     * 显示图书列表
     */

    private void initList() {
        mList = getData();
        mSimpleAdapter = new SimpleAdapter(context, mList, R.layout.item_book,
                new String[]{"bookName", "author", "bookPress",
                        "pressTime", "Address", "state"},
                new int[]{R.id.bookName, R.id.author,
                        R.id.bookPress, R.id.pressTime,
                        R.id.bookAdress, R.id.bookState});
        mListView.setAdapter(mSimpleAdapter);


    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mBookVector.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            Book book = mBookVector.get(i);
            map.put("bookName", book.getBookName());
            map.put("author", book.getAuthor());
            map.put("bookPress", book.getBookPress());
            map.put("pressTime", book.getPressTime());
            map.put("Address", book.getAdress());
            map.put("state", book.getState());
            list.add(map);
        }
        return list;
    }


}
