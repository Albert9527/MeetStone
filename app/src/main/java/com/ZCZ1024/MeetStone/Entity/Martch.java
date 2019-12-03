package com.ZCZ1024.MeetStone.Entity;

public class Martch {
    private String martchname;

    public Martch(String s) {
        this.martchname = s;
    }

    public String getMartchname() {
        return martchname;
    }

    public void setMartchname(String martchname) {
        this.martchname = martchname;
    }

    @Override
    public String toString() {
        return "Martch{" +
                "martchname='" + martchname + '\'' +
                '}';
    }
}
