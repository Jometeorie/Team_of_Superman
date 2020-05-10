package com.example.library.database.src.team.library.demo;

import java.math.BigDecimal;

public class ReaderInfo {
    public String reader_id;
    public String reader_name;
    public String password;
    public String e_mail;
    public BigDecimal reader_diposit;
    public BigDecimal reader_fine;
    public int state;

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReader_diposit(BigDecimal reader_diposit) {
        this.reader_diposit = reader_diposit;
    }

    public void setReader_fine(BigDecimal reader_fine) {
        this.reader_fine = reader_fine;
    }

    public void setState(int state) {
        this.state = state;
    }
}
