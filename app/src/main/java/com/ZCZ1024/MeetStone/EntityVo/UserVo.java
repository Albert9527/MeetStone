package com.ZCZ1024.MeetStone.EntityVo;

import com.ZCZ1024.MeetStone.Entity.User;

public class UserVo {
    private String success;
    private String error;

    private User data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return data;
    }

    public void setUser(User data) {
        this.data = data;
    }


}
