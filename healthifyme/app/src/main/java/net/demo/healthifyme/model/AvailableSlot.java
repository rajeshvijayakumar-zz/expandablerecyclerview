package net.demo.healthifyme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.demo.healthifyme.utils.DateTimeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by rajesh5kumar on 13/7/16.
 */
public class AvailableSlot implements Serializable {

    @SerializedName("slots")
    @Expose
    private Map<String, DaySlot> availableSlots;

    public Map<String, DaySlot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Map<String, DaySlot> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public String getSlotsMonthName() {
        Set<String> keys = availableSlots.keySet();

        String monthName = null;
        int index = 0;
        for (String key : keys) {
            String currentMonthName = DateTimeUtils.getMonthName(key);
            if (index == 0 || null == monthName) {
                monthName = currentMonthName;
            } else if (null != monthName && !monthName.contains(currentMonthName)) {
                monthName += " - " + currentMonthName;
                break;
            }
        }
        return monthName;
    }

    public String getKeyAtPosition(int index) {
        Set<String> keys = availableSlots.keySet();
        int count = 0;
        for (String key : keys) {
            if (count == index && index < keys.size()) {
                return key;
            }
            count++;
        }

        return null;
    }

    public List<Data> getTitles() {

        int length = availableSlots.size();
        List<Data> dataLst = new ArrayList<Data>();
        for (int i = 0; i < length; i++) {
            String key = getKeyAtPosition(i);
            int date = DateTimeUtils.getDate(key);
            String day = DateTimeUtils.getDay(key);
            dataLst.add(new Data(String.valueOf(date), day));
        }
        return dataLst;
    }
}
