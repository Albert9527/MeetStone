package com.ZCZ1024.MeetStone.EntityVo;

import com.ZCZ1024.MeetStone.Entity.Martch;

import java.util.List;

public class MartchVo {

    /**
     * data : [{"img":null,"intro":"bs1","name":"bs","id":"1","ctgy":"213","upuser":"me","url":"www.baidu.com","uptime":"2020-01-01"}]
     * success : true
     * error : -1
     */

    private boolean success;
    private int error;
    private List<Martch> data;

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

    public List<Martch> getData() {
        return data;
    }

    public void setData(List<Martch> data) {
        this.data = data;
    }

}
