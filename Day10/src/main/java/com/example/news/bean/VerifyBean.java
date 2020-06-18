package com.example.news.bean;

public class VerifyBean {

    public VerifyBean(){}

    public VerifyBean(int code, String ret, String data) {
        this.code = code;
        this.ret = ret;
        this.data = data;
    }

    /**
     * code : 200
     * ret : success
     * data : icpz
     */

    private int code;
    private String ret;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
