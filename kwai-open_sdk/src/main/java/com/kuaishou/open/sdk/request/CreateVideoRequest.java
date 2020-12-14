package com.kuaishou.open.sdk.request;

import com.kuaishou.open.sdk.anotation.NotNull;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-16
 */
public class CreateVideoRequest extends BaseOpenApiRequest {

    @NotNull
    private byte[] videoFileData;
    @NotNull
    private String caption;
    @NotNull
    private byte[] cover;
    private String stereoType;

    public byte[] getVideoFileData() {
        return videoFileData;
    }

    public void setVideoFileData(byte[] videoFileData) {
        this.videoFileData = videoFileData;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public String getStereoType() {
        return stereoType;
    }

    public void setStereoType(String stereoType) {
        this.stereoType = stereoType;
    }
}
