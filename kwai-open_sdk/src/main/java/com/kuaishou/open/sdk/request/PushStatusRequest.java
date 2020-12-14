package com.kuaishou.open.sdk.request;

import java.util.Map;

import com.kuaishou.open.sdk.anotation.NotNull;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class PushStatusRequest extends BaseOpenApiRequest {

    public PushStatusRequest() {
    }

    public PushStatusRequest(String accessToken, String streamName) {
        super(accessToken);
        this.streamName = streamName;
    }

    @NotNull
    private String streamName;

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }
}
