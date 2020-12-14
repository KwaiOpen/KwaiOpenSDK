package com.kuaishou.open.sdk.response;

import com.kuaishou.open.sdk.KwaiOpenResultCode;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class StopPushResponse extends BaseResponse {

    private String hostName;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public KwaiOpenResultCode getResultCode() {
        if (getResult() == 0) {
            return KwaiOpenResultCode.SUCCESS;
        }
        if (String.valueOf(getResult()).startsWith("200")) {
            return KwaiOpenResultCode.valueOf(100000000 + getResult());
        }
        return super.getResultCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StopPushResponse{");
        sb.append("hostName='").append(hostName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
