package com.ZCZ1024.MeetStone.Entity;

public class User {

    /**
     * imgurl : data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADEAAAAnCAIAAAApcTfNAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFZSURBVFhH7ZexkYQwDEVpkD4oghKogAbIyYlJSQkJyeiA+zP+o0wgy4K9gBftLLb8jC0LV+f/43Oy8TnZUJ3WdeWv11Gdqqqq63qapn3f+ddbXDkJXdfN88wHz2NyEoZh2LaNLR4jzynRNA1e23EcbBqNx0no+/6JVChySiAVxnEMTIUAJyEqFSKdBKxpSSqoTpg0R/CCVMDx5kgF1SmxLAsmjR3DcVwgAuIwooEbJwFrgcMJU+c4+dhTweokIChWpESOgXSynQS3HPvr+J2EXDl20wlwEpIcR9Zha50wp5ShHPYSdtApdUr5mHVYsKeO08mhIjCETp5T+RGK7oylc++E4oDKWlhqsuqM6pSSqG1bRvWCF5Nbj1UnhvSCybi/W4Kd7EXtgjAnrFHUd3CpEzZv+H3B7/TcvSrb6YX7p9UpZPMauXcK3LxGVKcnNq8R1emHfE4WzvMPRMtKrpypv/4AAAAASUVORK5CYII=
     * intro : 呵呵
     * contact : 无
     * nickname : ZingZhou
     */

    private String imgurl;
    private String intro;
    private String contact;
    private String nickname;

    public User() {
    }

    public User(String uname) {
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
