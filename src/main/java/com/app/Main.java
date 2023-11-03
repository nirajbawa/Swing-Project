package com.app;

import javax.swing.*;
import com.app.views.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static JFrame mF;
    Main(String title)
    {
        mF = new JFrame(title);
        HomeRight hR = new HomeRight();
        HomeLeft hL = new HomeLeft();
        Navbar navbar = new Navbar();

        mF.setJMenuBar(navbar.main());

        mF.add(hL.main(mF));
        mF.add(hR.main());

        mF.setLayout(new GridLayout(1, 2));
        mF.setVisible(true);

        mF.setSize(1500, 800);
        mF.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mF.repaint();
                mF.revalidate();
            }
        });
    }


    public static void main(String[] args) {
        new Main("Chat Gpt");
    }
}
