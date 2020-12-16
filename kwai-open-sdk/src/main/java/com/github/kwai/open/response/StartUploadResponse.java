package com.github.kwai.open.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-11
 */
public class StartUploadResponse extends BaseResponse  {


    @JsonProperty("upload_token")
    private String uploadToken;

    private String endpoint;



    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StartUploadResponse{");
        sb.append("uploadToken='").append(uploadToken).append('\'');
        sb.append(", endpoint='").append(endpoint).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
