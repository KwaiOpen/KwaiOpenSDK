package com.github.kwai.open.request;

import java.util.HashMap;
import java.util.Map;

import com.github.kwai.open.anotation.NotNull;
import com.github.kwai.open.api.IOpenAPI;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class BaseOpenApiRequest {

    @NotNull
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public BaseOpenApiRequest() {
    }

    public BaseOpenApiRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", openAPI.getAppId());
        map.put("access_token", accessToken);
        return map;
    }

}
