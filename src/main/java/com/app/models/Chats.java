package com.app.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.Properties;
import java.util.UUID;


public class Chats {
    public JSONObject getRecords() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatGptCloneUsingJava", "root", "");
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("select * from generations");


        JSONObject data = new JSONObject();
        JSONArray dataArray = new JSONArray();


        int i = 0;
        while(rs.next())
        {
            JSONObject childObjs = new JSONObject();
            childObjs.put("id", rs.getString("id"));
            childObjs.put("title", rs.getString("title"));
            childObjs.put("content", rs.getString("content"));
            childObjs.put("createdAt", rs.getTimestamp("createdAt"));
            dataArray.put(i,childObjs);
            i++;
        }

        data.put("records", dataArray);


        return data;
    }

    public JSONObject getRecord(String id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatGptCloneUsingJava", "root", "");
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("select * from generations where id='"+id+"';");


        JSONObject childObjs = new JSONObject();


        while(rs.next())
        {
            childObjs.put("id", rs.getString("id"));
            childObjs.put("title", rs.getString("title"));
            childObjs.put("content", rs.getString("content"));
            childObjs.put("createdAt", rs.getTimestamp("createdAt"));
        }

        return  childObjs;
    }

    public String setRecord(String title, String content) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatGptCloneUsingJava", "root", "");
        PreparedStatement ps = con.prepareStatement("insert into generations (id, title, content) values (?, ?, ?);");

        String uniqueID = UUID.randomUUID().toString();
        ps.setString(1, uniqueID);
        ps.setString(2, title);
        ps.setString(3, content);

        ps.executeUpdate();

        return uniqueID;
    }

    public String deleteRecord(String id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatGptCloneUsingJava", "root", "");
        PreparedStatement ps = con.prepareStatement("delete from generations Where id='?';");
        ps.setString(1, id);
        return "record deleted successfully";
    }
}
