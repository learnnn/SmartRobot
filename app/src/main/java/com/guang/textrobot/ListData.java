package com.guang.textrobot;

public class ListData {

    public static final int SEND = 1;
    public static final int RECEIVE = 2;
    private String contentStr;
    private int flag;
    private String time;

    public ListData(String contentStr,int flag,String time){
        setContentStr(contentStr);
        setFlag(flag);
        setTime(time);
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
