package com.app.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.app.controllers.InsertRecord;

public class Navbar {
    public JMenuBar main()
    {
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Record");
        JMenuItem mi = new JMenuItem("Save Current Response");
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertRecord().insert();
            }
        });
        m.add(mi);
        mb.add(m);
        return mb;
    }
}
