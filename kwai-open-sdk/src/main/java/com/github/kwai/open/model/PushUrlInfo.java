package com.github.kwai.open.model;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class PushUrlInfo {

    private String pushUrl;
    private String liveStreamName;

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getLiveStreamName() {
        return liveStreamName;
    }

    public void setLiveStreamName(String liveStreamName) {
        this.liveStreamName = liveStreamName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PushUrlInfo{");
        sb.append("pushUrl='").append(pushUrl).append('\'');
        sb.append(", liveStreamName='").append(liveStreamName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
