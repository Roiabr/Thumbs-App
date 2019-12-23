package com.example.thumbs_app;

public class Tremp {

    String id;
    String Name;
    String TimeStart;
    String TimeEnd;
    String LocationStart;
    String LocationEnd;
    String day;

    public Tremp(){

    }

    public Tremp(String id1, String name, String timeStart, String timeEnd, String location,String End, String day) {
        id = id1;
        Name = name;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        LocationStart = location;
        LocationEnd= End;
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationStart() {
        return LocationStart;
    }

    public void setLocationStart(String locationStart) {
        LocationStart = locationStart;
    }

    public String getLocationEnd() {
        return LocationEnd;
    }

    public void setLocationEnd(String locationEnd) {
        LocationEnd = locationEnd;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(String timeStart) {
        TimeStart = timeStart;
    }

    public String getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        TimeEnd = timeEnd;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}