package net.demo.healthifyme.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajesh5kumar on 13/7/16.
 */
public class SlotEntity implements Serializable {

    @SerializedName("slot_id")
    private int id;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("is_booked")
    private boolean isBooked;

    @SerializedName("is_expired")
    private boolean isExpired;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
