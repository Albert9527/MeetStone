package com.ZCZ1024.MeetStone.Entity;

public class Acount {
    private String username;
    private String password;
    private String userid;
    private int role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Acount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userid='" + userid + '\'' +
                ", role=" + role +
                '}';
    }
}
