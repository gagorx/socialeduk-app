package com.example.socialeduk.views.events;

public class EventsContent {

    public String day;
    public String month;
    public String hour;
    public String titleEvent;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTitleEvent() {
        return titleEvent;
    }

    public void setTitleEvent(String titleEvent) {
        this.titleEvent = titleEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public String descriptionEvent;

    public EventsContent(String day, String month, String hour, String titleEvent, String descriptionEvent) {
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.titleEvent = titleEvent;
        this.descriptionEvent = descriptionEvent;
    }



}
