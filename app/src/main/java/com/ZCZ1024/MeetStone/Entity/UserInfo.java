package com.ZCZ1024.MeetStone.Entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String name;
    private String sex;
    private String age;
    private String intro;
    private String address;
    private String ocpt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOcpt() {
        return ocpt;
    }

    public void setOcpt(String ocpt) {
        this.ocpt = ocpt;
    }


}
