package com.ZCZ1024.MeetStone.Entity;

import java.io.Serializable;

public class Team implements Serializable {

    /**
     * img : null
     * test : {"team_id":"1","team_name":"2","team_madetime":"2020-01-01","team_captain":"ff88dc16a6f74b9dae4b97644cab5d16","team_intro":"4","team_maxquota":"5","team_quota":"5","team_ctgy":"12","team_imgurl":null}
     * max : 5
     * intro : 4
     * quota : 5
     * name : 2
     * id : 1
     * captain : ff88dc16a6f74b9dae4b97644cab5d16
     * time : 2020-01-01
     * ctgy : 12
     */

    private Object img;
    private TestBean test;
    private String max;
    private String intro;
    private String quota;
    private String name;
    private String id;
    private String captain;
    private String time;
    private String ctgy;

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }

    public TestBean getTest() {
        return test;
    }

    public void setTest(TestBean test) {
        this.test = test;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCtgy() {
        return ctgy;
    }

    public void setCtgy(String ctgy) {
        this.ctgy = ctgy;
    }

    public static class TestBean {
        /**
         * team_id : 1
         * team_name : 2
         * team_madetime : 2020-01-01
         * team_captain : ff88dc16a6f74b9dae4b97644cab5d16
         * team_intro : 4
         * team_maxquota : 5
         * team_quota : 5
         * team_ctgy : 12
         * team_imgurl : null
         */

        private String team_id;
        private String team_name;
        private String team_madetime;
        private String team_captain;
        private String team_intro;
        private String team_maxquota;
        private String team_quota;
        private String team_ctgy;
        private Object team_imgurl;

        public String getTeam_id() {
            return team_id;
        }

        public void setTeam_id(String team_id) {
            this.team_id = team_id;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getTeam_madetime() {
            return team_madetime;
        }

        public void setTeam_madetime(String team_madetime) {
            this.team_madetime = team_madetime;
        }

        public String getTeam_captain() {
            return team_captain;
        }

        public void setTeam_captain(String team_captain) {
            this.team_captain = team_captain;
        }

        public String getTeam_intro() {
            return team_intro;
        }

        public void setTeam_intro(String team_intro) {
            this.team_intro = team_intro;
        }

        public String getTeam_maxquota() {
            return team_maxquota;
        }

        public void setTeam_maxquota(String team_maxquota) {
            this.team_maxquota = team_maxquota;
        }

        public String getTeam_quota() {
            return team_quota;
        }

        public void setTeam_quota(String team_quota) {
            this.team_quota = team_quota;
        }

        public String getTeam_ctgy() {
            return team_ctgy;
        }

        public void setTeam_ctgy(String team_ctgy) {
            this.team_ctgy = team_ctgy;
        }

        public Object getTeam_imgurl() {
            return team_imgurl;
        }

        public void setTeam_imgurl(Object team_imgurl) {
            this.team_imgurl = team_imgurl;
        }
    }
}
