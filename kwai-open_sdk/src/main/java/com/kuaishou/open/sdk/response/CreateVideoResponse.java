package com.kuaishou.open.sdk.response;

import com.kuaishou.open.sdk.model.VideoInfo;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-16
 */
public class CreateVideoResponse extends BaseResponse {

    private VideoInfo videoInfo;

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CreateVideoResponse{");
        sb.append("videoInfo=").append(videoInfo);
        sb.append('}');
        return sb.toString();
    }
}
