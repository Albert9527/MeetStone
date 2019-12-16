package com.ZCZ1024.MeetStone.Entity;

public class Acount {
    private String userName;
    private String userPsd;
    private String userID;
    private int userRole;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Acount{" +
                "userName='" + userName + '\'' +
                ", userPsd='" + userPsd + '\'' +
                ", userID='" + userID + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
