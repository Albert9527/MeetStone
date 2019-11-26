package com.ZCZ1024.MeetStone.Entity;

import java.io.Serializable;

public class Dynamic implements Serializable {
    private String content;

    public Dynamic(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
                "content='" + content + '\'' +
                '}';
    }
}
