package com.example.thumbs_app;

public class Tremp {

    String id;
    String Name;
    String TimeStart;
    String TimeEnd;
    String Location;
    String day;

    public Tremp(String id1, String name, String timeStart, String timeEnd, String location, String day) {
        id = id1;
        Name = name;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        Location = location;
        this.day = day;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}