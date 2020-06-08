package com.example.library.database.src.team.library.demo;

import java.math.BigDecimal;

public class MoneyTakeInfo {
    public String take_id;
    public String libr_id;
    public String reader_id;
    public String take_time;
    public BigDecimal money_Amount;
    public int type;
    public String reader_name;

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public void setLibr_id(String libr_id) {
        this.libr_id = libr_id;
    }

    public void setMoney_Amount(BigDecimal money_Amount) {
        this.money_Amount = money_Amount;
    }

    public void setTake_id(String take_id) {
        this.take_id = take_id;
    }

    public void setTake_time(String take_time) {
        this.take_time = take_time;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }
}
