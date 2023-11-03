package com.app.controllers;

import com.app.views.HomeRight;
import com.app.models.*;

import java.awt.*;
import com.app.views.HomeLeft;
import com.app.Main;

class CustomButton extends Button {
    private String hiddenID;

    public CustomButton(String label, String hiddenID) {
        super(label);
        this.hiddenID = hiddenID;
    }

    public String getHiddenID() {
        return hiddenID;
    }

    public void setHiddenID(String hiddenID) {
        this.hiddenID = hiddenID;
    }
}


public class InsertRecord {
    public void insert()
    {
        Chats chdb = new Chats();
        try{
            String id = chdb.setRecord(HomeRight.getTitle(), HomeRight.getOutput());
            CustomButton btn = new CustomButton(HomeRight.getTitle(), id);
            HomeLeft.up.add(btn);
            HomeLeft.panel.repaint();
            Main.mF.repaint();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
