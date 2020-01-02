package com.ZCZ1024.MeetStone.EntityVo;

import com.ZCZ1024.MeetStone.Entity.Team;

import java.util.List;

public class TeamVo {

    /**
     * data : [{"img":null,"test":{"team_id":"1","team_name":"2","team_madetime":"2020-01-01","team_captain":"ff88dc16a6f74b9dae4b97644cab5d16","team_intro":"4","team_maxquota":"5","team_quota":"5","team_ctgy":"12","team_imgurl":null},"max":"5","intro":"4","quota":"5","name":"2","id":"1","captain":"ff88dc16a6f74b9dae4b97644cab5d16","time":"2020-01-01","ctgy":"12"}]
     * success : true
     * error : -1
     */

    private boolean success;
    private int error;
    private List<Team> data;

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

    public List<Team> getData() {
        return data;
    }

    public void setData(List<Team> data) {
        this.data = data;
    }
    }
