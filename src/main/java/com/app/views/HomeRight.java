package com.app.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.app.controllers.*;
import okhttp3.Call;
import org.json.JSONObject;


public class HomeRight{
    static String output = "";
    static String title = "";
    public JComponent main()
    {
        JPanel panel = new JPanel();
        GridBagLayout lay = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(lay);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        TextArea uTA = new TextArea("", 40, 1, TextArea.SCROLLBARS_VERTICAL_ONLY);
        panel.add(uTA, gbc);

        TextArea dTA = new TextArea("enter text here");

        ImageIcon img = new ImageIcon("src/main/java/com/app/image/send.png");

        JButton btn = new JButton(img);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prompt = dTA.getText();
                CallGPT call = new CallGPT();
                try {
                    title = prompt;
                    JSONObject obj = call.callGPT(prompt);
                    output = obj.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
                    uTA.setText(output);

                } catch (Exception ex) {
                    uTA.setText("internal server error");
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel down = new JPanel();

        down.setLayout(new FlowLayout());

        down.add(dTA);
        down.add(btn);
        panel.add(down, gbc);



        return panel;
    }

    public static String getOutput() {
        return output;
    }

    public static String getTitle() {
        return title;
    }
}
