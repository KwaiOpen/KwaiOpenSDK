package com.github.kwai.open.response;

import java.util.List;
import java.util.Map;

import com.github.kwai.open.KwaiOpenResultCode;
import com.github.kwai.open.model.LiveSceneInfo;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class GetAllSceneResponse extends BaseResponse {

    private Map<String, Map<String, List<LiveSceneInfo>>> content;

    private String hostName;

    public Map<String, Map<String, List<LiveSceneInfo>>> getContent() {
        return content;
    }

    public void setContent(Map<String, Map<String, List<LiveSceneInfo>>> content) {
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
        final StringBuffer sb = new StringBuffer("GetAllSceneResponse{");
        sb.append("content=").append(content);
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
