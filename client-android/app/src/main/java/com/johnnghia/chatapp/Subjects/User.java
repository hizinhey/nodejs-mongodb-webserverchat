package com.johnnghia.chatapp.Subjects;

import java.util.ArrayList;

public class User {
    private int ID;
    private ArrayList<Message> listMess;

    public User(int id, ArrayList<Message> listMess) {
        this.ID = id;
        this.listMess = listMess;
    }

    public void setID(int id) {
        ID = id;
    }

    public ArrayList<Message> getListMess() {
        return listMess;
    }

    public void setListMess(ArrayList<Message> listMess) {
        this.listMess = listMess;
    }

    public int getID() {
        return ID;
    }
}
