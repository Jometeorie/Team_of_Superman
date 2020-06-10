package com.example.library.database.src.team.library.demo;

import java.math.BigDecimal;

public class MoneyTakeMonthlyInfo {

        public double monthly;
        public double weekly;
        public double daily;
        public String timePeriod;
        public int type ;


        public void setMonthly(double Monthly) {
            this.monthly = Monthly;
        }

        public void setWeekly(double Weekly) {
        this.weekly = Weekly;
    }
        public void setDaily(double Daily) {
        this.daily = Daily;
    }
        public void setType(int type) {
            this.type = type;
        }
        public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }


    }

