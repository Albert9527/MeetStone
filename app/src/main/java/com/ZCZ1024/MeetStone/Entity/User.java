package com.ZCZ1024.MeetStone.Entity;

public class User {
    private String uname;

    public User(String uname) {
        this.uname = uname;
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
