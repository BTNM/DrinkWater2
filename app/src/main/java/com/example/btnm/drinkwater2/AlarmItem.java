package com.example.btnm.drinkwater2;

import android.widget.Switch;

public class AlarmItem {
    private int imageID;
    private String repeatingAlarmTime;
    private Switch activeSwitch;

//    private String description1;
//    private String description2;
//
//    public AlarmItem(int imageID, String description1, String description2) {
//        this.imageID = imageID;
//        this.description1 = description1;
//        this.description2 = description2;
//    }


    public AlarmItem(int imageID, String repeatingAlarmTime) {
        this.imageID = imageID;
        this.repeatingAlarmTime = repeatingAlarmTime;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getRepeatingAlarmTime() {
        return repeatingAlarmTime;
    }

    public void setRepeatingAlarmTime(String repeatingAlarmTime) {
        this.repeatingAlarmTime = repeatingAlarmTime;
    }

    public Switch getActiveSwitch() {
        return activeSwitch;
    }

    public void setActiveSwitch(Switch activeSwitch) {
        this.activeSwitch = activeSwitch;
    }

//    public String getDescription1() {
//        return description1;
//    }
//
//    public void setDescription1(String description) {
//        this.description1 = description;
//    }
//
//    public String getDescription2() {
//        return description2;
//    }
//
//    public void setDescription2(String description2) {
//        this.description2 = description2;
//    }

}
