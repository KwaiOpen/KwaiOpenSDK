package com.kuaishou.open.sdk.request;

import java.util.HashMap;
import java.util.Map;

import com.kuaishou.open.sdk.anotation.NotNull;
import com.kuaishou.open.sdk.response.StartUploadResponse;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class UploadFragmentRequest {


    @NotNull
    private String uploadToken;

    @NotNull
    private String endPoint;

    @NotNull
    private byte[] fileData;

    @NotNull
    private int fragmentId;

    public UploadFragmentRequest() {
    }

    public UploadFragmentRequest(String uploadToken, String endPoint, byte[] fileData, int fragmentId) {
        this.uploadToken = uploadToken;
        this.endPoint = endPoint;
        this.fileData = fileData;
        this.fragmentId = fragmentId;
    }

    public UploadFragmentRequest(StartUploadResponse startUploadResponse, byte[] fileData, int fragmentId) {
        this.uploadToken = startUploadResponse.getUploadToken();
        this.endPoint = startUploadResponse.getEndpoint();
        this.fileData = fileData;
        this.fragmentId = fragmentId;
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

    public int getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(int fragmentId) {
        this.fragmentId = fragmentId;
    }

    public Map<String, Object> toQueryParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("upload_token", uploadToken);
        map.put("fragment_id", fragmentId);
        return map;
    }
}
