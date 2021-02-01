package com.example.lead_insert;

public class NotificationListModel {

    String notification_msg;
    String datetime;
    String type_notfy;

    public NotificationListModel(String notification_msg, String date, String type_notfy) {
        this.notification_msg = notification_msg;
        this.datetime = date;
        this.type_notfy = type_notfy;
    }

    public String getNotification_msg() {
        return notification_msg;
    }

    public void setNotification_msg(String notification_msg) {
        this.notification_msg = notification_msg;
    }

    public void setType_notfy(String type_notfy) {
        this.type_notfy = type_notfy;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getType_notfy() {
        return type_notfy;
    }
}

