package com.kuaishou.open.sdk.response;


/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-16
 */
public class UploadFileResponse extends BaseResponse {


    private String uploadToken;

    private String endPoint;

    private Boolean fragmentWay;

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public Boolean getFragmentWay() {
        return fragmentWay;
    }

    public void setFragmentWay(Boolean fragmentWay) {
        this.fragmentWay = fragmentWay;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UploadFileResponse{");
        sb.append("uploadToken='").append(uploadToken).append('\'');
        sb.append(", endPoint='").append(endPoint).append('\'');
        sb.append(", fragmentWay=").append(fragmentWay);
        sb.append('}');
        return sb.toString();
    }
}
