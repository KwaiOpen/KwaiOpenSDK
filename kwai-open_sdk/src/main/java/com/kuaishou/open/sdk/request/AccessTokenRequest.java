package com.kuaishou.open.sdk.request;

import java.util.HashMap;
import java.util.Map;

import com.kuaishou.open.sdk.anotation.NotNull;
import com.kuaishou.open.sdk.api.IOpenAPI;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class AccessTokenRequest {

    @NotNull
    private String code;

    @NotNull
    private String appSecret;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public AccessTokenRequest() {
    }

    public AccessTokenRequest(String code, String appSecret) {
        this.code = code;
        this.appSecret = appSecret;
    }

    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", openAPI.getAppId());
        map.put("app_secret", appSecret);
        map.put("grant_type", "code");
        map.put("code", code);
        return map;
    }


}
