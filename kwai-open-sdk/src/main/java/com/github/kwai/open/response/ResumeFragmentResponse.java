package com.github.kwai.open.response;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-16
 */
public class ResumeFragmentResponse extends BaseResponse {

    private String uploadToken;

    private String endPoint;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResumeFragmentResponse{");
        sb.append("uploadToken='").append(uploadToken).append('\'');
        sb.append(", endPoint='").append(endPoint).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
