package com.example.library.database.src.team.library.demo;

public class ReturnInfo {
    public String return_id;
    public String libr_id;
    public String book_id;
    public String book_name;
    public String reader_id;
    public String return_time;
    public float fine;   //这个不需要后端赋值，我在jdbc里算了
    public String reader_name;

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setLibr_id(String libr_id) {
        this.libr_id = libr_id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public void setReturn_id(String return_id) {
        this.return_id = return_id;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }
}
