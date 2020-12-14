package com.kuaishou.open.sdk.model;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class LiveStreamStatusInfo {

    private String liveStreamName;
    private String status;
    private String statusMsg;

    public String getLiveStreamName() {
        return liveStreamName;
    }

    public void setLiveStreamName(String liveStreamName) {
        this.liveStreamName = liveStreamName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LiveStreamStatusInfo{");
        sb.append("liveStreamName='").append(liveStreamName).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", statusMsg='").append(statusMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
