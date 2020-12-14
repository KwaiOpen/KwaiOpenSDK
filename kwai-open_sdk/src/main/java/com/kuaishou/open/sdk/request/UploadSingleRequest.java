package com.kuaishou.open.sdk.request;

import java.util.HashMap;
import java.util.Map;

import com.kuaishou.open.sdk.anotation.NotNull;
import com.kuaishou.open.sdk.response.StartUploadResponse;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class UploadSingleRequest {

    @NotNull
    private String uploadToken;

    @NotNull
    private String endPoint;

    @NotNull
    private byte[] fileData;

    public UploadSingleRequest() {
    }

    public UploadSingleRequest(String uploadToken, String endPoint, byte[] fileData) {
        this.uploadToken = uploadToken;
        this.endPoint = endPoint;
        this.fileData = fileData;
    }

    public UploadSingleRequest(StartUploadResponse startUploadResponse, byte[] fileData) {
        this.uploadToken = startUploadResponse.getUploadToken();
        this.endPoint = startUploadResponse.getEndpoint();
        this.fileData = fileData;
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

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Map<String, Object> toQueryParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("upload_token", uploadToken);
        return map;
    }
}
