package com.weirdsoft.Planner;


import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kater
 */
public class Month {

    int month;
    int year;
    int length;
    int startDay;

    public Month(YearMonth ym) {
        month = ym.getMonthValue();
        year = ym.getYear();
        startDay = ym.atDay(1).getDayOfWeek().getValue();
        length = ym.lengthOfMonth();
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public int getStartDay() {
        return startDay;
    }
    
    public String getDisplayText() {
        return YearMonth.of(year, month).format(DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("en")));
    }
}
