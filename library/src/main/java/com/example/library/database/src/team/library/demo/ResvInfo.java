package com.example.library.database.src.team.library.demo;
// import java.math.BigDecimal;

public class ResvInfo
{
    public String resv_id;
    public String book_id;
    public String book_name;
    public String reader_id;
    public String begin_time;
    public String end_time;
    public String status;
    public String reader_name;

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public void setResv_id(String resv_id) {
        this.resv_id = resv_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }
}
