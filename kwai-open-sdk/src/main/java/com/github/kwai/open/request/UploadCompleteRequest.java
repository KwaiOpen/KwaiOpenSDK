package com.github.kwai.open.request;

import java.util.HashMap;
import java.util.Map;

import com.github.kwai.open.anotation.NotNull;
import com.github.kwai.open.response.StartUploadResponse;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class UploadCompleteRequest {


    @NotNull
    private String uploadToken;

    @NotNull
    private String endPoint;

    @NotNull
    private int fragmentCount;

    public UploadCompleteRequest() {
    }

    public UploadCompleteRequest(String uploadToken, String endPoint, int fragmentCount) {
        this.uploadToken = uploadToken;
        this.endPoint = endPoint;
        this.fragmentCount = fragmentCount;
    }

    public UploadCompleteRequest(StartUploadResponse startUploadResponse, int fragmentCount) {
        this.uploadToken = startUploadResponse.getUploadToken();
        this.endPoint = startUploadResponse.getEndpoint();
        this.fragmentCount = fragmentCount;
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

    public int getFragmentCount() {
        return fragmentCount;
    }

    public void setFragmentCount(int fragmentCount) {
        this.fragmentCount = fragmentCount;
    }

    public Map<String, Object> toQueryParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("upload_token", uploadToken);
        map.put("fragment_count", fragmentCount);
        return map;
    }
}
