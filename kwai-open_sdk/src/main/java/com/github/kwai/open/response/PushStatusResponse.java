package com.github.kwai.open.response;

import com.github.kwai.open.KwaiOpenResultCode;
import com.github.kwai.open.model.LiveStreamStatusInfo;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class PushStatusResponse extends BaseResponse {

    private LiveStreamStatusInfo content;
    private String hostName;

    public LiveStreamStatusInfo getContent() {
        return content;
    }

    public void setContent(LiveStreamStatusInfo content) {
        this.content = content;
    }

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
        final StringBuffer sb = new StringBuffer("PushStatusResponse{");
        sb.append("content=").append(content);
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
