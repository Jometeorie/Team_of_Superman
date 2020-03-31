package com.example.library.database.src.team.library.demo;

import java.math.BigDecimal;

public class BookInfo {
    private String book_name;       //书籍名称
    private String author;          //书籍作者
    private String location;        //书籍位置
    private BigDecimal price;       //书籍价格
    private String category;        //书籍类别
    private boolean state;          //书籍是否被借阅

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", state=" + state +
                '}';
    }
}
