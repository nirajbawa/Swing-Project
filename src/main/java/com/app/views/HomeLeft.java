package com.app.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.app.models.*;
import org.json.JSONObject;

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


public class HomeLeft {
    public static JPanel up;
    public static JPanel panel;
    public Component main(Frame f)
    {
        panel = new JPanel();

        up = new JPanel();
        up.setLayout(new GridLayout(10, 2));

        Chats chdb = new Chats();

        try{
            JSONObject obj = chdb.getRecords();
            for (int i = 0; i<obj.getJSONArray("records").length(); i++)
            {
                CustomButton btn = new CustomButton(obj.getJSONArray("records").getJSONObject(i).getString("title"), obj.getJSONArray("records").getJSONObject(i).getString("id"));

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChatDialog jd = new ChatDialog(f, "chat", true, btn.getHiddenID(), btn);
                        jd.setVisible(true);
                    }
                });
                up.add(btn);
            }
        }
        catch (Exception e)
        {

        }

        panel.add(up);


        JPanel dp = new JPanel();
        dp.setLayout(new FlowLayout());
        JButton jpn = new JButton("previous");
        JButton jpb = new JButton("next");
        dp.add(jpn);
        dp.add(jpb);

        panel.add(dp);

        panel.setLayout(new GridLayout(2, 1));

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                panel.repaint();
                panel.revalidate();
            }
        });

        up.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                up.repaint();
                up.revalidate();
            }
        });

        return panel;
    }
}
