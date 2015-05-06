package com.guang.textrobot;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class MainActivity extends ActionBarActivity implements HttpResponseListener{

    private HttpData httpData;
    private EditText sendText;
    private ListView listView;
    private MyListAdapter adapter;
    private List<ListData> lists;
    private Button sendBtn;
    long currentTime,oldTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendTextString = sendText.getText().toString();
                if(!("".equals(sendTextString))){
                    ListData listData;
                    listData = new ListData(sendTextString,ListData.SEND,getTime());
                    lists.add(listData);
                    adapter.notifyDataSetChanged();
                    httpData = (HttpData) new HttpData(sendTextString,MainActivity.this).execute();
                    sendText.setText("");
                }
            }
        });
    }

    @Override
    public void getResponseData(String data) {
        parseData(data);
    }

    private void initView(){
        sendText = (EditText) findViewById(R.id.sendText);
        listView = (ListView) findViewById(R.id.listView);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        lists = new ArrayList<ListData>();
        ListData listData;
        listData = new ListData(welcomeString(),ListData.RECEIVE,getTime());
        lists.add(listData);
        adapter = new MyListAdapter(lists,this);
        listView.setAdapter(adapter);
    }

    private void parseData(String data){
        try {
            JSONObject jb = new JSONObject(data);
            ListData listData;
            listData = new ListData(jb.getString("text"),ListData.RECEIVE,getTime());
            lists.add(listData);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String welcomeString(){
        String[] strings;
        int index;
        strings =  getResources().getStringArray(R.array.welcomeString);
        index = (int) (Math.random()*(strings.length - 1));
        return strings[index];
    }

    private String getTime(){
        currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date currentDate = new Date();
        String str = format.format(currentDate);
        if(currentTime - oldTime >= 10000){
            oldTime = currentTime;
            return str;
        }
        oldTime = currentTime;
        return "";
    }
}
