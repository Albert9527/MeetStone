package com.ZCZ1024.MeetStone.EntityVo;

import com.ZCZ1024.MeetStone.Entity.UserInfo;

public class UserInfoVo {

    /**
     * data : {"imgurl":"fabbf261-e1ea-4022-a482-b6872f88a9f7.png","address":"3","sex":"nan","intro":"呵呵","nickname":"ZingZhou","age":"18"}
     * success : true
     * error : -1
     */

    private UserInfo data;
    private boolean success;
    private int error;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
