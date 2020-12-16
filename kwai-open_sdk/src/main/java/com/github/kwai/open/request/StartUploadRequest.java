package com.github.kwai.open.request;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class StartUploadRequest extends BaseOpenApiRequest {

    public StartUploadRequest() {
    }

    public StartUploadRequest(String accessToken) {
        super(accessToken);
    }
}
