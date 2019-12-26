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

    public Team() {

    }

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public Date getMadetime() {
        return madetime;
    }

    public void setMadetime(Date madetime) {
        this.madetime = madetime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getTeamCtgy() {
        return TeamCtgy;
    }

    public void setTeamCtgy(String teamCtgy) {
        TeamCtgy = teamCtgy;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", captain='" + captain + '\'' +
                ", madetime=" + madetime +
                ", intro='" + intro + '\'' +
                ", quota=" + quota +
                ", TeamCtgy='" + TeamCtgy + '\'' +
                '}';
    }
}
