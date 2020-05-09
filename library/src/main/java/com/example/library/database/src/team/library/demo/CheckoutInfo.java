package com.example.library.database.src.team.library.demo;

public class CheckoutInfo {
    public String checkout_id;
    public String libr_id;
    public String book_id;
    public String book_name;
    public String reader_id;
    public String end_time;
    public String reader_name;

    public CheckoutInfo(String checkout_id,  String libr_id, String book_id, String book_name, 
                                                String reader_id, String end_time, String reader_name) {
                                                    this.checkout_id = checkout_id;
                                                    this.libr_id = libr_id;
                                                    this.book_id = book_id;
                                                    this.book_name = book_name;
                                                    this.reader_id = reader_id;
                                                    this.end_time = end_time;
                                                    this.reader_name = reader_name;                  
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setCheckout_id(String checkout_id) {
        this.checkout_id = checkout_id;
    }

    public void setLibr_id(String libr_id) {
        this.libr_id = libr_id;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }
}
