package com.ZCZ1024.MeetStone.Entity;

import java.io.Serializable;
import java.sql.Date;

public class Team implements Serializable {
    private String name;
    private String captain;
    private Date madetime;
    private String intro;
    private int quota;
    private String TeamCtgy;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}
