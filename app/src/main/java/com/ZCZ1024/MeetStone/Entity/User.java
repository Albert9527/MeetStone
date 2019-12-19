package com.ZCZ1024.MeetStone.Entity;

public class User {
    private String uname;
    private String touxiang;
    private String intro;


    public User(String uname) {
        this.uname = uname;
    }

    public User() {

    }

    public User(String name, String tou) {
        this.uname = name;
        this.touxiang = tou;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                '}';
    }
}
