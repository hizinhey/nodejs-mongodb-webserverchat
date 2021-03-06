package com.johnnghia.chatapp.Subjects;

import org.joda.time.DateTime;

import java.util.Date;
import java.sql.Time;

public class Message {
    DateTime timeSend;
    String detailMess;
    boolean adminSend;

    public Message(DateTime timeSend, String detailMess, boolean adminSend) {
        this.timeSend = timeSend;
        this.detailMess = detailMess;
        this.adminSend = adminSend;
    }

    public DateTime getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(DateTime timeSend) {
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
