package com.kuaishou.open.sdk.request;

import java.util.Map;

import com.kuaishou.open.sdk.anotation.NotNull;
import com.kuaishou.open.sdk.api.IOpenAPI;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class StopPushRequest extends BaseOpenApiRequest {

    public StopPushRequest() {
    }

    public StopPushRequest(String accessToken, String liveStreamName) {
        super(accessToken);
        this.liveStreamName = liveStreamName;
    }

    @NotNull
    private String liveStreamName;

    public String getLiveStreamName() {
        return liveStreamName;
    }

    public void setLiveStreamName(String liveStreamName) {
        this.liveStreamName = liveStreamName;
    }

    @Override
    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = super.toQueryParam(openAPI);
        map.put("liveStreamName", liveStreamName);
        return map;
    }
}
