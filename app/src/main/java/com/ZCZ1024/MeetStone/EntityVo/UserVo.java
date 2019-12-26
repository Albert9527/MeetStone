package com.ZCZ1024.MeetStone.EntityVo;

import com.ZCZ1024.MeetStone.Entity.User;

public class UserVo {


    /**
     * data : {"imgurl":"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADEAAAAnCAIAAAApcTfNAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFZSURBVFhH7ZexkYQwDEVpkD4oghKogAbIyYlJSQkJyeiA+zP+o0wgy4K9gBftLLb8jC0LV+f/43Oy8TnZUJ3WdeWv11Gdqqqq63qapn3f+ddbXDkJXdfN88wHz2NyEoZh2LaNLR4jzynRNA1e23EcbBqNx0no+/6JVChySiAVxnEMTIUAJyEqFSKdBKxpSSqoTpg0R/CCVMDx5kgF1SmxLAsmjR3DcVwgAuIwooEbJwFrgcMJU+c4+dhTweokIChWpESOgXSynQS3HPvr+J2EXDl20wlwEpIcR9Zha50wp5ShHPYSdtApdUr5mHVYsKeO08mhIjCETp5T+RGK7oylc++E4oDKWlhqsuqM6pSSqG1bRvWCF5Nbj1UnhvSCybi/W4Kd7EXtgjAnrFHUd3CpEzZv+H3B7/TcvSrb6YX7p9UpZPMauXcK3LxGVKcnNq8R1emHfE4WzvMPRMtKrpypv/4AAAAASUVORK5CYII=","intro":"呵呵","contact":"无","nickname":"ZingZhou"}
     * success : true
     * error : -1
     */

    private User data;
    private String success;
    private int error;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
