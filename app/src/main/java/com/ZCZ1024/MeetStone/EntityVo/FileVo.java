package com.ZCZ1024.MeetStone.EntityVo;

public class FileVo {

    /**
     * path : /opt/MeetStoneProject/target/img/
     * data : 8d43a2b9-32c8-4343-ac61-a5b9f3440113.jpg
     * success : true
     * error : 1
     */

    private String path;
    private String data;
    private boolean success;
    private int error;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

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
}
