package com.ZCZ1024.MeetStone.Entity;

public class Member extends User{
    private String content;

    public Member(){
        super();
    }

    public Member(String uname) {
        super(uname);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
