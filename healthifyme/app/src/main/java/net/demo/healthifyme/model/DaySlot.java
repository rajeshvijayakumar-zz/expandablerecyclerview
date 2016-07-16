package net.demo.healthifyme.model;

import com.google.gson.annotations.SerializedName;

import net.demo.healthifyme.constants.HMConstants;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rajesh5kumar on 13/7/16.
 */
public class DaySlot implements Serializable {

    @SerializedName("morning")
    private List<SlotEntity> morning;

    @SerializedName("afternoon")
    private List<SlotEntity> afternoon;

    @SerializedName("evening")
    private List<SlotEntity> evening;

    public List<SlotEntity> getMorning() {
        return morning;
    }

    public void setMorning(List<SlotEntity> morning) {
        this.morning = morning;
    }

    public List<SlotEntity> getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(List<SlotEntity> afternoon) {
        this.afternoon = afternoon;
    }

    public List<SlotEntity> getEvening() {
        return evening;
    }

    public void setEvening(List<SlotEntity> evening) {
        this.evening = evening;
    }

    public int getCurrentSlotsCount(int index) {
        switch (index) {
            case 0:
                return (getMorning() != null) ? getMorning().size() : 0;
            case 1:
                return (getAfternoon() != null) ? getAfternoon().size() : 0;
            case 2:
                return (getEvening() != null) ? getEvening().size() : 0;
            default:
                return 0;
        }
    }

    public int getMorningActiveSlots() {

        int count = 0;
        for (SlotEntity slotEntity : getMorning()) {
            if (slotEntity != null && !slotEntity.isBooked() && !slotEntity.isExpired()) {
                count++;
            }
        }
        return count;
    }

    public int getAfternoonActiveSlots() {

        int count = 0;
        for (SlotEntity slotEntity : getAfternoon()) {
            if (slotEntity != null && !slotEntity.isBooked() && !slotEntity.isExpired()) {
                count++;
            }
        }
        return count;
    }

    public int getEveningActiveSlots() {

        int count = 0;
        for (SlotEntity slotEntity : getEvening()) {
            if (slotEntity != null && !slotEntity.isBooked() && !slotEntity.isExpired()) {
                count++;
            }
        }

        return count;
    }

    public int getActiveSlotCount(int index) {
        switch (index) {
            case 0:
                return getMorningActiveSlots();
            case 1:
                return getAfternoonActiveSlots();
            case 2:
                return getEveningActiveSlots();
            default:
                return 0;
        }
    }

    public int getActiveSlotCount(String slotName) {
        int index = (slotName.equalsIgnoreCase(HMConstants.KEY_MORNING)) ? 0
                : (slotName.equalsIgnoreCase(HMConstants.KEY_AFTERNOON)) ? 1
                : (slotName.equalsIgnoreCase(HMConstants.KEY_EVENING)) ? 2 : -1;

        switch (index) {
            case 0:
                return getMorningActiveSlots();
            case 1:
                return getAfternoonActiveSlots();
            case 2:
                return getEveningActiveSlots();
            default:
                return 0;
        }
    }

    public List<SlotEntity> getCurrentSlot(String slotName) {
        int index = (slotName.equalsIgnoreCase(HMConstants.KEY_MORNING)) ? 0
                : (slotName.equalsIgnoreCase(HMConstants.KEY_AFTERNOON)) ? 1
                : (slotName.equalsIgnoreCase(HMConstants.KEY_EVENING)) ? 2 : -1;

        switch (index) {
            case 0:
                return getMorning();
            case 1:
                return getAfternoon();
            case 2:
                return getEvening();
            default:
                return null;
        }
    }


    public List<SlotEntity> getCurrentSlot(int index) {

        switch (index) {
            case 0:
                return getMorning();
            case 1:
                return getAfternoon();
            case 2:
                return getEvening();
            default:
                return null;
        }
    }

    public String getCurrentSlotName(int index) {

        switch (index) {
            case 0:
                return HMConstants.KEY_MORNING;
            case 1:
                return HMConstants.KEY_AFTERNOON;
            case 2:
                return HMConstants.KEY_EVENING;
            default:
                return null;
        }
    }
}
