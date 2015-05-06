package com.guang.textrobot;

import android.location.GpsStatus;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpData extends AsyncTask<String,Void,String> {

    private HttpURLConnection connection;
    private String sendString;
    private HttpResponseListener listener;

    public HttpData(String sendString,HttpResponseListener listener){
        this.sendString = sendString;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String INFO = URLEncoder.encode(sendString,"utf-8");
            String urlString = "http://www.tuling123.com/openapi/api?key=" +
                    "ad76ea7191fbe3e90544f52048cde1d5" + "&info=" + INFO; ;
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine())!= null){
                sb.append(line);
            }
            br.close();
            connection.disconnect();
            return  sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        listener.getResponseData(s);
        super.onPostExecute(s);
    }
}
