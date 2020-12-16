package com.github.kwai.open.response;

import com.github.kwai.open.KwaiOpenResultCode;
import com.github.kwai.open.model.PushUrlInfo;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class GetPushUrlResponse extends BaseResponse {

    private String hostName;

    private PushUrlInfo content;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public PushUrlInfo getContent() {
        return content;
    }

    public void setContent(PushUrlInfo content) {
        this.content = content;
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
        final StringBuffer sb = new StringBuffer("GetPushUrlResponse{");
        sb.append("hostName='").append(hostName).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}
