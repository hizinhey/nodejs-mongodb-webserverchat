package com.johnnghia.chatapp.Subjects;

import java.sql.Time;

public class Message {
    Time timeSend;
    String detailMess;
    boolean adminSend;

    public Message(Time timeSend, String detailMess, boolean adminSend) {
        this.timeSend = timeSend;
        this.detailMess = detailMess;
        this.adminSend = adminSend;
    }

    public Time getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Time timeSend) {
        this.timeSend = timeSend;
    }

    public String getDetailMess() {
        return detailMess;
    }

    public void setDetailMess(String detailMess) {
        this.detailMess = detailMess;
    }

    public boolean isAdminSend() {
        return adminSend;
    }

    public void setAdminSend(boolean adminSend) {
        this.adminSend = adminSend;
    }
}
