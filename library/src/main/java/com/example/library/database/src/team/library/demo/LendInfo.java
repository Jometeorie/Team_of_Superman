package com.example.library.database.src.team.library.demo;

public class LendInfo {
    public String lend_id;
    public String libr_id;
    public String book_id;
    public String book_name;
    public String reader_id;
    public String lend_time;
    public String reader_name;

    public LendInfo() {}
    public LendInfo(String lend_id,  String libr_id, String book_id, String book_name, 
                                                String reader_id, String lend_time, String reader_name) {
                                                    this.lend_id = lend_id;
                                                    this.libr_id = libr_id;
                                                    this.book_id = book_id;
                                                    this.book_name = book_name;
                                                    this.reader_id = reader_id;
                                                    this.lend_time = lend_time;
                                                    this.reader_name = reader_name;                  
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public void setLend_time(String lend_time) {
        this.lend_time = lend_time;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setLend_id(String lend_id) {
        this.lend_id = lend_id;
    }

    public void setLibr_id(String libr_id) {
        this.libr_id = libr_id;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }
}