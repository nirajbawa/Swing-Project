package com.app.views;

import com.app.controllers.CallGPT;
import com.app.models.Chats;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import com.app.models.*;

public class ChatDialog extends JDialog {
     public ChatDialog(Frame parent, String title, boolean model, String id, Button b) {
         super(parent, title, model);

         JMenuBar menuBar = new JMenuBar();
         JMenu menu = new JMenu("file");
         JMenuItem mi = new JMenuItem("delete");
         mi.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Chats chdb = new Chats();
                 try {


                     System.out.println( chdb.deleteRecord(id));
                     b.setVisible(false);
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
             }
         });
         menu.add(mi);
         menuBar.add(menu);
         setJMenuBar(menuBar);

         Chats chdb = new Chats();

         try{
             JSONObject obj = chdb.getRecord(id);
             TextArea ta = new TextArea(obj.getString("content"), 40, 1, TextArea.SCROLLBARS_VERTICAL_ONLY);
             add(ta);

             setSize(500, 500);
             setLayout(new GridLayout(1, 1));
         }
         catch (Exception e)
         {

         }


     }
}
