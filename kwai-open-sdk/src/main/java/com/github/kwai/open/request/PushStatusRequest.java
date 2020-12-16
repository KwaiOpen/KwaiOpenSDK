package com.github.kwai.open.request;

import com.github.kwai.open.anotation.NotNull;

/**
 * @author wuge wuge@kuaishou.com
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
