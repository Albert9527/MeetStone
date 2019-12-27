package com.ZCZ1024.MeetStone.Entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

    /**
     * imgurl : fabbf261-e1ea-4022-a482-b6872f88a9f7.png
     * address : 3
     * sex : nan
     * intro : 呵呵
     * nickname : ZingZhou
     * age : 18
     */

    private String imgurl;
    private String address;
    private String sex;
    private String intro;
    private String nickname;
    private String age;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
