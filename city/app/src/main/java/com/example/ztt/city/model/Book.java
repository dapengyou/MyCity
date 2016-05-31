package com.example.ztt.city.model;

/**
 * Created by ztt on 16/5/31.
 */
public class Book {
    private String bookName;//书名
    private String author; //作者
    private String bookPress;//出版社
    private String pressTime;//出版时间
    private String Adress;   //藏书位置
    private String state;    //图书状态
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getPressTime() {
        return pressTime;
    }

    public void setPressTime(String pressTime) {
        this.pressTime = pressTime;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
