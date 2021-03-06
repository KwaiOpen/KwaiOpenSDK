package com.github.kwai.open.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.kwai.open.model.VideoInfo;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-11
 */
public class VideoInfoResponse  extends BaseResponse   {


    @JsonProperty("video_info")
    private VideoInfo videoInfo;


    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VideoInfoResponse{");
        sb.append("videoInfo=").append(videoInfo);
        sb.append('}');
        return sb.toString();
    }
}
