package net.demo.healthifyme.model;

/**
 * Created by rajesh5kumar on 14/7/16.
 */
public class Data {

    private String date;
    private String day;

    Data(String date, String day) {
        this.date = date;
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
