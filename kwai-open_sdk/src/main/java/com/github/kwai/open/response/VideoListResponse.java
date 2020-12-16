package com.github.kwai.open.response;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.kwai.open.model.VideoInfo;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-11
 */
public class VideoListResponse extends BaseResponse {

    @JsonProperty("video_list")
    private List<VideoInfo> videoList;

    private String lastCursor;

    public List<VideoInfo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoInfo> videoList) {
        this.videoList = videoList.stream()
            .sorted(Comparator.comparing(VideoInfo::getCreateTime).reversed())
            .collect(Collectors.toList());
    }

    public String getLastCursor() {
        if (lastCursor == null) {
            if (videoList == null || videoList.size() == 0) {
                lastCursor = "";
                return lastCursor;
            }
            lastCursor = videoList.stream()
                .sorted(Comparator.comparing(VideoInfo::getCreateTime))
                .findFirst()
                .map(VideoInfo::getPhotoId)
                .orElse("");
        }

        return lastCursor;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VideoListResponse{");
        sb.append("videoList=").append(videoList);
        sb.append(", lastCursor='").append(getLastCursor()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
