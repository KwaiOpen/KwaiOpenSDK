package com.kuaishou.open.sdk.request;

import java.util.HashMap;
import java.util.Map;

import com.kuaishou.open.sdk.anotation.NotNull;
import com.kuaishou.open.sdk.response.StartUploadResponse;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class GetFragmentResumeInfoRequest {


    @NotNull
    private String uploadToken;

    @NotNull
    private String endPoint;

    public GetFragmentResumeInfoRequest() {
    }

    public GetFragmentResumeInfoRequest(StartUploadResponse startUploadResponse) {
        this.uploadToken = startUploadResponse.getUploadToken();
        this.endPoint = startUploadResponse.getEndpoint();
    }

    public GetFragmentResumeInfoRequest(String uploadToken, String endPoint) {
        this.uploadToken = uploadToken;
        this.endPoint = endPoint;
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

    public Map<String, Object> toQueryParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("upload_token", uploadToken);
        return map;
    }
}
