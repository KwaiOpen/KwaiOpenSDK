package com.github.kwai.open.request;

import com.github.kwai.open.anotation.NotNull;
import com.github.kwai.open.response.StartUploadResponse;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-16
 */
public class ResumeFragmentRequest {

    public ResumeFragmentRequest() {
    }

    public ResumeFragmentRequest(String uploadToken, String endPoint, byte[] videoFileData) {
        this.videoFileData = videoFileData;
        this.uploadToken = uploadToken;
        this.endPoint = endPoint;
    }

    public ResumeFragmentRequest(StartUploadResponse startUploadResponse, byte[] videoFileData) {
        this.videoFileData = videoFileData;
        this.uploadToken = startUploadResponse.getUploadToken();
        this.endPoint = startUploadResponse.getEndpoint();
    }


    @NotNull
    private byte[] videoFileData;

    private String uploadToken;

    private String endPoint;

    public byte[] getVideoFileData() {
        return videoFileData;
    }

    public void setVideoFileData(byte[] videoFileData) {
        this.videoFileData = videoFileData;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
