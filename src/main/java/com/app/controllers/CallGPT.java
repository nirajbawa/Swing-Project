package com.app.controllers;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class CallGPT {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String ApiKey = "yourkey";
    public JSONObject callGPT(String text) throws IOException {
        OkHttpClient client = new OkHttpClient();



        JSONObject data = new JSONObject();
        data.put("model", "gpt-3.5-turbo");
        JSONArray js = new JSONArray();
        JSONObject messages = new JSONObject();
        messages.put("role", "system");
        messages.put("content", text);
        js.put(0, messages);
        data.put("messages", js);



        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, data.toString());

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + ApiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        return new JSONObject(responseBody);
    }
}
